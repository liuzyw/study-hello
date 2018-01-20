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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = DataSourceConfig2.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory2")
public class DataSourceConfig2 {


    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.study.sso.mapper.mapper2";
    static final String MAPPER_LOCATION = "classpath:mapper/mapper2/*.xml";

    /**
     * 多数据源配置的时候注意，必须要有一个主数据源  Primary
     */
    @Bean(name = "dataSource2")
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource dataSource2() {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    @Bean(name = "transactionManager2")
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(dataSource2());
    }

    @Bean(name = "sqlSessionFactory2")
    public SqlSessionFactory masterSqlSessionFactory(@Autowired @Qualifier("dataSource2") DataSource dataSource2)
        throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource2);
        sessionFactory.setTypeAliasesPackage("com.study.sso.po");
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
            .getResources(DataSourceConfig2.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}