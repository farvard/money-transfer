package com.taher.moneytransfer.service;

import com.taher.moneytransfer.dao.AccountDao;
import com.taher.moneytransfer.dao.AccountDaoH2Impl;
import com.taher.moneytransfer.dao.TransactionDao;
import com.taher.moneytransfer.dao.TransactionDaoH2Impl;
import com.taher.moneytransfer.model.Transfer;

/**
 *
 */
public class TransferService {

    private AccountDao accountDao = new AccountDaoH2Impl();
    private TransactionDao transactionDao = new TransactionDaoH2Impl();

    public void transfer(Transfer transfer) {
        //TODO
    }

}

    