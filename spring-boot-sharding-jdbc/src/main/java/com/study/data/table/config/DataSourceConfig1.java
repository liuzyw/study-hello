package com.study.data.table.config;

import com.alibaba.druid.pool.DruidDataSource;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
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
@ConfigurationProperties(prefix = DataSourceConstants.DATASOURCE_PREFIX)
@MapperScan(basePackages = DataSourceConstants.MAPPER_USER_PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory1")
public class DataSourceConfig1 {

    private String url;

    private String username;

    private String password;

    /**
     * 多数据源配置的时候注意，必须要有一个主数据源  Primary
     */
    @Bean(name = "dataSource1")
    @Primary
    public DataSource dataSource1() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(DataSourceConstants.DRIVER_CLASS);
        dataSource.setUrl(String.format(url, DataSourceConstants.DATABASE_AAA));
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        /* 配置初始化大小、最小、最大 */
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(20);

        /* 配置获取连接等待超时的时间 */
        dataSource.setMaxWait(60000);

        /* 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 */
        dataSource.setTimeBetweenEvictionRunsMillis(60000);

        /* 配置一个连接在池中最小生存的时间，单位是毫秒 */
        dataSource.setMinEvictableIdleTimeMillis(300000);

        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);

        /* 打开PSCache，并且指定每个连接上PSCache的大小。
           如果用Oracle，则把poolPreparedStatements配置为true，
           mysql可以配置为false。分库分表较多的数据库，建议配置为false */
        dataSource.setPoolPreparedStatements(false);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

        /* 配置监控统计拦截的filters */
        dataSource.setFilters("stat,wall,log4j");
        return dataSource;
    }

    @Bean(name = "transactionManager1")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource1());
    }

    @Bean(name = "sqlSessionFactory1")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Autowired @Qualifier("dataSource1") DataSource dataSource1)
        throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource1);

        sessionFactory.setTypeAliasesPackage(DataSourceConstants.ENTITY_PACKAGE);

        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
            .getResources(DataSourceConstants.MAPPER_USER_XML));
        return sessionFactory.getObject();
    }

    @Bean(name = "sqlSessionTemplate1")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlSessionFactory1") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}