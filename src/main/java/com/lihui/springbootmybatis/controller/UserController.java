package com.lihui.springbootmybatis.controller;

import com.lihui.springbootmybatis.entity.User;
import com.lihui.springbootmybatis.service.UserService;
import com.lihui.springbootmybatis.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author LiHui
 * @create 2019-06-18-14:09
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/save")
    public void saveUserHandle( User user){
    userService.save(user);
    }

    @GetMapping("/get/{id}")
    public User getUserHandle(@PathVariable("id")String id){
        return userService.getUser(id);
    }

    @GetMapping("/all")
    public List<User> getAllHandle(){
        return userService.findAllUser();
    }
}
