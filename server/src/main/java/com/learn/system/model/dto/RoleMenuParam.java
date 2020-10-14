package com.learn.system.model.dto;

import java.util.List;

/**
 * @description: 角色关联菜单参数实体
 * @author: PENGLW
 * @date: 2020/10/10
 */
public class RoleMenuParam {

    private String roleId;

    private List<String> menuIds;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<String> menuIds) {
        this.menuIds = menuIds;
    }
}
