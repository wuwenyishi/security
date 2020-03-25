package com.haha.xixi.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;

/**
 * @author weilai
 * @data 2019年4月3日 下午2:50:20
 *
 * @desc 类描述
 *       <li>
 */
@JsonInclude(value = Include.NON_NULL)
public class UserJson implements Serializable{

	private static final long serialVersionUID = 6497091873740773602L;

	protected String id;
	
	protected String username;// 登录名

	protected String password;// 密码
	
	protected String realName;// 中文名称

	protected String nickname;// 昵称
	
	protected String mobile;// 手机
	
	protected String email;// 邮箱

	protected Integer status;// 账户状态 0=正常,1=停用,3=

	protected Integer type;// 用户类型
	
	protected Object post;// 岗位id，岗位名称

	protected Object depart;// 部门id，部门名称

	protected Object roles;// 拥有的角色
	
	protected Object menus;//可访问的菜单项，对应后台页面左侧的 菜单
	
	protected Object treeMenu;
	
	protected Object resourceForm;//具体到页面内表单元素控制资源
	
	protected Object resourceUrl;//具体到页面内 ajax的url等  颗粒度较细的控制资源
	
	
	
	/**
	 * 
	 */
	public UserJson() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param username
	 */
	public UserJson(String username,String id) {
		super();
		this.id = id;
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Object getPost() {
		return post;
	}

	public void setPost(Object post) {
		this.post = post;
	}

	public Object getDepart() {
		return depart;
	}

	public void setDepart(Object depart) {
		this.depart = depart;
	}

	public Object getRoles() {
		return roles;
	}

	public void setRoles(Object roles) {
		this.roles = roles;
	}

	public Object getMenus() {
		return menus;
	}

	public void setMenus(Object menus) {
		this.menus = menus;
	}

	public Object getResourceForm() {
		return resourceForm;
	}

	public void setResourceForm(Object resourceForm) {
		this.resourceForm = resourceForm;
	}

	public Object getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(Object resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public Object getTreeMenu() {
		return treeMenu;
	}

	public void setTreeMenu(Object treeMenu) {
		this.treeMenu = treeMenu;
	}

	@Override
	public String toString() {
		return "UserJson{" +
				"id='" + id + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", realName='" + realName + '\'' +
				", nickname='" + nickname + '\'' +
				", mobile='" + mobile + '\'' +
				", email='" + email + '\'' +
				", status=" + status +
				", type=" + type +
				", post=" + post +
				", depart=" + depart +
				", roles=" + roles +
				", menus=" + menus +
				", treeMenu=" + treeMenu +
				", resourceForm=" + resourceForm +
				", resourceUrl=" + resourceUrl +
				'}';
	}
}
