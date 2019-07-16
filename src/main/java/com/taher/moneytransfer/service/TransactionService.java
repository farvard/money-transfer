package com.taher.moneytransfer.service;

import com.taher.moneytransfer.dao.TransactionDao;
import com.taher.moneytransfer.dao.TransactionDaoH2Impl;
import com.taher.moneytransfer.model.Transaction;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public class TransactionService {

    TransactionDao transactionDao = new TransactionDaoH2Impl();

    public Optional<Transaction> get(Long id) {
        return transactionDao.get(id);
    }

    public List<Transaction> getAll() {
        return transactionDao.getAll();
    }

    public Transaction save(Transaction transaction) {
        return transactionDao.save(transaction);
    }

    public List<Transaction> getAllByAccountId(Long accountId) {
        return transactionDao.getAllByAccountId(accountId);
    }

}

