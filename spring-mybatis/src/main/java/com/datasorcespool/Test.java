package com.datasorcespool;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created on 2018-06-23
 *
 * @author liuzhaoyuan
 */
public class Test {

    public static MyPool myPool = MyPoolFactory.getInstance();

    public static void main(String[] args) {

        for (int i = 0; i < 1; i++) {
            MyPoolConnection connection = myPool.getConnection();
            ResultSet resultSet = connection.query("SELECT id, name, age FROM user WHERE id = 11");
            System.out.println("-------");
            try {
                while (resultSet.next()) {
                    System.out.println("re: name " + resultSet.getString("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection.close();

        }

    }

}
