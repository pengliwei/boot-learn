package com.learn.system.service;

import com.learn.system.model.entity.Dept;
import com.learn.system.model.entity.ResponseBean;

/**
 * @description: 部门service接口层
 * @author: PENGLW
 * @date: 2020/10/21
 */
public interface DeptService {

    /**
     * 获取部门
     *
     * @param parentId 父id
     * @return
     */
    ResponseBean getDeptByFilter(String parentId);

    /**
     * 获取所有部门
     *
     * @return
     */
    ResponseBean getAllDept();

    /**
     * 新增部门
     *
     * @param dept
     * @return
     */
    Integer addDept(Dept dept);

    /**
     * 删除部门
     *
     * @param deptId
     * @return
     */
    Integer delDept(String deptId);
}
