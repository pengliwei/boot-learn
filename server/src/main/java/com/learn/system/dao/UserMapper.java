package com.learn.system.dao;

import com.learn.system.model.entity.Role;
import com.learn.system.model.entity.User;

import java.util.List;

/**
 * @description: 用户管理dao
 * @author: PENGLW
 * @date: 2020/10/21
 */
public interface UserMapper {

    /**
     * 判断用户是否存在
     * @param username
     * @return
     */
    User loadUserByUsername(String username);

    /**
     * 获取用户角色
     * @param id
     * @return
     */
    List<Role> getUserRolesById(Integer id);

//    Integer updateUserface(@Param("url") String url, @Param("id") Integer id);
}