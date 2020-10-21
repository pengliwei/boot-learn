package com.learn.system.dao;


import com.learn.system.model.dto.MenuDTO;
import com.learn.system.model.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Integer> getMidsByRid(Integer rid);

    /**
     * 根据用户查询菜单id
     * @param userid
     * @return
     */
    List<Menu> getMenusByUserId(Integer userid);

    /**
     * 获取当前角色下的菜单
     * @return
     */
    List<Menu> getAllMenusWithRole();

    /**
     * 获取所有菜单
     * @return
     */
    List<MenuDTO> getAllMenus();

    /**
     * 获取分页记录数
     *
     * @param menu
     * @return
     */
    Long getTotal(@Param("menu") Menu menu);

    /**
     * 分页条件查询菜单
     *
     * @param page
     * @param size
     * @param menu
     * @return
     */
    List<MenuDTO> getMenuByFilter(@Param("page")Integer page, @Param("size")Integer size, @Param("menu")Menu menu);

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    Integer delMenu(String menuId);
}