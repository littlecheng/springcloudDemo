package com.example.springcloud.service;

import com.example.springcloud.pojo.Dept;

import java.util.List;

public interface DeptService {

    /**
     * 保存部门
     * @param dept
     * @return
     */
    boolean saveDept(Dept dept);

    /**
     * 查询部门
     * @param id
     * @return
     */
    Dept selectByID(Long id);

    /**
     * 查询所有
     * @return
     */
    List<Dept> selectAll();
}
