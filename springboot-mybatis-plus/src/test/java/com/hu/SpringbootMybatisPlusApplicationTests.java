package com.hu;

import com.hu.mapper.UserMapper;
import com.hu.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringbootMybatisPlusApplicationTests {


    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {

        User user = userMapper.selectById(11);

        System.err.println(user);
    }

}
