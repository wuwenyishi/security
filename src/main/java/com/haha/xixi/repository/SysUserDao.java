package com.haha.xixi.repository;

import com.haha.xixi.model.SysUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xuemd
 * @Date 2020/3/24
 * @description:
 **/
@Repository
public interface SysUserDao extends CrudRepository<SysUser, Long> {
    SysUser findByUserName(String s);
}
