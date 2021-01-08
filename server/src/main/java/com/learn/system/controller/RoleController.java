package com.learn.system.controller;

import com.learn.system.model.dto.RoleMenuParam;
import com.learn.system.model.entity.ResponseBean;
import com.learn.system.model.entity.ResponsePageBean;
import com.learn.system.model.entity.Role;
import com.learn.system.service.MenuService;
import com.learn.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 角色管理
 * @author: PENGLW
 * @date: 2020/9/24
 */
@RestController
@RequestMapping("/system/role/")
public class RoleController {

    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;

    /**
     * 角色列表
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("getRole")
    public ResponsePageBean getAllRoles(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer size, @RequestParam String name) {

        return roleService.getRoleByFilter(page, size, name);
    }

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @PostMapping("addRole")
    public ResponseBean addRole(@RequestBody Role role) {
        if (roleService.addRole(role) == 1) {
            return ResponseBean.ok("添加成功!");
        }
        return ResponseBean.error("添加失败!");
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @DeleteMapping("delRole/{roleId}")
    public ResponseBean delRole(@PathVariable String roleId) {
        if (roleService.delRole(roleId) == 1) {
            return ResponseBean.ok("删除成功!");
        }
        return ResponseBean.error("删除失败!");
    }

    /**
     * 获取角色对应的菜单id
     *
     * @param roleId
     * @return
     */
    @GetMapping("getRoleMenu")
    public ResponseBean getRoleMenu(String roleId) {

        return roleService.getMenuIdsByRoleId(roleId);
    }

    /**
     * 保存角色关联的菜单
     *
     * @param param
     * @return
     */
    @PostMapping("/saveMenu")
    public ResponseBean saveMenu(@RequestBody RoleMenuParam param) {
        Integer i = roleService.saveMenu(param.getRoleId(), param.getMenuIds());
        if (i == 0) {
            return ResponseBean.error("保存失败!");
        }
        return ResponseBean.ok("保存成功!");
    }
}
