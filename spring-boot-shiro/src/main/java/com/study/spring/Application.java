package com.study.spring;

import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Created on 2018-01-04
 *
 * @author liuzhaoyuan
 */
@SpringBootApplication
@MapperScan("com.study.spring.mapper")
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
//        return new org.apache.tomcat.jdbc.pool.DataSource();
        return new DruidDataSource();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(this.dataSource());

        // 扫描实体包
        sqlSessionFactoryBean.setTypeAliasesPackage("com.study.spring.entity");

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println();
        LOGGER.info("============ SpringBoot Shiro Start Success ===========");
        System.out.println();
        System.out.println();
    }
}
