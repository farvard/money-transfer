package com.taher.moneytransfer.dao;

import com.taher.moneytransfer.model.Transaction;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface TransactionDao {

    Optional<Transaction> get(Long id);

    List<Transaction> getAll();

    Transaction save(Transaction transaction);

    List<Transaction> getAllByAccountId(Long accountId);

}

