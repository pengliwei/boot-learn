package com.learn.commom.config.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: 动态切换数据源工具类
 * @author: PENGLW
 * @date: 2021/3/9
 */
public class DynamicDBTypeUtil {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDBTypeUtil.class);

    /**
     * 存储数据源对象
     * master: 代表当前线程使用的是主数据库
     * slave: 代表当前线程使用的是从数据库
     */
    public static final ThreadLocal<DBTypeEnum> CONTEXT_HAND = ThreadLocal.withInitial(() -> DBTypeEnum.MASTER);


    /**
     * 切换当前线程要是用的数据源
     *
     * @param dbType
     */
    public static void set(DBTypeEnum dbType) {
        CONTEXT_HAND.set(dbType);
        logger.info("当前数据源是：" + dbType);
    }

    /**
     * 切换到主数据库
     */
    public static void master() {
        CONTEXT_HAND.set(DBTypeEnum.MASTER);
        logger.info("切换到master数据源");
    }

    /**
     * 切换到从数据库
     */
    public static void slave() {
        set(DBTypeEnum.SLAVE);
        logger.info("切换到slave数据源");
    }

    /**
     * 移除当前线程使用的数据源
     */
    public static void remove() {
        CONTEXT_HAND.remove();
    }


    /**
     * 获取当前线程使用的枚举值
     *
     * @return
     */
    public static DBTypeEnum get() {
        return CONTEXT_HAND.get();
    }


}
