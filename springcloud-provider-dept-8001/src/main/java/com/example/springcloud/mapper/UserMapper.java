package com.example.springcloud.mapper;

import com.example.springcloud.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("user") User record);

    int insertSelective(@Param("user") User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(@Param("user") User record);

    int updateByPrimaryKey(@Param("user") User record);
}