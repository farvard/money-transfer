package com.taher.moneytransfer.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {

    private Long srcAccountId;
    private Long dstAccountId;
    private Long amount;

    public Transaction createTransaction() {
        return new Transaction(null, new Date(), srcAccountId, dstAccountId, amount);
    }
}

    