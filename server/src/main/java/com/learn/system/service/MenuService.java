package com.learn.system.service;


import com.learn.system.model.entity.Menu;
import com.learn.system.model.entity.ResponseBean;
import com.learn.system.model.entity.ResponsePageBean;

import java.util.List;


public interface MenuService {

    List<Menu> getMenusByUserId();

    List<Menu> getAllMenusWithRole();

    List<Integer> getMidsByRid(Integer rid);

    boolean updateMenuRole(Integer rid, Integer[] mids);

    /**
     * 查询菜单
     * @param menu
     * @return
     */
    ResponsePageBean getMenuByFilter(Menu menu);

    /**
     * 获取所有菜单
     * @return
     */
    ResponseBean getAllMenus();
}
