package com.haha.xixi.model;


import com.haha.xixi.json.UserJson;
import com.haha.xixi.utils.BcryptUtil;

import javax.persistence.*;
import java.util.Date;

/**
 * @author admin
 * @data 2018年11月11日 下午4:55:33
 *
 * @desc 类描述
 *       <li>用户信息扩展表
 */
@Entity
@Table(name = "cbm_mag_user_ext")
public class UserBaseExtModel extends BaseModel {

	private static final long serialVersionUID = 6087621917977786675L;



	private String realName;// 中文名称

	private String nickname;// 昵称

	private String mobile;// 手机

	private String email;// 邮箱

	private Integer type = 0;// 用户类型

	private Integer status = 0;// 账户状态
	
	/**
	 * @desc UserBaseModel.class 在本数据表中对应字段user_id，在UserInfoModel实体中对应userId
	 */
	@JoinColumn(name = "user_id")
	@OneToOne(targetEntity = UserBaseModel.class, cascade = CascadeType.ALL)
	private UserBaseModel baseuser;
	
	

	public UserBaseExtModel() {
		super();

	}

	public UserBaseExtModel(UserJson json) {
		this.status = json.getStatus();
		this.nickname = json.getNickname();
		this.realName = json.getRealName();
		this.mobile = json.getMobile();
		this.email = json.getEmail();
		this.creatDate = this.modifyDate = new Date();
		this.type = json.getType();
		this.baseuser = new UserBaseModel(json.getUsername(), BcryptUtil.getBcryptEncoder(json.getPassword()),this);
	}

	public UserBaseExtModel build(UserJson json, UserBaseExtModel user) {
		user.status = json.getStatus();
		this.nickname = json.getNickname();
		this.realName = json.getRealName();
		this.mobile = json.getMobile();
		this.email = json.getEmail();
		return user;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public UserBaseModel getBaseuser() {
		return baseuser;
	}

	public void setBaseuser(UserBaseModel baseuser) {
		this.baseuser = baseuser;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
