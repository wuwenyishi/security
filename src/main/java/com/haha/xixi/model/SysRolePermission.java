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
public class SysRolePermission implements Serializable {


    private static final long serialVersionUID = 8252486601009358074L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long roleId;
    private Long permissionId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "SysRolePermission{" +
        ", roleId=" + roleId +
        ", permissionId=" + permissionId +
        "}";
    }
}
