package com.learn.system.controller.system;

import com.learn.system.model.entity.Menu;
import com.learn.system.model.entity.ResponseBean;
import com.learn.system.model.entity.ResponsePageBean;
import com.learn.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 菜单控制层
 * @author: PENGLW
 * @date: 2020/9/8
 */
@RestController
public class MenuController {

    @Autowired
    MenuService menuService;

    /**
     * 根据用户获取菜单
     *
     * @return
     */
    @GetMapping("/system/config/menu")
    public List<Menu> getMenusByUserId() {
        return menuService.getMenusByUserId();
    }

    /**
     * 获取菜单
     *
     * @param menu
     * @return
     */
    @GetMapping("/system/menu")
    public ResponsePageBean getEmployeeByPage(Menu menu) {

        return menuService.getMenuByFilter(menu);
    }

    /**
     * 获取所有菜单
     *
     * @return
     */
    @GetMapping("/system/getAllMenu")
    public ResponseBean getAllMenu() {

        return menuService.getAllMenus();
    }

}
