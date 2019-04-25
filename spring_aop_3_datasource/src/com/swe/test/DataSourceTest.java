package com.swe.test;

import com.swe.datasource.MyDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceTest {
    public static void main(String[] args) {
        int poolSize = MyDataSource.getPoolSize();
        System.out.println("使用连接池之前的大小："+poolSize);
        try {
            Connection conn = MyDataSource.getConnection();
            System.out.println(conn);
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int poolSize2 = MyDataSource.getPoolSize();
        System.out.println("使用连接池之后的大小："+poolSize2);
    }

}
