package com.taher.moneytransfer.dao;

import static com.taher.moneytransfer.TestUtil.assertAccountsEqualsNotConsideringId;
import static com.taher.moneytransfer.TestUtil.randomAccount;

import com.taher.moneytransfer.BaseTest;
import com.taher.moneytransfer.exception.RecordNotFoundException;
import com.taher.moneytransfer.model.Account;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class AccountDaoTest extends BaseTest {

    private AccountDao dao = new AccountDaoH2Impl();

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

}