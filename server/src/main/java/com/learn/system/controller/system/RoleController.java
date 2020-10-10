package com.learn.system.controller.system;

import com.learn.system.model.entity.ResponseBean;
import com.learn.system.model.entity.ResponsePageBean;
import com.learn.system.model.entity.Role;
import com.learn.system.service.MenuService;
import com.learn.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 角色列表
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getRoleList")
    public ResponsePageBean getAllRoles(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {

        return roleService.getRoleByFilter(page, size);
    }

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @PostMapping("/addRole")
    public ResponseBean addRole(@RequestBody Role role) {
        if (roleService.addRole(role) == 1) {
            return ResponseBean.ok("添加成功!");
        }
        return ResponseBean.error("添加失败!");
    }

    /**
     * 获取角色对应的菜单id
     *
     * @param roleId
     * @return
     */
    @GetMapping("/getRoleMenu")
    public ResponseBean getRoleMenu(String roleId) {

        return roleService.getMenuIdsByRoleId(roleId);
    }

    /**
     * 保存角色关联的菜单
     *
     * @param menuIds
     * @return
     */
    @PostMapping("/saveMenu")
    public ResponseBean saveMenu(@RequestBody List<String> menuIds) {
        System.out.println(menuIds);

        return ResponseBean.error("添加失败!");
    }


}
