package com.haha.xixi.oath;

import com.haha.xixi.model.SysUser;
import com.haha.xixi.service.impl.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Pan Weilong
 * @date 2019/7/9 15:57
 * @description: 接口.
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.findByUsername(s);
        if(sysUser==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return new UserVoDetail(sysUser.getUserId(),sysUser.getUserName(),sysUser.getPassword());
    }
}
