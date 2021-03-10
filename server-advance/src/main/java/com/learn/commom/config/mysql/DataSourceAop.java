package com.learn.commom.config.mysql;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @description: AOP配置类
 * @author: PENGLW
 * @date: 2021/3/9
 */
@Aspect
@Component
public class DataSourceAop {

    /**
     * 只要加了@Read注解的方法就是一个切入点
     */
    @Pointcut("@annotation(com.learn.commom.config.mysql.Read)")
    public void readPointcut() {
    }

    /**
     * 只要加了@write注解的方法就是一个切入点
     */
    @Pointcut("@annotation(com.learn.commom.config.mysql.Write)")
    public void writePointcut() {
    }


    /**
     * 配置前置通知,如果是readPoint就切换数据源为从数据库
     */
    @Before("readPointcut()")
    public void readAdvice() {
        DynamicDBTypeUtil.slave();
    }

    /**
     * 配置前置通知，如果是writePoint就切换数据源为主数据库
     */
    @Before("writePointcut()")
    public void writeAdvice() {
        DynamicDBTypeUtil.master();
    }
}
