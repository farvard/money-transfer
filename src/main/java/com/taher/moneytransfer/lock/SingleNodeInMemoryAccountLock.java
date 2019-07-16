package com.taher.moneytransfer.lock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class SingleNodeInMemoryAccountLock implements AccountLock {

    private ConcurrentHashMap<Long, Lock> lockMap = new ConcurrentHashMap<>();

    @Override
    public void lock(Long accountId, long timeoutMillis) throws InterruptedException {
        lockMap.putIfAbsent(accountId, new ReentrantLock());
        lockMap.get(accountId).tryLock(timeoutMillis, TimeUnit.MILLISECONDS);
    }

    @Override
    public void unlock(Long accountId) {
        lockMap.putIfAbsent(accountId, new ReentrantLock());
        lockMap.get(accountId).unlock();
    }
}

    