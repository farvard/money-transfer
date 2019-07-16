package com.taher.moneytransfer.service;

import com.taher.moneytransfer.dao.AccountDao;
import com.taher.moneytransfer.dao.AccountDaoH2Impl;
import com.taher.moneytransfer.model.Account;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public class AccountService {

    private AccountDao accountDao = new AccountDaoH2Impl();

    public Optional<Account> get(Long id) {
        return accountDao.get(id);
    }

    public List<Account> getAll() {
        return accountDao.getAll();
    }

    public Account save(Account account) {
        return accountDao.save(account);
    }

    public void update(Account account) {
        accountDao.update(account);
    }

}

    