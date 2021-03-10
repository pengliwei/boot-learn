package com.learn.commom.config.mysql;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 数据源配置类
 * @author: PENGLW
 * @date: 2021/3/9
 */
@Configuration
public class DataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    /**
     * 将创建的master数据源存入spring容器中
     *
     * @return
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource() {
        logger.info("master  数据库");
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 将创建的slave数据源存入spring容器中
     *
     * @return
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource slaveDataSource() {
        logger.info("slave  数据库");
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public DataSource targetDataSource(
            @Qualifier("masterDataSource") DataSource masterDataSource,
            @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        // 存放主从数据源
        Map<Object, Object> targetDataSource = new HashMap<>(2);
        targetDataSource.put(DBTypeEnum.MASTER, masterDataSource);
        targetDataSource.put(DBTypeEnum.SLAVE, slaveDataSource);

        // 创建对象 实现动态切换数据源
        RoutingDataSource routingDataSource = new RoutingDataSource();
        // 设置默认数据源
        routingDataSource.setDefaultTargetDataSource(masterDataSource);
//        DynamicDBTypeUtil.master();
        // 绑定所有数据源
        routingDataSource.setTargetDataSources(targetDataSource);

        return routingDataSource;


    }


}
