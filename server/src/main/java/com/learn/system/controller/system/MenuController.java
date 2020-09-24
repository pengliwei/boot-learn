package com.learn.system.controller.system;

import com.learn.system.model.entity.Menu;
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

    @GetMapping("/system/config/menu")
    public List<Menu> getMenusByUserId() {
        return menuService.getMenusByUserId();
    }

    @GetMapping("/system/menu")
    public ResponsePageBean getEmployeeByPage(Menu menu) {

        return menuService.getMenuByFilter(menu);
    }

    @GetMapping("/system/menu1")
    public ResponsePageBean getEmployeeByPage1(Menu menu) {

        return menuService.getMenuByFilter1(menu);
    }

}
