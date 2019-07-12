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
public class Transaction {

    private Long id;
    private Date time;
    private Long srcAccountId;
    private Long dstAccountId;
    private Long amount;

}

