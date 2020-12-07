package com.learn.system.controller.system;

import com.learn.system.model.entity.ResponseBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户管理
 * @author: PENGLW
 * @date: 2020/10/26
 */
@RestController
@RequestMapping("/system/user")
public class UserController {

    /**
     * 用户列表
     *
     * @param page
     * @param size
     * @param name
     * @return
     */
    @GetMapping("/user")
    public ResponseBean getUser(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer size, @RequestParam String name) {
        //todo 需要重新处理
        return null;
    }
}
