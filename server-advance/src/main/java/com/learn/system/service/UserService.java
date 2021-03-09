package com.learn.system.service;


import com.learn.system.model.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 获取用户
     *
     * @param user
     * @return
     */
    List<User> getUserByFilter(User user);
}
