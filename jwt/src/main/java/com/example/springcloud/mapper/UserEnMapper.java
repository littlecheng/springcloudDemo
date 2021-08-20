package com.example.springcloud.mapper;

import com.example.springcloud.entity.UserEn;
import org.apache.ibatis.annotations.Param;

public interface UserEnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("user") UserEn record);

    int insertSelective(@Param("user") UserEn record);

    UserEn selectByPrimaryKey(Integer id);

    UserEn selectByUserName(String username);

    int updateByPrimaryKeySelective(@Param("user") UserEn record);

    int updateByPrimaryKey(@Param("user") UserEn record);
}
