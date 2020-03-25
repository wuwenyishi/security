package com.haha.xixi.service.impl.impl;


import com.haha.xixi.model.SysUser;
import com.haha.xixi.repository.SysUserDao;
import com.haha.xixi.service.impl.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pwl
 * @since 2019-06-20
 */
@Service
public class SysUserServiceImpl  implements SysUserService {

    @Autowired
    SysUserDao sysUserDao;

    @Override
    public List<SysUser> selectList(Object o) {
        List<SysUser> objects = new ArrayList<>();
        SysUser user4 = sysUserDao.findByUserName("user4");
        objects.add(user4);
        return objects;
    }

    @Override
    public SysUser findByUsername(String s) {
        return sysUserDao.findByUserName(s);
    }
}
