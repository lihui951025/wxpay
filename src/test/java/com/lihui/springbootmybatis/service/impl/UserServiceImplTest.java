package com.lihui.springbootmybatis.service.impl;

import com.lihui.springbootmybatis.entity.User;
import com.lihui.springbootmybatis.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author LiHui
 * @create 2019-06-18-14:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
@Autowired
    UserMapper userMapper;
    @Test
    public void save() {
    }

    @Test
    public void getUser() {

        User user = userMapper.getUser("1");
    }

    @Test
    public void findAllUser() {
        List<User> allUser = userMapper.findAllUser();
        System.out.println(allUser);
    }
}