package com.learn.system.dao;


import com.learn.system.model.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增角色
     *
     * @param record
     * @return
     */
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
     *
     * @param page 第几页
     * @param size 页码
     * @param name 角色名称
     * @return
     */
    List<Role> getRoleByFilter(@Param("page")Integer page, @Param("size")Integer size, @Param("name")String name);

    /**
     * 逻辑删除角色
     *
     * @param roleId
     * @return
     */
    Integer deleteById(String roleId);

    /**
     * 根据用户id获取角色
     * @param userId
     * @return
     */
    List<Role> getRoleByUserId(String userId);
}