package com.example.springcloud.controller;

import com.example.springcloud.pojo.User;
import com.example.springcloud.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation("删除用户")
    @RequestMapping("/deleteByID/{id}")
    public int deleteByPrimaryKey(@PathVariable Integer id) {
        return userService.deleteByPrimaryKey(id);
    }

    @ApiOperation("保存用户1")
    @PostMapping(value="/insert",produces = "application/json")
    public int insert(@RequestBody User record) {
        return userService.insert(record);
    }

    @ApiOperation("保存用户")
    @PostMapping(value="/insertSelective",produces = "application/json")
    public int insertSelective(@RequestBody  User record) {
        return userService.insertSelective(record);
    }

    @ApiOperation("查询用户")
    @GetMapping("/select/{id}")
    public User selectByPrimaryKey(@PathVariable(name = "id") Integer id) {
        logger.info("id",id);
        return userService.selectByPrimaryKey(id);
    }
    @ApiOperation("更新")
    @PutMapping(value="/updateSelective",produces = "application/json")
    public int updateByPrimaryKeySelective(@RequestBody  User record) {
        return userService.updateByPrimaryKeySelective(record);
    }

    @ApiOperation("更新用户")
    @PutMapping(value="/updateByPrimaryKey",produces = "application/json")
    public int updateByPrimaryKey(@RequestBody  User record) {
        return userService.updateByPrimaryKey(record);
    }
}
