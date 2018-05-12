package com.study.data.table.config;

public abstract class DataSourceConstants {

    /**
     * 数据源配置前缀
     */
    public static final String DATASOURCE_PREFIX = "mybatis.datasource";

    /**
     * MySQL驱动
     */
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    /**
     * Mybatis Mapper接口路径
     */
    public static final String MAPPER_RECEIPT_PACKAGE = "com.study.data.table.mapper.receipt";

    public static final String MAPPER_RECEIPT_XML = "classpath:/mapper/receipt/*.xml";


    public static final String DATABASE_RECEEIPT_1 = "receipt_1";

    public static final String DATABASE_RECEEIPT_2 = "receipt_2";


    public static final String MAPPER_USER_PACKAGE = "com.study.data.table.mapper.user";

    public static final String MAPPER_USER_XML = "classpath:/mapper/user/*.xml";

    public static final String DATABASE_AAA = "aaa";


    public static final String ENTITY_PACKAGE = "com.study.data.table.entity";


}
