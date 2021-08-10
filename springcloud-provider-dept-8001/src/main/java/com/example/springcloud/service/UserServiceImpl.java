package com.example.springcloud.service;

import com.example.springcloud.mapper.LogMapper;
import com.example.springcloud.mapper.UserMapper;
import com.example.springcloud.pojo.Dept;
import com.example.springcloud.pojo.LogMess;
import com.example.springcloud.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;


    @Autowired
    LogService logService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int insert(User record) {

        logService.addLog( new LogMess(new Date(),"hello"));
        return userMapper.insert(record);

       // int i = 1/0;//这种情况异常,不会把数据插入到数据库

       /*
        这种情况会把数据插入到数据库
        try {
            int i = 1/0;
        }catch (ArithmeticException E){

        }*/
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }
}
