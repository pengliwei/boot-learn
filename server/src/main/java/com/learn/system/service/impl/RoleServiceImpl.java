package com.learn.system.service.impl;

import com.learn.commom.utils.IDGenerator;
import com.learn.system.dao.MenuRoleMapper;
import com.learn.system.dao.RoleMapper;
import com.learn.system.model.entity.ResponseBean;
import com.learn.system.model.entity.ResponsePageBean;
import com.learn.system.model.entity.Role;
import com.learn.system.service.RoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 角色管理service实现层
 * @author: PENGLW
 * @date: 2020/9/24
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    MenuRoleMapper menuRoleMapper;

    @Override
    public ResponseBean getAllRoles() {
        List<Role> roleList = roleMapper.getAllRoles();
        return new ResponseBean(HttpStatus.OK.getReasonPhrase(), roleList);
    }

    @Override
    public ResponsePageBean getRoleByFilter(Integer page, Integer size) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        //获取总记录数和数据
        Long total = roleMapper.getTotal();
        List<Role> roleList = roleMapper.getRoleByFilter(page, size);
        ResponsePageBean bean = new ResponsePageBean();
        bean.setData(roleList);
        bean.setTotal(total);
        return bean;
    }

    @Override
    public Integer addRole(Role role) {
        role.setId(IDGenerator.newID());
        role.setStatus(1);
        int insert = roleMapper.insert(role);
        return insert;
    }

    @Override
    public Integer deleteRoleById(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }

    @Override
    public ResponseBean getMenuIdsByRoleId(String roleId) {
        //获取对应菜单并返回不同类型结果
        List<String> menuIds = menuRoleMapper.getMenuIdsByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(menuIds)) {
            return new ResponseBean(HttpStatus.OK.getReasonPhrase(), menuIds);
        } else {
            return new ResponseBean(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.getReasonPhrase());
        }
    }
}
