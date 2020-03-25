package com.haha.xixi.config;

import com.haha.xixi.exception.CustomWebResponseExceptionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.util.Arrays;


/**
 * @author xuemd
 * @Date 2020/3/25
 * @description: 认证服务器 认证相关的配置Oauth2AuthorizationServerConfig
 **/
@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${access.token.validity:360}") // 默认值过期时间360
    private int accessTokenValiditySeconds;

    @Value("${access.refresh.validity:420}") // 默认值7分钟
    private int refreshTokenValiditySeconds;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomWebResponseExceptionTranslator customException;

    @Autowired
    private AuthenticationManager authenticationManager;//如果要使用密码授权模式 就要用到这个

    /**
     * @desc 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，
     * @desc 你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     * @desc 允许的客户端用户名和密码 参见数据表oauth_client_details
     * @desc 注意client_secret字段存储内容方式, 密码前增加:{bcrypt}
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }


    /**
     * @Auther: weilai
     * @Date: 2018/10/28 17:24
     * @Description: <li>1、配置tokenStore</li>
     * <li>2、声明加密方式使用AuthenticationManager</li>
     * <li>3、用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。</li>
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//        // 将增强的token设置到增强链中
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtTokenConverter(), customTokenEnhancer()));

        // 配置TokenServices参数
        DefaultTokenServices services = new DefaultTokenServices();
        services.setSupportRefreshToken(false);// refresh_token存放到数据表oauth_refresh_token
        services.setTokenStore(jdbcTokenStores());// 生成的token存放在数据库表oauth_access_token
        services.setTokenEnhancer(tokenEnhancerChain);
        services.setAccessTokenValiditySeconds(accessTokenValiditySeconds);//token过期时间 设置-1时，永不过期
        services.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);

        endpoints
                .tokenServices(services)
                .exceptionTranslator(customException)
                .authenticationManager(authenticationManager);
    }


    @Bean
    protected JwtAccessTokenConverter jwtTokenConverter() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("leesky.jks"), "pwd123".toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("keyPair"));
        return converter;
    }

    @Bean
    public JwtEnhance customTokenEnhancer() {
        return new JwtEnhance();
    }
    @Bean
    public JdbcTokenStores jdbcTokenStores() {
        return new JdbcTokenStores(dataSource);
    }
    /**
     * @authour :weilai
     * @data :2019/5/29 13:06
     * @desc://授权端点开放
     **/
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")//  开启/oauth/token_key验证端口无权限访问
                .checkTokenAccess("isAuthenticated()") // 开启/oauth/check_token验证端口认证权限访问
                .allowFormAuthenticationForClients();
    }

}

