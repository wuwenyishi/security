package com.haha.xixi.service;

import com.haha.xixi.model.UserBaseExtModel;
import com.haha.xixi.model.UserBaseModel;

import java.util.List;


public interface IuserBaseExtService {

	/**
	 * 
	 * @author admin
	 * @desc 新增用户
	 * @date 2018年10月29日下午1:03:10
	 * 
	 */
	public UserBaseExtModel addUser(UserBaseExtModel user);

	/**
	 * 
	 * @author admin
	 * @desc 删除用户
	 * @date 2018年10月30日下午1:44:57
	 * @param username
	 */
	public void delUser(String username);
	/**
	 * 
	 * @author admin
	 * @desc 删除用户
	 * @date 2018年10月30日下午1:44:57
	 * @param ids
	 */
	void delUser(List<String> ids);
	/**
	 * 
	 * @author admin
	 * @date 2019年4月3日上午11:56:26
	 * @desc
	 *       <li>
	 * @param username
	 * @param pwd
	 * @return
	 */
	public UserBaseModel editPwd(String username, String pwd);

	/**
	 * 
	 * @author admin
	 * @desc 根据用户名 查找用户
	 * @date 2018年10月30日下午2:16:56
	 * @param username
	 * @return
	 */
	public UserBaseExtModel findByUsername(String username);

	void save(UserBaseExtModel newUser);

}
