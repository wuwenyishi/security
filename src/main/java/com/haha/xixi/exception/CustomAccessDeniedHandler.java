package com.haha.xixi.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author weilai
 * @data 2018年11月4日 下午6:05:51
 *
 * @desc 类描述
 *       <li>只有确实的访问失败才会进入AccessDeniedHandler，如果是未登陆或者会话超时等，
 *       <li>不会触发AccessDeniedHandler，而是会直接跳转到登陆页面或报告：401错误。本系统跳转到/error/401
 * 
 *       <li>400错误：
 *       <li>在ajax请求后台数据时有时会报 HTTP 400 错误 - 请求无效 (Bad request);出现这个请求无效报错说明请求没有进入到后台服务里；
 * 
 *       <li>原因：1）前端提交数据的字段名称或者是字段类型和后台的实体类不一致，导致无法封装；
 * 
 *       <li>2）前端提交的到后台的数据应该是json字符串类型，而前端没有将对象转化为字符串类型；
 * 
 *       <li>解决方案：
 * 
 *       <li>1）对照字段名称，类型保证一致性
 * 
 *       <li>2）使用stringify将前端传递的对象转化为字符串 data: JSON.stringify(param) ;
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
		response.setHeader("message", accessDeniedException.getMessage());
		response.setHeader("path", request.getServletPath());
		response.setHeader("status", String.valueOf(HttpServletResponse.SC_BAD_REQUEST));
		response.sendRedirect("/error/400");//400
	}

}
