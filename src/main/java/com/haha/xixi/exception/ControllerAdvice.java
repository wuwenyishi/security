package com.haha.xixi.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @data 2018年12月19日 下午1:34:35
 *
 * @desc 类描述
 *       <li>
 */
@org.springframework.web.bind.annotation.ControllerAdvice(basePackages = "com.leesky.ezframework.resource")
public class ControllerAdvice {

	/**
	 * Exception类异常，反正异常返回统一格式的map
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public Map<String, Object> exceptionHandler(Exception ex) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 1001);
		map.put("msg", ex.getMessage());
		return map;
	}

	/**
	 * @desc 自定义异常 MyException.class
	 * @desc
	 *       <li>用法 throw new
	 *       MyException("1001","empty","/API/getUserName","在获取用户名字的时候为空");
	 */
	@ResponseBody
	@ExceptionHandler(value = MyException.class)
	public Map<String, Object> myExceptionHandler(MyException myex) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", myex.getCode());
		map.put("message", myex.getMessage());
		map.put("method", myex.getMethod());
		map.put("descinfo", myex.getDescinfo());

		return map;
	}

	/**
	 * @desc 自定义异常 MyException.class
	 * @desc
	 *       <li>用法 throw new
	 *       MyException("1001","empty","/API/getUserName","在获取用户名字的时候为空");
	 */
	@ResponseBody
	@ExceptionHandler(value = AccessDeniedException.class)
	public Map<String, Object> accessDeniedExceptionHandler(AccessDeniedException myex) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 401);
		map.put("message", myex.getMessage());


		return map;
	}

	
	
}
