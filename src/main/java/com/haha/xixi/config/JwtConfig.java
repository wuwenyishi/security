package com.haha.xixi.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * 
 * @author admin
 * @data 2018年10月29日 下午6:47:35
 *
 * @desc 类描述
 *       <li>读取资源服务中resource目录下的 leesky.crt 文件
 *       <li>leesky.crt 是默认文件，请勿改变
 *       <li>
 *       <li>  注入JwtAccessTokenConverter
 */
@Configuration
public class JwtConfig {

	@Bean
	@Qualifier("tokenStore")
	public TokenStore tokenStore() throws IOException {
		return new JwtTokenStore(jwtTokenEnhancer());
	}

	@Bean
	protected JwtAccessTokenConverter jwtTokenEnhancer() throws IOException {
		Resource resource = new ClassPathResource("leesky.crt");
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		String publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
		converter.setVerifierKey(publicKey);
		return converter;
	}
}
