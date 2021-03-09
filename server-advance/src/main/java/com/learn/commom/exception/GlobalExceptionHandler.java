package com.learn.commom.exception;

import com.learn.system.model.entity.ResponseBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @description: 共用异常类
 * @author: PENGLW
 * @date: 2020/7/14
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseBean sqlException(Exception e) {
        e.printStackTrace();
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return ResponseBean.error("该数据有关联数据，操作失败!");
        }
        return ResponseBean.error("数据库异常，操作失败!");
    }
}