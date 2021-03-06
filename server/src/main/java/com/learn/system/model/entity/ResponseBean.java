package com.learn.system.model.entity;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @description: 返回对象
 * @author: PENGLW
 * @date: 2020/8/30
 */
public class ResponseBean implements Serializable {

    private Integer status;

    private String msg;

    private Object data;

    public static ResponseBean build() {
        return new ResponseBean();
    }

    public static ResponseBean ok(String msg) {
        return new ResponseBean(200, msg, null);
    }

    public static ResponseBean ok(String msg, Object data) {
        return new ResponseBean(200, msg, data);
    }

    public static ResponseBean error(String msg) {
        return new ResponseBean(500, msg, null);
    }

    public static ResponseBean error(String msg, Object data) {
        return new ResponseBean(500, msg, data);
    }

    public ResponseBean() {
    }

    /**
     * 输出正常有值内容的类型
     *
     * @param data
     */
    public ResponseBean(Object data) {
        this.status = HttpStatus.OK.value();
        this.msg = HttpStatus.OK.getReasonPhrase();
        this.data = data;
    }

    /**
     * 输出正常有值内容的类型
     *
     * @param msg
     * @param data
     */
    public ResponseBean(String msg, Object data) {
        this.status = 200;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 输出无值返回结果的类型
     *
     * @param status
     * @param msg
     */
    public ResponseBean(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseBean(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
