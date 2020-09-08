package com.learn.system.controller.system;

import com.learn.system.model.entity.Menu;
import com.learn.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @description: 菜单控制层
 * @author: PENGLW
 * @date: 2020/9/8
 */
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/system/config/menu")
    public List<Menu> getMenusByHrId() {

        return menuService.getMenusByHrId();
    }
}
