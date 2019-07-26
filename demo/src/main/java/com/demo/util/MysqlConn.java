package com.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConn {

    public static Connection conn = null;

    public static Connection getFinupSqlConn() {

        String url = ReadProperties.getValue("finup_lend_url");
        String password = ReadProperties.getValue("password");
        String username = ReadProperties.getValue("username");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static Connection getLendAppConn(){

        String url = ReadProperties.getValue("lend_app_url");
        String password = ReadProperties.getValue("password");
        String username = ReadProperties.getValue("username");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
