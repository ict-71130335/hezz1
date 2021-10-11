package com.hezz1.test.controller;

import com.hezz1.test.model.User;
import com.hezz1.test.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    //查找user
    @GetMapping(value="/getUser", produces="text/html;charset=utf-8")
    public String getUser(String id, String name, String age) {
        return userService.getUser(id, name, age);
    }

    //增加user
    @PostMapping(value="/addUser", produces="text/html;charset=utf-8")
    public String addUser(User user) {
        return userService.addUser(user);
    }
    //删除user
    @PostMapping(value="/delUser", produces="text/html;charset=utf-8")
    public String delUser(User user) {
        return userService.addUser(user);
    }
    //修改user
    @PostMapping(value="/changeUser", produces="text/html;charset=utf-8")
    public String changeUser(User user) {
        return userService.addUser(user);
    }
}
