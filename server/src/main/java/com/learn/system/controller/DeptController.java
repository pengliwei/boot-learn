package com.learn.system.controller;

import com.learn.system.model.entity.Dept;
import com.learn.system.model.entity.ResponseBean;
import com.learn.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 部门管理控制层
 * @author: PENGLW
 * @date: 2020/10/21
 */
@RestController
@RequestMapping("system/dept/")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 分级获取部门
     *
     * @param parentId
     * @return
     */
    @GetMapping("dept")
    public ResponseBean getDept(@RequestParam String parentId) {
        return deptService.getDeptByFilter(parentId);
    }

    /**
     * 获取所有部门
     *
     * @return
     */
    @GetMapping("getAllDept")
    public ResponseBean getAllDept() {
        return deptService.getAllDept();
    }

    /**
     * 新增部门
     *
     * @param dept
     * @return
     */
    @PostMapping("addDept")
    public ResponseBean addDept(@RequestBody Dept dept) {
        if (deptService.addDept(dept) == 1) {
            return ResponseBean.ok("保存成功！");
        }
        return ResponseBean.error("保存失败！");
    }

    /**
     * 删除部门
     *
     * @param deptId
     * @return
     */
    @DeleteMapping("delDept/{deptId}")
    public ResponseBean delDept(@PathVariable String deptId) {
        if (deptService.delDept(deptId) == 1) {
            return ResponseBean.ok("删除成功！");
        }
        return ResponseBean.error("删除失败！");
    }

}
