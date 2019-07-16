package com.taher.moneytransfer.service;

import com.taher.moneytransfer.dao.AccountDao;
import com.taher.moneytransfer.dao.AccountDaoH2Impl;
import com.taher.moneytransfer.dao.TransactionDao;
import com.taher.moneytransfer.dao.TransactionDaoH2Impl;
import com.taher.moneytransfer.exception.NotEnoughMoneyException;
import com.taher.moneytransfer.lock.AccountLock;
import com.taher.moneytransfer.lock.SingleNodeInMemoryAccountLock;
import com.taher.moneytransfer.model.Account;
import com.taher.moneytransfer.model.Transfer;

/**
 *
 */
public class TransferService {

    private AccountDao accountDao = new AccountDaoH2Impl();
    private TransactionDao transactionDao = new TransactionDaoH2Impl();
    private AccountLock lock = new SingleNodeInMemoryAccountLock();
    private long timeoutMillis = 2000;

    public void transfer(Transfer transfer) throws InterruptedException, NotEnoughMoneyException {
        Long srcAccountId = transfer.getSrcAccountId();
        Long dstAccountId = transfer.getDstAccountId();
        lock(srcAccountId, dstAccountId);
        transfer(srcAccountId, dstAccountId, transfer.getAmount());
        transactionDao.save(transfer.createTransaction());
        unlock(srcAccountId, dstAccountId);
    }

    private void transfer(Long src, Long dst, long amount) throws NotEnoughMoneyException {
        Account s = accountDao.get(src).get();
        Account d = accountDao.get(dst).get();
        if (s.getBalance() < amount) {
            throw new NotEnoughMoneyException();
        }
        s.setBalance(s.getBalance() - amount);
        d.setBalance(d.getBalance() + amount);
        accountDao.update(s);
        accountDao.update(d);
    }

    private void lock(Long src, Long dst) throws InterruptedException {
        lock.lock(src, timeoutMillis);
        lock.lock(dst, timeoutMillis);
    }

    private void unlock(Long src, Long dst) throws InterruptedException {
        lock.unlock(src);
        lock.unlock(dst);
    }

}

    