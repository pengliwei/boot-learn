package com.learn.system.service;


import com.learn.system.model.entity.Menu;
import com.learn.system.model.entity.ResponseBean;
import com.learn.system.model.entity.ResponsePageBean;
import com.learn.system.model.entity.Role;
import org.springframework.web.bind.annotation.RequestParam;

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
     * @return
     */
    ResponsePageBean getRoleByFilter(Integer page, Integer size);


    Integer addRole(Role role);

    public Integer deleteRoleById(Integer rid);
}
