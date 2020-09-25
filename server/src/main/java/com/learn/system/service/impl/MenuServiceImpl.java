package com.learn.system.service.impl;

import com.learn.system.dao.MenuMapper;
import com.learn.system.dao.MenuRoleMapper;
import com.learn.system.model.dto.MenuDTO1;
import com.learn.system.model.entity.Menu;
import com.learn.system.model.entity.ResponsePageBean;
import com.learn.system.model.entity.User;
import com.learn.system.model.dto.MenuDTO;
import com.learn.system.service.MenuService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: PENGLW
 * @date: 2020/9/16
 */
@Service
//@CacheConfig(cacheNames = "menus_cache")
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;
    @Autowired
    MenuRoleMapper menuRoleMapper;


    @Override
    public List<Menu> getMenusByUserId() {
        return menuMapper.getMenusByUserId(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

    @Override
    public List<Menu> getAllMenusWithRole() {

        return menuMapper.getAllMenusWithRole();
    }

    @Override
    public List<Menu> getAllMenus() {

        return menuMapper.getAllMenus();
    }

    @Override
    public List<Integer> getMidsByRid(Integer rid) {

        return menuMapper.getMidsByRid(rid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.deleteByRid(rid);
        if (mids == null || mids.length == 0) {
            return true;
        }
        Integer result = menuRoleMapper.insertRecord(rid, mids);
        return result == mids.length;
    }

    @Override
    public ResponsePageBean getMenuByFilter(Menu menu) {
        //设置初始菜单
        String parentId = menu.getParentId();
        if (!StringUtils.isNotBlank(parentId)) {
            menu.setParentId("1");
        }
        Long total = menuMapper.getTotal(menu);
        //设置当前菜单是否有子节点
        List<MenuDTO> menuVOList = menuMapper.getMenuByFilter(null, null, menu);
        if (CollectionUtils.isNotEmpty(menuVOList)) {
            menuVOList.forEach(menuDTO -> {
                if (menuDTO.getIsLeaf() == 0) {
                    menuDTO.setHasChildren(true);
                }
            });
        }
        ResponsePageBean bean = new ResponsePageBean();
        bean.setData(menuVOList);
        bean.setTotal(total);
        return bean;
    }

    @Override
    public ResponsePageBean getMenuByFilter1(Menu menu) {
        //设置初始菜单
        String parentId = menu.getParentId();
        if (StringUtils.isNotBlank(parentId)) {
            menu.setParentId("1");
        }
        Long total = menuMapper.getTotal(menu);
        //设置当前菜单是否有子节点
        List<MenuDTO> menuVOList = menuMapper.getMenuByFilter(null, null, menu);
        if (CollectionUtils.isNotEmpty(menuVOList)) {
            menuVOList.forEach(menuDTO -> {
                if (menuDTO.getIsLeaf() == 0) {
                    menuDTO.setHasChildren(true);
                }
            });
        }
        List<MenuDTO1> menuDTO1List = new ArrayList(){};
        for (MenuDTO menuDTO : menuVOList){
            MenuDTO1 menuDTO1 = new MenuDTO1();
            menuDTO1.setId(menuDTO.getId());
            menuDTO1.setName(menuDTO.getName());
            menuDTO1.setParentId(menuDTO.getParentId());
            menuDTO1List.add(menuDTO1);
        }

        ResponsePageBean bean = new ResponsePageBean();
        bean.setData(menuDTO1List);
        bean.setTotal(total);
        return bean;
    }
}
