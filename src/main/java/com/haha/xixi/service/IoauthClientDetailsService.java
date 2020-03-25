package com.haha.xixi.service;


import com.haha.xixi.model.OauthClientDetailsModel;

/**
 * @author Administrator
 * @data 2018年10月30日 上午10:50:41
 *
 * @desc 类描述
 *       <li>
 */
public interface IoauthClientDetailsService {
	/**
	 * 
	 * @author weilai
	 * @date 2018年11月13日下午7:22:13
	 * @desc
	 *       <li>
	 * @param cid
	 * @return
	 */

	OauthClientDetailsModel findByClientId(String cid);

	/**
	 * 
	 * @author weilai
	 * @desc 新增客户端
	 * @date 2018年10月30日上午11:31:31
	 * @param entity
	 */
	void addClient(OauthClientDetailsModel entity);

	/**
	 * 
	 * @author weilai
	 * @desc 修改客户端密码，只允许修改密码
	 * @date 2018年10月30日上午11:31:59
	 * @param entity
	 */
	void editClitetPwd(String Id, String pwd);

	/**
	 * 
	 * @author weilai
	 * @desc 根据clentid删除用户
	 * @date 2018年10月30日下午1:46:25
	 * @param Id
	 */
	void delClient(String Id);

}
