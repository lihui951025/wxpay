package com.lihui.springbootmybatis.service;

import com.lihui.springbootmybatis.entity.User;

import java.util.List;

/**
 * @author LiHui
 * @create 2019-06-18-13:56
 */
public interface UserService {
    void save(User user);

    User getUser(String id);

    List<User> findAllUser();
}
