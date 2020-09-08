package com.learn.system.dao;

import com.learn.system.model.entity.User;
import com.learn.system.model.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User loadUserByUsername(String username);

    List<Role> getUserRolesById(Integer id);

    List<User> getAllUsers(@Param("userid") Integer userid, @Param("keywords") String keywords);

    List<User> getAllUsersExceptCurrentUser(Integer id);

    Integer updatePasswd(@Param("userid") Integer userid, @Param("encodePass") String encodePass);

    Integer updateUserface(@Param("url") String url, @Param("id") Integer id);
}