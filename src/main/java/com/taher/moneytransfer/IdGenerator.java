package com.taher.moneytransfer;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 */
public class IdGenerator {

    private AtomicLong integer = new AtomicLong(0);

    public long next() {
        return integer.incrementAndGet();
    }


}

    