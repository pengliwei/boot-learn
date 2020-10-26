package com.learn.system.dao;

import com.learn.system.model.dto.DeptDTO;
import com.learn.system.model.entity.Dept;

import java.util.List;

/**
 * @description: 部门管理dao
 * @author: PENGLW
 * @date: 2020/10/21
 */
public interface DeptMapper {

    /**
     * 根据条件获取部门list
     *
     * @param parentId 父id
     * @return
     */
    List<DeptDTO> getDeptByFilter(String parentId);

    /**
     * 获取所有部门
     *
     * @return
     */
    List<DeptDTO> getAllDept();

    /**
     * 新增部门
     *
     * @param dept
     * @return
     */
    Integer insert(Dept dept);

    /**
     * 删除部门
     *
     * @param deptId
     * @return
     */
    Integer deleteById(String deptId);
}
