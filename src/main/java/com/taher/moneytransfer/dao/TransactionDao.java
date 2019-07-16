package com.taher.moneytransfer.dao;

import com.taher.moneytransfer.exception.RecordNotFoundException;
import com.taher.moneytransfer.model.Transaction;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface TransactionDao {

    Transaction get(Long id) throws RecordNotFoundException;

    List<Transaction> getAll();

    Transaction save(Transaction transaction);

    List<Transaction> getAllByAccountId(Long accountId);

}

