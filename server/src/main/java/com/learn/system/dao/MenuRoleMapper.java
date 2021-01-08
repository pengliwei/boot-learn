package com.learn.system.dao;

import com.learn.system.model.entity.MenuRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 菜单角色dao
 * @author: PENGLW
 * @date: 2020/10/21
 */
public interface MenuRoleMapper {

    /**
     * 插入记录
     *
     * @param menuRole
     * @return
     */
    int insert(MenuRole menuRole);

    /**
     * 根据角色id获取对应的菜单menuIds
     *
     * @param roleId
     * @return
     */
    List<String> getMenuIdsByRoleId(@Param("roleId") String roleId);

    /**
     * 根据角色id删除菜单menuIds
     *
     * @param roleId
     */
    void deleteByRoleId(@Param("roleId") String roleId);

    /**
     * 批量插入记录
     *
     * @param menuRoleList
     * @return
     */
    Integer batchInsert(@Param("menuRoleList") List<MenuRole> menuRoleList);
}