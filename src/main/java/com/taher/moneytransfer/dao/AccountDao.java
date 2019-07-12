package com.taher.moneytransfer.dao;

import com.taher.moneytransfer.model.Account;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface AccountDao {

    Optional<Account> get(Long id);

    List<Account> getAll();

    Account save(Account account);

    void update(Account account);
}

    