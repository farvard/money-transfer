package com.taher.moneytransfer.util;

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

    