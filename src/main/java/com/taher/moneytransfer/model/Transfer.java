package com.taher.moneytransfer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}

    