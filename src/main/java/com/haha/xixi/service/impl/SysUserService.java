package com.haha.xixi.service.impl;


import com.haha.xixi.model.SysUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pwl
 * @since 2019-06-20
 */
public interface SysUserService {

    List<SysUser> selectList(Object o);

    SysUser findByUsername(String s);
}
