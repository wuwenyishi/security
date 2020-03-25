package com.haha.xixi.service.impl;

import com.haha.xixi.model.OauthClientDetailsModel;
import com.haha.xixi.repository.IoauthClientDetailsRepo;
import com.haha.xixi.service.IoauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author Administrator
 * @data 2018年10月30日 上午10:51:14
 *
 * @desc 类描述
 *       <li>
 */
@Service
public class OauthClientDetailsServiceImpl  implements IoauthClientDetailsService {

	@Autowired
	private IoauthClientDetailsRepo repo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.leesky.ezframework.uaa.service.IoauthClientDetailsService#addClient(com.
	 * leesky.ezframework.uaa.model.OauthClientDetailsModel)
	 */
	@Override
	public void addClient(OauthClientDetailsModel entity) {
		this.repo.save(entity);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.leesky.ezframework.uaa.service.IoauthClientDetailsService#editClitet(com.
	 * leesky.ezframework.uaa.model.OauthClientDetailsModel)
	 */
	@Override
	public void editClitetPwd(String Id, String pwd) {

		OauthClientDetailsModel model = this.repo.findByClientId(Id);

		model.setModifyDate(new Date());
		model.setClientSecret(pwd);
		this.repo.save(model);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.leesky.ezframework.uaa.service.IoauthClientDetailsService#delClient(java.
	 * lang.String)
	 */
	@Override
	public void delClient(String Id) {
		OauthClientDetailsModel entity = this.repo.findById(Id).get();
		this.repo.delete(entity);

	}

	@Override
	public OauthClientDetailsModel findByClientId(String cid) {

		return this.repo.findByClientId(cid);
	}
}
