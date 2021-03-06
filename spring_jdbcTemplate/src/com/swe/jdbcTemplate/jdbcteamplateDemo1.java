package com.swe.jdbcTemplate;

import com.swe.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        jdbcTemplate.update("insert into account(name,money) values(?,?)", "fff", "3456");
        //更新
        jdbcTemplate.update("update account set money = ? where id =?", 4567, 3);
        //删除
        jdbcTemplate.update("delete from account where id =?", 5);
        //查询所有
        List<Account> accounts = jdbcTemplate.query("select * from account where money >?", new BeanPropertyRowMapper<Account>(Account.class), 1000);
        for (Account account : accounts) {
            System.out.println(account);
        }
        //查询一个
        List<Account> accounts1 = jdbcTemplate.query("select * from account where id =?", new AccountRowMapper(), 1);
        System.out.println(accounts1.isEmpty() ? "没有结果" : accounts1.get(0));
        //查询返回一行一列，聚合函数的使用
        //queryForObject是spring3.x之后的新方法，在spring2.x的时候，他的方法是多个queryForInt queryForshort
        Integer count1 = jdbcTemplate.queryForObject("select count(*) from account where money>?", Integer.class, 1000);
        Long count2 = jdbcTemplate.queryForObject("select count(*) from account where money>?", Long.class, 1000);
        System.out.println(count1);
        System.out.println(count2);


    }
}

class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setMoney(resultSet.getFloat("money"));
        account.setName(resultSet.getString("name"));
        return account;
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