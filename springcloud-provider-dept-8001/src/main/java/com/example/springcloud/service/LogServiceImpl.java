package com.example.springcloud.service;

import com.example.springcloud.mapper.LogMapper;
import com.example.springcloud.pojo.LogMess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogServiceImpl implements LogService{

    @Autowired
    private LogMapper logDao;

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void addLog(LogMess log) {
            logDao.addLog(log);
            //@Transactional(propagation = Propagation.NESTED	)  嵌套事务异常，父事务会回滚
            /*
            //抓住异常会导致插入数据。不抓住会回滚。
            try {
                int res = 1/0;
            }catch (Exception e){

            }*/
    }
}
