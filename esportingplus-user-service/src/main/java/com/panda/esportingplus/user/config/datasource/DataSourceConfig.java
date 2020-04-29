package com.panda.esportingplus.user.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * user 数据源配置
 *
 * @author shusong.liang
 * @version 1.0
 * @date 2020/03/25 18:04
 */
@Configuration
@MapperScan(basePackages = DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {


    /**
     * java repository 目录
     */
    static final String PACKAGE = "com.panda.esportingplus.user.data.repository";
    static final String MAPPER_LOCATION = "classpath:META-INF/mappers/*.xml";
    private Logger logger = LoggerFactory.getLogger(getClass());



    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource dataSource() {
        logger.info("start init dataSource");
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
            throws Exception {
        logger.info("start init sqlSessionFactory");
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        logger.info("start init transactionManager");
        return new DataSourceTransactionManager(dataSource);
    }

}
