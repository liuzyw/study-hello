package com.study.sso.config;

import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = DataSourceConfig1.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory1")
public class DataSourceConfig1 {


    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.study.sso.mapper.mapper1";
    static final String MAPPER_LOCATION = "classpath:mapper/mapper1/*.xml";

    /**
     * 多数据源配置的时候注意，必须要有一个主数据源  Primary
     */
    @Bean(name = "dataSource1")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource1")
    public DataSource dataSource1() {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    @Bean(name = "transactionManager1")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(dataSource1());
    }

    @Bean(name = "sqlSessionFactory1")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Autowired @Qualifier("dataSource1") DataSource dataSource1)
        throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource1);

        sessionFactory.setTypeAliasesPackage("com.study.sso.po");

        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
            .getResources(DataSourceConfig1.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}