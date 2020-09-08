package com.learn.system.service;


import com.learn.system.dao.UserMapper;
import com.learn.system.dao.UserRoleMapper;
import com.learn.system.model.entity.User;
import com.learn.system.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
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

    public List<User> getAllUsers(String keywords) {
        return userMapper.getAllUsers(UserUtils.getCurrentUser().getId(), keywords);
    }

    public Integer updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional
    public boolean updateUserRole(Integer userid, Integer[] rids) {
        userRoleMapper.deleteByUserid(userid);
        return userRoleMapper.addRole(userid, rids) == rids.length;
    }

    public Integer deleteUserById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    public List<User> getAllUsersExceptCurrentUser() {
        return userMapper.getAllUsersExceptCurrentUser(UserUtils.getCurrentUser().getId());
    }

    public Integer updateUserById(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public boolean updateUserPasswd(String oldpass, String pass, Integer userid) {
        User user = userMapper.selectByPrimaryKey(userid);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldpass, user.getPassword())) {
            String encodePass = encoder.encode(pass);
            Integer result = userMapper.updatePasswd(userid, encodePass);
            if (result == 1) {
                return true;
            }
        }
        return false;
    }

    public Integer updateUserface(String url, Integer id) {
        return userMapper.updateUserface(url, id);
    }
}
