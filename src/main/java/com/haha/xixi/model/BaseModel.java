package com.haha.xixi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: admin
 * @date: 2018/9/21 11:33
 * @desc:
 *        <li>类描述 @MappedSuperclass是JPA 特殊要求的。不加的话子类会提示：No identifier specified
 *        for entity
 * @org：Junchi Technology Department
 **/

@SuppressWarnings("serial")
@MappedSuperclass
public class BaseModel implements Serializable {


	@Id
	@GeneratedValue(generator = "IDGenerator")
	@Column(name = "id", length = 36, nullable = false)
	@GenericGenerator(name = "IDGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	protected String id;


	@Column(name = "creat_date")
	protected Date creatDate;

	@Column(name = "modify_date")
	protected Date modifyDate;

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
