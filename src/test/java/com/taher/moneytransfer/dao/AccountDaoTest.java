package com.taher.moneytransfer.dao;

import com.taher.moneytransfer.exception.RecordNotFoundException;
import com.taher.moneytransfer.model.Account;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoTest {

    private AccountDao dao = new AccountDaoH2Impl();

    @BeforeClass
    public static void beforeClass() throws SQLException {
        DatabaseUtil.initDB();
    }

    @Test
    public void get() throws RecordNotFoundException {
        Account random = randomAccount();
        Account save = dao.save(random);
        Account get = dao.get(save.getId());
        assertAccountsEqualsNotConsideringId(random, save);
        assertAccountsEqualsNotConsideringId(random, get);
        Assert.assertEquals(random, get);
    }

    @Test
    public void getAll() throws RecordNotFoundException {
        List<Account> accounts = new ArrayList<>();
        List<Account> saves = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Account account = randomAccount();
            accounts.add(account);
            saves.add(dao.save(account));
        }
        Assert.assertEquals(saves.size(), 10);
        for (int i = 0; i < 10; i++) {
            Account get = dao.get(saves.get(i).getId());
            assertAccountsEqualsNotConsideringId(accounts.get(i), saves.get(i));
            assertAccountsEqualsNotConsideringId(accounts.get(i), get);
        }
    }

    @Test
    public void save() throws RecordNotFoundException {
        Account random = randomAccount();
        Account save = dao.save(random);
        Account get = dao.get(save.getId());
        assertAccountsEqualsNotConsideringId(random, save);
        assertAccountsEqualsNotConsideringId(random, get);
    }

    private Account randomAccount() {
        Account a = new Account();
        a.setUser(RandomStringUtils.randomAlphabetic(20));
        a.setBalance(RandomUtils.nextLong(1000, 100000));
        return a;
    }

    private void assertAccountsEqualsNotConsideringId(Account expected, Account actual) {
        Assert.assertEquals(expected.getUser(), actual.getUser());
        Assert.assertEquals(expected.getBalance(), actual.getBalance());
    }

}