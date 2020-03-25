package com.haha.xixi.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author weilai
 * @data 2018年11月4日 下午5:35:30
 *
 * @desc 类描述
 *       <li> 自定义Oath
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException  extends OAuth2Exception{

	private static final long serialVersionUID = 2699852706832665841L;
	
	public CustomOauthException(String msg) {
		super(msg);
		
	}

	

}
