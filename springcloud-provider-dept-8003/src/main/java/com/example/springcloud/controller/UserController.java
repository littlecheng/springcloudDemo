package com.example.springcloud.controller;

import com.example.springcloud.pojo.User;
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

    @PostMapping(value="/insert",produces = "application/json")
    public int insert(@RequestBody User record) {
        return userService.insert(record);
    }

    @PostMapping(value="/insertSelective",produces = "application/json")
    public int insertSelective(@RequestBody  User record) {
        return userService.insertSelective(record);
    }

    @GetMapping("/select/{id}")
    public User selectByPrimaryKey(@PathVariable(name = "id") Integer id) {
        logger.info("id",id);
        return userService.selectByPrimaryKey(id);
    }

    @PutMapping(value="/updateSelective",produces = "application/json")
    public int updateByPrimaryKeySelective(@RequestBody  User record) {
        return userService.updateByPrimaryKeySelective(record);
    }

    @PutMapping(value="/updateByPrimaryKey",produces = "application/json")
    public int updateByPrimaryKey(@RequestBody  User record) {
        return userService.updateByPrimaryKey(record);
    }
}
