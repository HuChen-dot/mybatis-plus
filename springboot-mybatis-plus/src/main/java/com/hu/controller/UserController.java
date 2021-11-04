package com.hu.controller;

import com.hu.mapper.UserMapper;
import com.hu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hu.chen
 * @Description:
 * @DateTime: 2021/11/4 1:58 下午
 **/
@RestController
@RequestMapping
public class UserController {

    @Autowired
   private UserMapper userMapper;

    @GetMapping("/test01")
    public void test01(){

        User user = userMapper.selectById(11);

        System.err.println(user);

    }
}
