package com.example.springcloud.controller;

import com.example.springcloud.entity.UserEn;
import com.example.springcloud.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/deleteByID/{id}")
    public int deleteByPrimaryKey(@PathVariable Integer id) {
        return userService.deleteByPrimaryKey(id);
    }

    @PostMapping(value="/signUp")
    public int insert(UserEn user) {
        return userService.insert(user);
    }

    @PostMapping(value="/insertSelective",produces = "application/json")
    public int insertSelective(@RequestBody  UserEn record) {
        return userService.insertSelective(record);
    }

    @GetMapping("/select/{id}")
    public UserEn selectByPrimaryKey(@PathVariable(name = "id") Integer id) {
        logger.info("id",id);
        return userService.selectByPrimaryKey(id);
    }

    @PutMapping(value="/updateSelective",produces = "application/json")
    public int updateByPrimaryKeySelective(@RequestBody  UserEn record) {
        return userService.updateByPrimaryKeySelective(record);
    }

    @PutMapping(value="/updateByPrimaryKey",produces = "application/json")
    public int updateByPrimaryKey(@RequestBody  UserEn record) {
        return userService.updateByPrimaryKey(record);
    }
}
