package com.taher.moneytransfer.service;

import com.taher.moneytransfer.dao.AccountDao;
import com.taher.moneytransfer.dao.AccountDaoH2Impl;
import com.taher.moneytransfer.exception.RecordNotFoundException;
import com.taher.moneytransfer.model.Account;

import java.util.List;

/**
 *
 */
public class AccountService {

    private AccountDao accountDao = new AccountDaoH2Impl();

    public Account get(Long id) throws RecordNotFoundException {
        return accountDao.get(id);
    }

    public List<Account> getAll() {
        return accountDao.getAll();
    }

    public Account save(Account account) {
        return accountDao.save(account);
    }

}

    