package com.haha.xixi.service.impl;


import com.haha.xixi.model.UserBaseModel;
import com.haha.xixi.repository.IuserBaseManageRepo;
import com.haha.xixi.service.IuserBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@SuppressWarnings("unused")
public class UserBaseServiceImpl  implements IuserBaseService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final IuserBaseManageRepo repo;

    @Autowired
    public UserBaseServiceImpl(IuserBaseManageRepo repo) {
        this.repo = repo;
    }

    /**
     * @Author: weilai
     * @Date: 2018/9/21 12:46
     * @parms: <li>
     * @desc 新增用户
     */
    @Override
    public UserBaseModel addUser(UserBaseModel user) {
        return repo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.repo.findByUsername(s);
    }

    @Override
    public UserBaseModel editPwd(String username, String pwd) {
        UserBaseModel model = repo.findByUsername(username);
        model.setPassword(pwd);
        return repo.save(model);
    }

    @Override
    public void delUser(String username) {
        UserBaseModel user = this.repo.findByUsername(username);
        this.repo.delete(user);
    }


}
