package com.learn.system.dao;

import com.learn.system.model.entity.MenuRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuRole record);

    int insertSelective(MenuRole record);

    MenuRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuRole record);

    int updateByPrimaryKey(MenuRole record);

    void deleteByRid(Integer rid);

    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);

    /**
     * 根据角色id获取对应的菜单menuIds
     * @param roleId
     * @return
     */
    List<String> getMenuIdsByRoleId(@Param("roleId") String roleId);
}