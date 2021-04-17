package com.example.springcloud.mapper;

import com.example.springcloud.pojo.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {

    boolean saveDept(@Param("dept") Dept dept);

    Dept selectByID(Long id);

    List<Dept> selectAll();

}
