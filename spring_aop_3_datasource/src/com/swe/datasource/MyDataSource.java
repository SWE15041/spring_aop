package com.swe.datasource;

import com.swe.util.JDBCUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyDataSource {

    //定义一个连接池，用于存放连接
    private static List<Connection> pool = Collections.synchronizedList(new ArrayList<Connection>());

    //使用静态代码块给池中加入连接
    static {
        for (int i = 0; i < 10; i++) {
            Connection conn = JDBCUtil.getConnection();
            pool.add(conn);
        }
    }

    /**
     * 获取池中的一个连接
     * @return
     */
    public static Connection getConnection(){

        //mysql5.0.8适用，5.1.7不适用
        Connection conn = pool.remove(0);

        Connection proxyConn = (Connection) Proxy.newProxyInstance(
                conn.getClass().getClassLoader(),
                conn.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object rtValue = null;
                        //1.判断当前方法是否为close方法
                        if ("close".equals(method.getName())) {
                            //不能直接关闭,将连接放回连接池
                            pool.add(conn);
                        } else {
                            rtValue = method.invoke(conn, args);
                        }

                        return rtValue;
                    }
                });
        return proxyConn;

    }

    /**
     * 获取池中的连接数
     * @return
     */
    public static int getPoolSize(){
        return pool.size();
    }
}
