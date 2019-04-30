package com.swe.dao.impl;

import com.swe.dao.IAccountDao;
import com.swe.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 账户的业务层实现类
 *
 * @author lyn
 */
public class AccountDaoImpl implements IAccountDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account findByAccountById(Integer accountId) {
        List<Account> list = jdbcTemplate.query("select * from account where id =?", new BeanPropertyRowMapper<Account>(Account.class), accountId);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Account findAccountByName(String name) {
        List<Account> list = jdbcTemplate.query("select * from account where name =?", new BeanPropertyRowMapper<Account>(Account.class), name);
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() > 1) {
            throw new RuntimeException("账户名不唯一");
        }
        return list.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        jdbcTemplate.update("update account set name =?,money= ? where id =?", account.getName(), account.getMoney(), account.getId());
    }


}

