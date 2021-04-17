package com.example.springcloud.fegin;

import com.example.springcloud.pojo.Dept;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeignError implements FeginDept{
    @Override
    public boolean saveDept(Dept dept) {
        return false;
    }

    @Override
    public Dept selectByID(Long id) {
        return null;
    }

    @Override
    public List<Dept> selectAll() {
        return null;
    }

    @Override
    public String getInfo() {
        return "维护中...";
    }
}
