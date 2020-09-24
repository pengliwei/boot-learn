package com.learn.system.controller.system;

import com.learn.system.model.entity.ResponseBean;
import com.learn.system.model.entity.ResponsePageBean;
import com.learn.system.service.MenuService;
import com.learn.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 角色管理
 * @author: PENGLW
 * @date: 2020/9/24
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;

    @GetMapping("/getRoleList")
    public ResponsePageBean getAllRoles(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {

        return roleService.getRoleByFilter(page,size);
    }


}
