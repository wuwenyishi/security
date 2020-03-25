package com.haha.xixi.repository;


import com.haha.xixi.model.UserBaseModel;

public interface IuserBaseManageRepo extends IbaseDao<UserBaseModel, String>{

	UserBaseModel findByUsername(String username);
    
    
    
}
