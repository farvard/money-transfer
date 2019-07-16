package com.taher.moneytransfer.dao;

import com.taher.moneytransfer.util.IdGenerator;
import com.taher.moneytransfer.model.Account;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public class AccountDaoH2Impl implements AccountDao {

    private static final String GET_QUERY = "SELECT * FROM account WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM account";
    private static final String INSERT_QUERY = "INSERT INTO account values(?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE account SET user=?, balance=? WHERE id=?";
    private static final IdGenerator ID_GENERATOR = new IdGenerator();

    @Override
    public Optional<Account> get(Long id) {
        return DatabaseUtil.queryOne(Account.class, GET_QUERY, id);
    }

    @Override
    public List<Account> getAll() {
        return DatabaseUtil.queryList(Account.class, GET_ALL_QUERY);
    }

    @Override
    public Account save(Account account) {
        long id = ID_GENERATOR.next();
        account.setId(id);
        DatabaseUtil.queryUpdate(INSERT_QUERY, account.getId(), account.getUser(), account.getBalance());
        return new Account(id, account.getUser(), account.getBalance());
    }

    @Override
    public void update(Account account) {
        if (account.getId() == null) {
            throw new IllegalStateException();
        }
        DatabaseUtil.queryUpdate(UPDATE_QUERY, account.getUser(), account.getBalance(), account.getId());
    }
}

