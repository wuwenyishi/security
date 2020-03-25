package com.haha.xixi.config;


import com.haha.xixi.exception.AuthExceptionEntryPoint;
import com.haha.xixi.exception.BootOAuth2WebResponseExceptionTranslator;
import com.haha.xixi.exception.CustomAccessDeniedHandler;
import com.haha.xixi.oath.UserVoDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证相关的配置
 *
 * @author xuemd
 * @date 2020/3/25
 **/
@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${access.token.validity:360}") // 默认值过期时间360
    private int accessTokenValiditySeconds;

    @Value("${access.refresh.validity:420}") // 默认值7分钟
    private int refreshTokenValiditySeconds;

    @Autowired
    private DataSource dataSource;
    @Autowired
    private ClientDetailsService clientDetails;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    private static final String DEMO_RESOURCE_ID = "order";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(DEMO_RESOURCE_ID).stateless(true)
                    //自定义Token异常信息,用于token校验失败返回信息
                    .authenticationEntryPoint(new AuthExceptionEntryPoint())
                    //授权异常处理
                    .accessDeniedHandler(new CustomAccessDeniedHandler());
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .authorizeRequests().antMatchers("/oauth/**").permitAll()
                    .and()
                    .authorizeRequests().antMatchers("/sysUser/**").authenticated()
                    .and()
                    //验证角色自动会加ROLE_前缀,以下需要用户有ROLE_USER 角色才能访问
                    .authorizeRequests().antMatchers("/hello/**").hasRole("USER")
                    .and().
                    authorizeRequests().anyRequest().authenticated();
            // @formatter:on
        }
    }



    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //配置客户端
        clients.inMemory().withClient("client_1")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("select")
                .authorities("oauth2")
                .secret("123456");
//            clients.withClientDetails(clientDetails);
    }

       /* @Bean
        @ConditionalOnMissingBean(ClientDetailsService.class)
        public ClientDetailsService clientDetails() {
            return new JdbcClientDetailsService(dataSource);
        }*/

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 配置TokenServices参数
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        DefaultTokenServices services = new DefaultTokenServices();
        services.setSupportRefreshToken(false);// refresh_token存放到数据表oauth_refresh_token
        services.setTokenStore(jdbcTokenStores());// 生成的token存放在数据库表oauth_access_token
        services.setTokenEnhancer(tokenEnhancerChain);
        services.setAccessTokenValiditySeconds(accessTokenValiditySeconds);//token过期时间 设置-1时，永不过期
        services.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);


        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
        endpoints
                .tokenEnhancer(tokenEnhancerChain)
                .accessTokenConverter(accessTokenConverter())
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                // 2018-4-3 增加配置，允许 GET、POST 请求获取 token，即访问端点：oauth/token
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                // 客户端详细信息服务的基本实现 这里使用JdbcClientDetailsService
                .setClientDetailsService(clientDetails);


        endpoints.reuseRefreshTokens(true);
        //oauth2登录异常处理
        endpoints.exceptionTranslator(new BootOAuth2WebResponseExceptionTranslator());
    }

    @Bean
    public JdbcTokenStores jdbcTokenStores() {
        return new JdbcTokenStores(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        //允许表单认证
        oauthServer.allowFormAuthenticationForClients();
        oauthServer.tokenKeyAccess("permitAll()")//  开启/oauth/token_key验证端口无权限访问
                .checkTokenAccess("isAuthenticated()") // 开启/oauth/check_token验证端口认证权限访问
                .allowFormAuthenticationForClients();
    }

    /**
     * @Author Pan Weilong
     * @Description jwt加密秘钥
     * @Date 17:58 2019/7/10
     **/
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    /**
     * @return org.springframework.security.oauth2.provider.token.TokenStore
     * @Author Pan Weilong
     * @Description 使用redis保存生成的token
     * @Date 14:38 2019/7/11
     * @Param []
     **/
    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        //key前缀
        tokenStore.setPrefix("pwl_");
        //new JwtTokenStore(accessTokenConverter())
        return tokenStore;
    }

    /**
     * jwt 生成token 定制化处理
     *
     * @return TokenEnhancer
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            UserVoDetail userDto = (UserVoDetail) authentication.getUserAuthentication().getPrincipal();
            final Map<String, Object> additionalInfo = new HashMap<>(1);
            additionalInfo.put("license", "pwl");
            additionalInfo.put("userId", userDto.getUserId());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            //设置token的过期时间30分钟
            Calendar nowTime = Calendar.getInstance();
            nowTime.add(Calendar.MINUTE, 30);
            ((DefaultOAuth2AccessToken) accessToken).setExpiration(nowTime.getTime());
            return accessToken;
        };
    }

}
