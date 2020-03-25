package com.haha.xixi.utils;

import com.haha.xixi.config.Global;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * @author weilai
 * @data 2019年4月3日 下午3:13:15
 *
 * @desc 类描述
 *       <li>
 */
public class BcryptUtil {

	
	private static final BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
	
	public static String getBcryptEncoder(String pwd) {

		String pwdEncode = bcryptEncoder.encode(pwd);

		String pwdFormat = StringUtils.join(Global.PASSWORD_TYPE_PREFIX, pwdEncode);

		return pwdFormat;
	}
}
