package com.readwite.application.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 决定返回哪个数据源的key
 */
public class RouttingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        /**
         * 返回当前线程正在使用的代表数据库的枚举对象
         * AbstractRoutingDataSource 该方法中持有 DataSourceConfig 配置的所有datasource 以及 默认的 datasource
         * 并且调用该方法的返回值，然后去获取一个 datasource 然后返回
         * 这里返回什么值，是在AOP 中实现切换的
         * AOP 是根据注解去实现
         */
        return DynamicSwitchDBTypeUtil.get();
    }
}
