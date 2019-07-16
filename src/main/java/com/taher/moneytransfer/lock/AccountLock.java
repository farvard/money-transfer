package com.taher.moneytransfer.lock;

/**
 *
 */
public interface AccountLock {

    void lock(Long accountId, long timeoutMillis) throws InterruptedException;

    void unlock(Long accountId);
}

    