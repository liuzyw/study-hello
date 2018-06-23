package com.datasorcespool;

/**
 * Created on 2018-06-23
 * <p>
 * <p>对外提供数据库连接池的基本服务，比如得到一个数据库操作管道</>
 *
 * @author liuzhaoyuan
 */
public interface MyPool {

    MyPoolConnection getConnection();

    void createConnection(int count);
}
