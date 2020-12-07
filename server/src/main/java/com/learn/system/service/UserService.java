package com.learn.system.service;


import com.learn.system.model.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUserByFilter(User user);
}
