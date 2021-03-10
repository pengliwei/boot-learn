package com.learn.commom.config.mysql;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @description: 决定返回哪个数据源的key
 * @author: PENGLW
 * @date: 2021/3/9
 */
public class RoutingDataSource extends AbstractRoutingDataSource {

    private DataSource masterDataSource;
    private DataSource slaveDataSource;

    public RoutingDataSource() {
    }

    public RoutingDataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        this.masterDataSource = masterDataSource;
        this.slaveDataSource = slaveDataSource;
    }

    /**
     * 返回当前线程正在使用的代表数据库的枚举对象
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {

        return DynamicDBTypeUtil.get();
    }

    /**
     * 连接失败的时候的处理方法，再次切换数据源
     *
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        try {
            return super.getConnection();
        } catch (SQLException e) {
            // 切换主从数据库
            if (DynamicDBTypeUtil.get() == DBTypeEnum.SLAVE) {
                return masterDataSource.getConnection();
            } else {
                return slaveDataSource.getConnection();
            }
        }
    }
}
