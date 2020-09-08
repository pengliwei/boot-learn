package com.learn.system.dao;

import com.learn.system.model.entity.UserRole;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    void deleteByUserid(Integer userid);

    Integer addRole(@Param("userid") Integer userid, @Param("rids") Integer[] rids);
}