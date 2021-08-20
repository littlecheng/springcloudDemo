package com.example.springcloud.service;

import com.example.springcloud.entity.UserEn;
import com.example.springcloud.mapper.UserEnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserEnMapper userEnMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userEnMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserEn record) {
        return userEnMapper.insert(record);
    }

    @Override
    public int insertSelective(UserEn record) {
        return userEnMapper.insertSelective(record);
    }

    @Override
    public UserEn selectByPrimaryKey(Integer id) {
        return userEnMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserEn selectByUserName(String username){
        return userEnMapper.selectByUserName(username);
    }

    @Override
    public int updateByPrimaryKeySelective(UserEn record) {
        return userEnMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserEn record) {
        return userEnMapper.updateByPrimaryKey(record);
    }
}
