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
public class SysPermission implements Serializable {

    private static final long serialVersionUID = -6395475341663634579L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long parentId;
    private String resName;
    private String resType;
    private String permission;
    private String url;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
        ", id=" + id +
        ", parentId=" + parentId +
        ", resName=" + resName +
        ", resType=" + resType +
        ", permission=" + permission +
        ", url=" + url +
        "}";
    }
}
