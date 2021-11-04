package com.hu;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.hu.mapper.UserMapper;
import com.hu.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: hu.chen
 * @Description:
 * @DateTime: 2021/11/4 1:33 下午
 **/
public class Test {



    @org.junit.Test
    public void test01() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis/mybatis-core.xml");

        //这里使用的是mybatis-plus提供的SqlSessionFactory的实现类
        SqlSessionFactory sqlSessionFactory =  new MybatisSqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //这个调用的是继承mybatis-plus的方法
        User userById = userMapper.selectById(11);


        System.err.println(userById);

    }
}
