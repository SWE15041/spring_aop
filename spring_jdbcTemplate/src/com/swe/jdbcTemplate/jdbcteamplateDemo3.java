package com.swe.jdbcTemplate;

import com.swe.dao.IAccountDao;
import com.swe.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * jdbcTemplate 的最基本用法
 */
public class jdbcteamplateDemo3 {
    public static void main(String[] args) {
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        //2.根据id获取bean对象
        IAccountDao accountDao = (IAccountDao) ac.getBean("accountDao2");

        Account account = accountDao.findByAccountById(1);
        System.out.println(account);


    }
}
