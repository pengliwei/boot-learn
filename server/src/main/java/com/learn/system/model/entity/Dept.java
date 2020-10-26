package com.learn.system.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 部门实体
 * @author: PENGLW
 * @date: 2020/10/21
 */
public class Dept implements Serializable {

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 是否是子节点：1-是；0-否
     */
    private Integer isLeaf;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 状态：1-正常；0-删除
     */
    private Integer status;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
