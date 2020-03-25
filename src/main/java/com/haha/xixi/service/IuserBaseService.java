package com.haha.xixi.service;

import com.haha.xixi.model.UserBaseModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IuserBaseService extends  UserDetailsService{

	/**
	 * 
	 * @author weilai
	 * @desc 新增用户
	 * @date 2018年10月29日下午1:03:10
	 * 
	 */
	UserBaseModel addUser(UserBaseModel user);

	/**
	 * 
	 * @author weilai
	 * @desc 修改用户信息
	 * @date 2018年10月30日上午11:30:45
	 * 
	 */
	UserBaseModel editPwd(String username, String pwd);

	/**
	 * 
	 * @author weilai
	 * @desc 删除用户
	 * @date 2018年10月30日下午1:44:57
	 * @param username
	 */
	void delUser(String username);
}
