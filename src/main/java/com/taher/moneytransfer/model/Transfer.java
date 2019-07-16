package com.taher.moneytransfer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 */
@Data
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

    