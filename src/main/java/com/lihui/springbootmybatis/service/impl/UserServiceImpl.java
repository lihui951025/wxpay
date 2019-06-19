package com.lihui.springbootmybatis.service.impl;

import com.lihui.springbootmybatis.entity.User;
import com.lihui.springbootmybatis.mapper.UserMapper;
import com.lihui.springbootmybatis.service.UserService;
import com.lihui.springbootmybatis.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author LiHui
 * @create 2019-06-18-13:57
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public void save(User user) {
        user.setId(WXPayUtil.generateUUID());
        user.setCreateTime(new Date());
        userMapper.save(user);

    }

    @Override
    public User getUser(String id) {

        return  userMapper.getUser(id);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }
}
