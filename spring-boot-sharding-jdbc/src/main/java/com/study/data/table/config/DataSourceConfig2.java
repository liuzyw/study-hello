package com.study.data.table.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.BindingTableRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.study.data.table.algorithm.ReceiptSingleKeyDatabaseShardingAlgorithm;
import com.study.data.table.algorithm.ReceiptSingleKeyTableShardingAlgorithm;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 数据源配置
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
@Configuration
@ConfigurationProperties(prefix = DataSourceConstants.DATASOURCE_PREFIX)
@MapperScan(basePackages = {DataSourceConstants.MAPPER_RECEIPT_PACKAGE}, sqlSessionFactoryRef = "mybatisSqlSessionFactory2")
public class DataSourceConfig2 {

    private String url;

    private String username;

    private String password;

    @Bean(name = "mybatisDataSource2")
    public DataSource getDataSource() throws SQLException {
        //设置分库映射
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        // 分库数据源
        dataSourceMap.put(DataSourceConstants.DATABASE_RECEEIPT_1, mybatisDataSource(DataSourceConstants.DATABASE_RECEEIPT_1));
        dataSourceMap.put(DataSourceConstants.DATABASE_RECEEIPT_2, mybatisDataSource(DataSourceConstants.DATABASE_RECEEIPT_2));

        //设置默认库，两个库以上时必须设置默认库。默认库的数据源名称必须是dataSourceMap的key之一
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, DataSourceConstants.DATABASE_RECEEIPT_1);

        //设置分表映射
        TableRule userTableRule = TableRule.builder("receipt")
//            .generateKeyColumn("user_id") //将user_id作为分布式主键
            .actualTables(Arrays.asList("receipt_1", "receipt_2"))
            .tableShardingStrategy(new TableShardingStrategy("receiptId", new ReceiptSingleKeyTableShardingAlgorithm()))
            .dataSourceRule(dataSourceRule)
            .build();

        //具体分库分表策略
        List<BindingTableRule> bindingTableRules = new ArrayList<>();

        ShardingRule shardingRule = ShardingRule.builder()
            .dataSourceRule(dataSourceRule)
            .tableRules(Collections.singletonList(userTableRule))
            .bindingTableRules(bindingTableRules)
            .databaseShardingStrategy(new DatabaseShardingStrategy("userId", new ReceiptSingleKeyDatabaseShardingAlgorithm()))
            .tableShardingStrategy(new TableShardingStrategy("receiptId", new ReceiptSingleKeyTableShardingAlgorithm())).build();

        DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule);

        //return new ShardingDataSource(shardingRule);
        return dataSource;
    }

    private DataSource mybatisDataSource(final String dataSourceName) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(DataSourceConstants.DRIVER_CLASS);
        dataSource.setUrl(String.format(url, dataSourceName));
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

    /**
     * Sharding-jdbc的事务支持
     *
     * @return
     */
    @Bean(name = "mybatisTransactionManager")
    public DataSourceTransactionManager mybatisTransactionManager(@Qualifier("mybatisDataSource2") DataSource dataSource) throws SQLException {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mybatisSqlSessionFactory2")
    public SqlSessionFactory mybatisSqlSessionFactory(@Qualifier("mybatisDataSource2") DataSource mybatisDataSource)
        throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mybatisDataSource);

        // 扫描实体包
        sqlSessionFactoryBean.setTypeAliasesPackage(DataSourceConstants.ENTITY_PACKAGE);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(DataSourceConstants.MAPPER_RECEIPT_XML));

        return sqlSessionFactoryBean.getObject();
    }


    @Bean(name = "sqlSessionTemplate2")
//    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("mybatisSqlSessionFactory2") SqlSessionFactory sqlSessionFactory) throws Exception {
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