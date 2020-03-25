package com.haha.xixi.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @data 2018年11月21日 下午6:25:49
 *
 * @desc 类描述
 *       <li>
 */
public class Global {
	// spring security中使用BCryptPasswordEncoder方法加密,得到密码字符串在最前面增加{bcrypt}标志，存放到数据表中
	// 登陆时进行密码比对,需要删除{bcrypt}
	public static final String PASSWORD_TYPE_PREFIX = "{bcrypt}";

	public final static List<String> notAuthPathList;
	static {
		notAuthPathList = new ArrayList();
		notAuthPathList.add("/");
		notAuthPathList.add("/login/white/**");
	}
	public static final String[] PASS_ADDRESS = notAuthPathList.toArray(new String[]{});
}
