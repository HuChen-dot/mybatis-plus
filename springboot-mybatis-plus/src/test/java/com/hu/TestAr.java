package com.hu;

import com.hu.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: hu.chen
 * @Description:
 * @DateTime: 2021/11/5 11:05 上午
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestAr {


    @Test
    public void test01(){

        User user = new User();

        user.setId(11L);
        user.getUserName01();

       User user1= user.selectById();

//        user.updateById()

        System.err.println(user);

    }
}
