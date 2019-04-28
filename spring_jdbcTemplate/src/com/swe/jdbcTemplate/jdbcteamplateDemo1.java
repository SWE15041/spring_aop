package com.swe.jdbcTemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * jdbcTemplate 的最基本用法
 */
public class jdbcteamplateDemo1 {
    public static void main(String[] args) {
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        //2.根据id获取bean对象
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ac.getBean("jdbcTemplate");
       //3.执行操作
//        String sql = "insert into account (name, money) values('ddd',1000)";
//        jdbcTemplate.execute(sql);
        //保存
        jdbcTemplate.update("insert into account(name,money) values(?,?)","fff","3456");
        //更新
        jdbcTemplate.update("update account set money = ? where id =?",4567,3)
        //删除
        jdbcTemplate.update("delete from account where id =?",5);
        //查询所有
        jdbcTemplate.query(" ");
        //查询一个

        //查询返回一行一列，聚合函数的使用



    }
}

//    //定义数据源
//    DriverManagerDataSource driver = new DriverManagerDataSource();
//        driver.setDriverClassName("com.mysql.jdbc.Driver");
//                driver.setUrl("jdbc:mysql://localhost:3306/spring_learn");
//                driver.setUsername("root");
//                driver.setPassword("970131");
//                //1. 获取对象
//                JdbcTemplate jdbcTemplate = new JdbcTemplate(driver);
//                //2. 执行操作
//                String sql = "insert into account (name, money) values('ccc',1000)";
//                jdbcTemplate.execute(sql);