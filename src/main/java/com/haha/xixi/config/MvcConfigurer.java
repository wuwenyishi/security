package com.haha.xixi.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author admin
 * @data 2019年2月14日 上午10:39:42
 *
 * @desc 类描述
 *       <li>springboot2 跨域解决方案
 *       <li>前端html中ajax 中要带有下列代码,目的是解决跨域session不一致问题：
 *       <li>xhrFields: { withCredentials: true }
 *       <li>如果项目中使用springSecurity，则还需要在OAuth2ResourceServerConfiguration增加http.cors()配置
 *       <li>
 *       <li>如果项目中使用springGate网关，则此配置应该去掉否则前端浏览器会报：有多个Access-Control-Allow-Origin等
 */
//@Configuration// 项目中使用网关springGate了，所以注释掉
public class MvcConfigurer implements WebMvcConfigurer {
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*").allowCredentials(true).maxAge(3600);
	}
}
