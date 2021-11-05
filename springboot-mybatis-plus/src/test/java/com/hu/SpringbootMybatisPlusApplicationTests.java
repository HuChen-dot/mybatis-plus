package com.hu;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hu.mapper.UserMapper;
import com.hu.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

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


    @Test
    void insert() {
        User user=new User();
        user.setUserName01("plus插入01");
        user.setUserPassword("123456");
        user.setUserPhone("124354354");

        int insert = userMapper.insert(user);

        System.err.println(user);
    }


    @Test
    void update() {
        User user=new User();
        user.setId(1456165740095774727L);
        user.setUserName01("plus插入01update");

        userMapper.updateById(user);

        System.err.println(user);
    }

    @Test
    void update01() {
        //在这里构建where条件
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
        updateWrapper.eq("user_name", "plus插入01update");
        //这里是修改的值
        User user=new User();
        user.setUserPassword("123");
        user.setUserName01("条件修改");

        userMapper.update(user,updateWrapper);

        System.err.println(user);
    }
    @Test
    public void delete(){

        userMapper.deleteBatchIds(Arrays.asList(1456165740095774722L,1456165740095774723L,1456165740095774724L,1456165740095774725L));
    }

    @Test
    public void select01(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("user_password", "123456");
        List<User> users = userMapper.selectList(queryWrapper);

        for (User user : users) {
            System.err.println(user);
        }
    }

    @Test
    public void pageselect01(){
        //构建where条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("user_password", "123456");

        IPage<User> page=new Page();
        //当前页
        page.setCurrent(2);
        //每页条数
        page.setSize(3);


        IPage<User> userIPage = userMapper.selectPage(page, queryWrapper);
        List<User> users = userIPage.getRecords();
        for (User user : users) {
            System.err.println(user);
        }
    }
}
