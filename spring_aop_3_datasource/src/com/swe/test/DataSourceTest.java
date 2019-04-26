package com.swe.test;

import com.swe.datasource.MyDataSource;

import java.sql.Connection;

public class DataSourceTest {
    public static void main(String[] args) throws Exception {
        int poolSize = MyDataSource.getPoolSize();
        System.out.println("使用连接池之前的大小：" + poolSize);
        for (int i = 0; i < 10; i++) {
            Connection conn = MyDataSource.getConnection();
            System.out.println(conn);
            conn.close();
        }
        int poolSize2 = MyDataSource.getPoolSize();
        System.out.println("使用连接池之后的大小：" + poolSize2);
        for (int i = 0; i < 10; i++) {
            Connection conn = MyDataSource.getConnection();
            System.out.println(conn);
            conn.close();
        }
    }

}
