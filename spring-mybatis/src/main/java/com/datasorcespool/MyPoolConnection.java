package com.datasorcespool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created on 2018-06-23
 * <p>代表数据库操作管道，它可以执行SQL，关闭管道等。</>
 *
 * @author liuzhaoyuan
 */
public class MyPoolConnection {

    private Connection connection;

    private boolean isBusy = false;

    public MyPoolConnection(Connection connection, boolean isBusy) {
        this.connection = connection;
        this.isBusy = isBusy;
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public void close() {
        this.isBusy = false;
    }


    public ResultSet query(String sql) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultSet;
    }
}
