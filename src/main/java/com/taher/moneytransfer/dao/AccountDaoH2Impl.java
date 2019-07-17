package com.taher.moneytransfer.dao;

import com.taher.moneytransfer.exception.RecordNotFoundException;
import com.taher.moneytransfer.model.Account;
import com.taher.moneytransfer.util.IdGenerator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 */
@Slf4j
public class AccountDaoH2Impl implements AccountDao {

    private static final String GET_QUERY = "SELECT * FROM account WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM account ORDER BY id";
    private static final String INSERT_QUERY = "INSERT INTO account values(?, ?, ?)";
    private static final String DEPOSIT_QUERY = "UPDATE account SET balance=balance + ?  WHERE id = ?";
    private static final String WITHDRAWAL_QUERY = "UPDATE account SET balance=balance - ?  WHERE id = ?";
    private static final IdGenerator ID_GENERATOR = new IdGenerator();

    @Override
    public Account get(Long id) throws RecordNotFoundException {
        return DatabaseUtil.queryOne(Account.class, GET_QUERY, id);
    }

    @Override
    public List<Account> getAll() {
        return DatabaseUtil.queryList(Account.class, GET_ALL_QUERY);
    }

    @Override
    public Account save(Account account) {
        log.info("saving account={}", account);
        long id = ID_GENERATOR.next();
        account.setId(id);
        DatabaseUtil.queryUpdate(INSERT_QUERY, account.getId(), account.getUser(), account.getBalance());
        return new Account(id, account.getUser(), account.getBalance());
    }

    @Override
    public void deposit(Long id, long amount) {
        log.info("deposit id={}, amount={}", id, amount);
        DatabaseUtil.queryUpdate(DEPOSIT_QUERY, amount, id);
    }

    @Override
    public void withdrawal(Long id, long amount) {
        log.info("withdrawal id={}, amount={}", id, amount);
        DatabaseUtil.queryUpdate(WITHDRAWAL_QUERY, amount, id);
    }
}

