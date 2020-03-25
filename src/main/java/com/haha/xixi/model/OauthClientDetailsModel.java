package com.haha.xixi.model;

import com.haha.xixi.utils.EncrypMD5Util;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * @author admin
 * @data 2018年10月30日 上午10:33:21
 *
 * @desc 类描述
 *       <li>客户端详情表
 */
@Entity
@Table(name = "oauth_client_details")
public class OauthClientDetailsModel implements Serializable {

	private static final long serialVersionUID = -6399970297950884142L;

	@Id
	@Column(name = "client_id")
	private String clientId;

	@Column(name = "client_secret")
	private String clientSecret;

	private String scope;

	@Column(name = "authorized_grant_types")
	private String authorizedGrantTypes;

	@Column(name = "creat_date")
	private Date creatDate = new Date();

	@Column(name = "modify_date")
	private Date modifyDate = new Date();

	@Column(name = "client_id_original")
	private String original;// 未加密的clentid

	/**
	 * 
	 */
	public OauthClientDetailsModel() {

	}

	/**
	 * @param clientId
	 * @param clientSecret
	 */
	public OauthClientDetailsModel(String clientId, String clientSecret) {
		super();
		this.clientId = EncrypMD5Util.eccrypt(clientId);
		this.original = clientId;
		this.clientSecret = clientSecret;
		this.scope = "read,write";
		this.authorizedGrantTypes = "password,refresh_token";

	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the clientSecret
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * @param clientSecret the clientSecret to set
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * @return the authorizedGrantTypes
	 */
	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	/**
	 * @param authorizedGrantTypes the authorizedGrantTypes to set
	 */
	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}
