package com.learn.system.model.dto;

import com.learn.system.model.entity.Dept;

import java.util.List;

/**
 * @description: 部门实体
 * @author: PENGLW
 * @date: 2020/10/21
 */
public class DeptDTO extends Dept {

    /**
     * 是否有子节点
     */
    private Boolean hasChildren;

    private List<DeptDTO> children;

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public List<DeptDTO> getChildren() {
        return children;
    }

    public void setChildren(List<DeptDTO> children) {
        this.children = children;
    }
}
