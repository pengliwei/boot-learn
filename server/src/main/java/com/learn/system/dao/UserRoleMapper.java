package com.learn.system.dao;

import com.learn.system.model.entity.UserRole;

/**
 * @description: 用户角色dao
 * @author: PENGLW
 * @date: 2020/10/21
 */
public interface UserRoleMapper {

    /**
     * 插入记录
     *
     * @param userRole
     * @return
     */
    int insert(UserRole userRole);

}