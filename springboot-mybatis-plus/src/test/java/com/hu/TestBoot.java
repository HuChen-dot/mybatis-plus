package com.hu;

import com.hu.mapper.UserMapper;
import com.hu.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: hu.chen
 * @Description:
 * @DateTime: 2021/11/4 3:16 下午
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestBoot {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {

        User user = userMapper.selectById(11);

        System.err.println(user);
    }

}
