package com.example.springcloud.service;

import com.example.springcloud.entity.UserEn;

public interface UserService {
    int deleteByPrimaryKey(Integer id);

    int insert(UserEn record);

    int insertSelective(UserEn record);

    UserEn selectByPrimaryKey(Integer id);

    UserEn selectByUserName(String username);

    int updateByPrimaryKeySelective(UserEn record);

    int updateByPrimaryKey(UserEn record);
}
