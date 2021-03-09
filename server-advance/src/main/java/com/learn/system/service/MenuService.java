package com.learn.system.service;


import com.learn.system.model.entity.Menu;
import com.learn.system.model.entity.ResponseBean;
import com.learn.system.model.entity.ResponsePageBean;

import java.util.List;


public interface MenuService {

    /**
     * 通过用户id获取菜单
     *
     * @return
     */
    List<Menu> getMenusByUserId();

    /**
     * 获取所有菜单和角色
     *
     * @return
     */
    List<Menu> getAllMenusWithRole();

    /**
     * 查询菜单
     *
     * @param menu
     * @return
     */
    ResponsePageBean getMenuByFilter(Menu menu);

    /**
     * 获取所有菜单
     *
     * @return
     */
    ResponseBean getAllMenus();

    /**
     * 删除菜单
     *
     * @param menuId 菜单id
     * @return
     */
    Integer delMenu(String menuId);

    /**
     * 新增菜单
     *
     * @param menu
     * @return
     */
    int addMenu(Menu menu);
}
