package com.learn.system.service;


import com.learn.system.model.entity.ResponseBean;
import com.learn.system.model.entity.ResponsePageBean;
import com.learn.system.model.entity.Role;

import java.util.List;


/**
 * @description: 角色管理service层
 * @author: PENGLW
 * @date: 2020/9/24
 */
public interface RoleService {

    /**
     * 获取所有角色
     *
     * @return
     */
    ResponseBean getAllRoles();

    /**
     * 获取所有角色
     *
     * @param page 第几页
     * @param size 页码
     * @param name 角色名称
     * @return
     */
    ResponsePageBean getRoleByFilter(Integer page, Integer size, String name);

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    Integer addRole(Role role);

    /**
     * 根据角色id获取菜单id
     *
     * @param roleId
     * @return
     */
    ResponseBean getMenuIdsByRoleId(String roleId);

    /**
     * 根据角色id保存菜单
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    Integer saveMenu(String roleId, List<String> menuIds);

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    Integer delRole(String roleId);
}
