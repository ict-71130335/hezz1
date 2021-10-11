package com.hezz1.test.service;

import com.hezz1.test.model.User;

public interface UserService {
    String addUser(User User);
    String getUser(String id,String name, String age);

    String deluser(String id, String name, String age);

    String changeUser(String id, String name, String age);

    String deleUser(String id, String name, String age);
}
