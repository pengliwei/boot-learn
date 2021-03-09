package com.learn.system.model.entity;

import java.util.List;

/**
 * @description: 返回数据
 * @author: PENGLW
 * @date: 2020/8/30
 */
public class ResponsePageBean {
    private Long total;
    private List<?> data;

    public ResponsePageBean() {
    }

    public ResponsePageBean(Long total, List<?> data) {
        this.total = total;
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
