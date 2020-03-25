package com.haha.xixi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author admin
 * @data 2019年4月24日 下午8:12:23
 *
 * @desc 类描述
 *       <li>
 */
@Entity
@Table(name = "cbm_mag_l_user_role")
public class UserRoleModel extends BaseModel {

	private static final long serialVersionUID = -5937121298947354949L;

	@Column(name = "role_id", length = 32)
	private String roleId;

	@Column(name = "user_id", length = 32)
	private String userId;

	public UserRoleModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRoleModel(String roleId, String userId) {
		super();
		this.roleId = roleId;
		this.userId = userId;
		this.creatDate = this.modifyDate = new Date();
	}

	public String getRoleId() {
		return roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
