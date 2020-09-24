package com.learn.system.dao;


import com.learn.system.model.entity.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 获取分页记录数
     *
     * @return
     */
    Long getTotal();

    /**
     * 获取所有角色
     *
     * @return
     */
    List<Role> getAllRoles();

    /**
     * 获取所有角色
     * @param page 第几页
     * @param size 页码
     * @return
     */
    List<Role> getRoleByFilter(Integer page, Integer size);

}