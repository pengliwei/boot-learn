package com.learn.system.service.impl;

import com.learn.system.dao.UserMapper;
import com.learn.system.dao.UserRoleMapper;
import com.learn.system.model.entity.User;
import com.learn.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: PENGLW
 * @date: 2020/10/26
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        user.setRoles(userMapper.getUserRolesById(user.getId()));
        return user;
    }


    @Override
    public List<User> getUserByFilter(User user) {
        return null;
    }
}
