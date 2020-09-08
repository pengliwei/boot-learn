package com.learn.system.dao;


import com.learn.system.model.entity.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> getMenusByUserId(Integer userid);

    List<Menu> getAllMenusWithRole();

    List<Menu> getAllMenus();

    List<Integer> getMidsByRid(Integer rid);
}