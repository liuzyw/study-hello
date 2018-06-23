package com.datasorcespool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 2018-06-23
 *
 * @author liuzhaoyuan
 */
public class MyDefaultPool implements MyPool {

    private List<MyPoolConnection> poolConnections = new ArrayList<>();

    private ReentrantLock lock = new ReentrantLock();

    private String jdbcDriver = "com.mysql.jdbc.Driver";

    private String jdbcURL = "jdbc:mysql://localhost:3306/aaa?useUnicode=true&amp;characterEncoding=UTF-8&amp;useSSL=false";

    private String username = "liu";

    private String password = "123456";

    private int minSize = 2;

    private int maxSize = 20;

    private int step = 2;


    public MyDefaultPool() {

        init();

        try {
            Class.forName(DBConfigXML.jdbcDriver);
        } catch (Exception e) {
            e.printStackTrace();
        }

        createConnection(minSize);

    }


    public void init() {
        jdbcDriver = DBConfigXML.jdbcDriver;
        jdbcURL = DBConfigXML.jdbcURL;
        username = DBConfigXML.username;
        password = DBConfigXML.password;
        maxSize = DBConfigXML.maxSize;
        minSize = DBConfigXML.minSize;
        step = DBConfigXML.step;
    }


    @Override
    public MyPoolConnection getConnection() {

        lock.lock();

        MyPoolConnection myPoolConnection = null;

        try {

            if (poolConnections.size() < 1) {
                return myPoolConnection;
            }

            myPoolConnection = getRealConnectionFromPool();

            while (myPoolConnection == null) {
                createConnection(step);
                myPoolConnection = getRealConnectionFromPool();
            }


        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

        return myPoolConnection;
    }

    @Override
    public void createConnection(int count) {

        lock.lock();
        try {
            if (poolConnections.size() > maxSize || poolConnections.size() + count > maxSize) {
                return;
            }
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);

            MyPoolConnection myPoolConnection = new MyPoolConnection(connection, false);
            poolConnections.add(myPoolConnection);

        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }

    private MyPoolConnection getRealConnectionFromPool() throws SQLException {
        for (MyPoolConnection poolConnection : poolConnections) {
            if (!poolConnection.isBusy()) {
                if (poolConnection.getConnection().isValid(3000)) {
                    poolConnection.setBusy(true);
                    return poolConnection;
                }
            }
        }
        return null;
    }
}
