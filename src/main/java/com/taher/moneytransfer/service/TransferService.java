package com.taher.moneytransfer.service;

import com.taher.moneytransfer.dao.AccountDao;
import com.taher.moneytransfer.dao.AccountDaoH2Impl;
import com.taher.moneytransfer.dao.TransactionDao;
import com.taher.moneytransfer.dao.TransactionDaoH2Impl;
import com.taher.moneytransfer.exception.NotEnoughMoneyException;
import com.taher.moneytransfer.exception.RecordNotFoundException;
import com.taher.moneytransfer.lock.AccountLock;
import com.taher.moneytransfer.lock.SingleNodeInMemoryAccountLock;
import com.taher.moneytransfer.model.Account;
import com.taher.moneytransfer.model.Transfer;

/**
 *
 */
public class TransferService {

    private static final long TIMEOUT_MILLIS = 2000;
    private AccountDao accountDao = new AccountDaoH2Impl();
    private TransactionDao transactionDao = new TransactionDaoH2Impl();
    private AccountLock lock = new SingleNodeInMemoryAccountLock();

    public void transfer(Transfer transfer)
          throws InterruptedException, NotEnoughMoneyException, RecordNotFoundException {
        Long srcAccountId = transfer.getSrcAccountId();
        Long dstAccountId = transfer.getDstAccountId();
        lock(srcAccountId, dstAccountId);
        transfer(srcAccountId, dstAccountId, transfer.getAmount());
        transactionDao.save(transfer.createTransaction());
        unlock(srcAccountId, dstAccountId);
    }

    private void transfer(Long src, Long dst, long amount) throws NotEnoughMoneyException, RecordNotFoundException {
        Account s = accountDao.get(src);
        if (s.getBalance() < amount) {
            throw new NotEnoughMoneyException();
        }
        accountDao.withdrawal(src, amount);
        accountDao.deposit(dst, amount);
    }

    private void lock(Long src, Long dst) throws InterruptedException {
        lock.lock(src, TIMEOUT_MILLIS);
        lock.lock(dst, TIMEOUT_MILLIS);
    }

    private void unlock(Long src, Long dst) throws InterruptedException {
        lock.unlock(dst);
        lock.unlock(src);
    }

}

    