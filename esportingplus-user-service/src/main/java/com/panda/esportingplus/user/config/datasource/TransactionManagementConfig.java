package com.panda.esportingplus.user.config.datasource;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * 多数据源分布式事务配置
 *
 * @author shusong.liang
 * @version 1.0
 * @date 2020/03/25 14:35
 */
@Configuration
@EnableTransactionManagement
public class TransactionManagementConfig implements TransactionManagementConfigurer {

    @Autowired
    @Qualifier("transactionManager")
    private DataSourceTransactionManager transactionManager;


    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new ChainedTransactionManager(transactionManager);
    }
}
