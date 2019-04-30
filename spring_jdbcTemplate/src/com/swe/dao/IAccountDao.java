package com.swe.dao;

import com.swe.domain.Account;

public interface IAccountDao {

    /**
     * 根据Id查询账户
     * @param accountId
     * @return
     */
    Account findByAccountById(Integer accountId);

    /**
     * 根据名称查询账户信息
     * @param name
     * @return
     */
    Account findAccountByName(String name);

    /**
     * 更新账户信息
     * @param account
     */
    void updateAccount(Account account);
}
