package com.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {

    public static Connection getDB(){
        Connection conn = null;
        try {
            Class.forName(ReadProperties.getValue("lend.jdbc.driverClassName"));
            conn = DriverManager.getConnection(ReadProperties.getValue("lend.jdbc.url"),ReadProperties.getValue("lend.jdbc.username"),ReadProperties.getValue("lend.jdbc.password"));

        } catch (Exception e){
            e.printStackTrace();
        }
        return conn !=null ? conn: null;
    }
}
