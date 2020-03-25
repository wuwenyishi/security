package com.haha.xixi.exception;

/**
 * @author admin
 * @data 2018年12月19日 下午2:29:11
 *
 * @desc 类描述
 *       <li>自定义异常类型
 *       <li> 用法
 */
public class MyException extends RuntimeException {
	
	private static final long serialVersionUID = 3588545316030398432L;
	
	private String code; // 异常状态码
	private String message; // 异常信息
	private String method; // 发生的方法，位置等
	private String descinfo; // 描述

	public MyException(String code, String message, String method, String descinfo) {
		this.code = code;
		this.message = message;
		this.method = method;
		this.descinfo = descinfo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDescinfo() {
		return descinfo;
	}

	public void setDescinfo(String descinfo) {
		this.descinfo = descinfo;
	}

}
