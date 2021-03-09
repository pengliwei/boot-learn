package com.learn.system.service.impl;

import com.learn.commom.utils.IDGenerator;
import com.learn.system.constant.SysContant;
import com.learn.system.dao.MenuMapper;
import com.learn.system.dao.MenuRoleMapper;
import com.learn.system.model.dto.MenuDTO;
import com.learn.system.model.entity.Menu;
import com.learn.system.model.entity.ResponseBean;
import com.learn.system.model.entity.ResponsePageBean;
import com.learn.system.model.entity.User;
import com.learn.system.service.MenuService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: PENGLW
 * @date: 2020/9/16
 */
@Service
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
    public Integer delMenu(String menuId) {

        return menuMapper.delMenu(menuId);
    }

    @Override
    public int addMenu(Menu menu) {
        menu.setId(IDGenerator.newID());
        menu.setEnabled(true);
        if ("1".equals(menu.getParentId())) {
            menu.setIsLeaf(0);
        } else {
            menu.setIsLeaf(1);
        }
        return menuMapper.insert(menu);
    }

    @Override
    public ResponseBean getAllMenus() {
        //获取所有菜单
        List<MenuDTO> allMenus = menuMapper.getAllMenus();
        List<MenuDTO> rootMenus = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(allMenus)) {
            //获取根节点菜单
            allMenus.forEach(menu -> {
                if (SysContant.MENU_PARENTID.equals(menu.getParentId())) {
                    rootMenus.add(menu);
                }
            });
            //为根菜单设置子菜单，getChild是递归调用
            for (MenuDTO menu : rootMenus) {
                //获取当前节点下的子节点
                List<MenuDTO> childList = getChild(menu.getId(), allMenus);
                menu.setChildren(childList);
            }
        }
        return new ResponseBean(rootMenus);
    }

    /**
     * 获取子节点
     *
     * @param menuId   父节点菜单id
     * @param allMenus 所有菜单list
     * @return 当前节点下的所有菜单
     */
    public List<MenuDTO> getChild(String menuId, List<MenuDTO> allMenus) {
        List<MenuDTO> childList = new ArrayList<>();
        //遍历所有节点,获取该节点的子节点
        allMenus.forEach(menu -> {
            if (menuId.equals(menu.getParentId())) {
                childList.add(menu);
            }
        });
        // 递归调用
        if (CollectionUtils.isNotEmpty(childList)) {
            for (MenuDTO menuDTO : childList) {
                menuDTO.setChildren(getChild(menuDTO.getId(), allMenus));
            }
        }
        return childList;
    }
}
