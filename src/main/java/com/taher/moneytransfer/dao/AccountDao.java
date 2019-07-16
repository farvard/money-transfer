package com.taher.moneytransfer.dao;

import com.taher.moneytransfer.exception.NotEnoughMoneyException;
import com.taher.moneytransfer.exception.RecordNotFoundException;
import com.taher.moneytransfer.model.Account;

import java.util.List;

/**
 *
 */
public interface AccountDao {

    Account get(Long id) throws RecordNotFoundException;

    List<Account> getAll();

    Account save(Account account);

    void deposit(Long id, long amount) throws RecordNotFoundException, NotEnoughMoneyException;

    void withdrawal(Long id, long amount) throws RecordNotFoundException;
}

    