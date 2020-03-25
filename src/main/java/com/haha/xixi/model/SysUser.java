package com.haha.xixi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author pwl
 * @since 2019-06-20
 */
@Entity
public class SysUser implements Serializable {

    private static final long serialVersionUID = -5645787431563312264L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userId;
    private String userName;
    private String fullName;
    private String password;
    private String salt;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "SysUser{" +
        ", userId=" + userId +
        ", userName=" + userName +
        ", fullName=" + fullName +
        ", password=" + password +
        ", salt=" + salt +
        "}";
    }
}
