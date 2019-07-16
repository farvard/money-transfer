package com.taher.moneytransfer.dao;

import com.taher.moneytransfer.exception.RecordNotFoundException;
import com.taher.moneytransfer.model.Transaction;
import com.taher.moneytransfer.util.IdGenerator;

import java.util.List;

/**
 *
 */
public class TransactionDaoH2Impl implements TransactionDao {

    private static final String GET_QUERY = "SELECT * FROM transaction WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM transaction ORDER BY id asc";
    private static final String GET_ALL_BY_ACCOUNT_ID_QUERY = "SELECT * FROM transaction WHERE srcAccountId = ? OR dstAccountId = ?";
    private static final String INSERT_QUERY = "INSERT INTO transaction values(?, ?, ?, ?, ?)";
    private static final IdGenerator ID_GENERATOR = new IdGenerator();

    @Override
    public Transaction get(Long id) throws RecordNotFoundException {
        return DatabaseUtil.queryOne(Transaction.class, GET_QUERY, id);
    }

    @Override
    public List<Transaction> getAll() {
        return DatabaseUtil.queryList(Transaction.class, GET_ALL_QUERY, null);
    }

    @Override
    public Transaction save(Transaction transaction) {
        long id = ID_GENERATOR.next();
        transaction.setId(id);
        DatabaseUtil.queryUpdate(INSERT_QUERY,
                transaction.getId(), transaction.getTime(),
                transaction.getSrcAccountId(), transaction.getDstAccountId(), transaction.getAmount());
        return new Transaction(id, transaction.getTime(),
                transaction.getSrcAccountId(), transaction.getDstAccountId(), transaction.getAmount());
    }

    @Override
    public List<Transaction> getAllByAccountId(Long accountId) {
        return DatabaseUtil.queryList(Transaction.class, GET_ALL_BY_ACCOUNT_ID_QUERY, accountId, accountId);
    }

}

    