package com.haha.xixi.repository;


import com.haha.xixi.model.OauthClientDetailsModel;

/**
 * @author Administrator
 * @data 2018年10月30日 上午10:48:27
 *
 * @desc 类描述
 *       <li>
 */

public interface IoauthClientDetailsRepo  extends IbaseDao<OauthClientDetailsModel, String> {

	OauthClientDetailsModel findByClientId(String cid);

}
