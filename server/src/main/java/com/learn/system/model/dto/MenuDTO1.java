package com.learn.system.model.dto;

/**
 * @description:
 * @author: PENGLW
 * @date: 2020/9/17
 */
public class MenuDTO1 {

    private Integer id;


    private String name;

    private Integer parentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
