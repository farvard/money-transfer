package com.taher.moneytransfer.model;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Long id;
    private String user;
    private Long balance;

}

    