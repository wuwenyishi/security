package com.haha.xixi.config;

import com.haha.xixi.exception.AuthExceptionEntryPoint;
import com.haha.xixi.exception.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author weilai
 * @desc <li>WebSecurityConfigurerAdapter是默认情况下SpringSecurity的http配置；
 * <li>ResourceServerConfigurerAdapter是默认情况下spring security oauth 的http配置。
 */

@Configuration
@EnableResourceServer // 声明为资源服务器。此注解自动增加了 OAuth2AuthenticationProcessingFilter的过滤器链，
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法级服务,支持@PreAuthorize("hasRole('Admin')")方式
public class OAuth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final TokenStore tokenStore;
    private final CustomAccessDeniedHandler customHandler;

    @Autowired
    public OAuth2ResourceServerConfiguration(TokenStore tokenStore, CustomAccessDeniedHandler customHandler) {
        this.tokenStore = tokenStore;
        this.customHandler = customHandler;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(tokenStore);
        resources.authenticationEntryPoint(CustomAuthentication()).accessDeniedHandler(customHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers(Global.PASS_ADDRESS).permitAll()
                .anyRequest().authenticated();

    }

    /**
     * @authour :weilai
     * @data :2019/5/31 14:45
     * @desc:TODO 自定义输出 401 未授权，需要token 错误
     **/
    @Bean
    public AuthenticationEntryPoint CustomAuthentication() {
        return new AuthExceptionEntryPoint();
    }
}
