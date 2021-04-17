package com.example.springcloud.service;

import com.example.springcloud.mapper.DeptMapper;
import com.example.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService{

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public boolean saveDept(Dept dept) {
        return deptMapper.saveDept(dept);
    }

    @Override
    public Dept selectByID(Long id) {
        return deptMapper.selectByID(id);
    }

    @Override
    public List<Dept> selectAll() {
        return deptMapper.selectAll();
    }
}
