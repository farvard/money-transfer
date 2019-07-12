package com.taher.moneytransfer.dao;

import com.taher.moneytransfer.model.Account;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDaoTest {

    private AccountDao dao = new AccountDaoH2Impl();

    @Test
    public void get() {
        Account random = randomAccount();
        Account save = dao.save(random);
        Optional<Account> get = dao.get(save.getId());
        Assert.assertTrue(get.isPresent());
        Assert.assertEquals(random.getUser(), save.getUser());
        Assert.assertEquals(random.getBalance(), save.getBalance());
        Assert.assertEquals(random.getUser(), get.get().getUser());
        Assert.assertEquals(random.getBalance(), get.get().getBalance());
        Assert.assertEquals(random, get.get());
    }

    @Test
    public void getAll() {
        List<Account> accounts = new ArrayList<>();
        List<Account> saves = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Account account = randomAccount();
            accounts.add(account);
            saves.add(dao.save(account));
        }
        Assert.assertEquals(saves.size(), 10);
        for (int i = 0; i < 10; i++) {
            Optional<Account> get = dao.get(saves.get(i).getId());
            Assert.assertTrue(get.isPresent());
            Assert.assertEquals(accounts.get(i).getUser(), saves.get(i).getUser());
            Assert.assertEquals(accounts.get(i).getBalance(), saves.get(i).getBalance());
            Assert.assertEquals(accounts.get(i).getUser(), get.get().getUser());
            Assert.assertEquals(accounts.get(i).getBalance(), get.get().getBalance());
        }
    }

    @Test
    public void save() {
        Account random = randomAccount();
        Account save = dao.save(random);
        Optional<Account> get = dao.get(save.getId());
        Assert.assertTrue(get.isPresent());
        Assert.assertEquals(random.getUser(), save.getUser());
        Assert.assertEquals(random.getBalance(), save.getBalance());
        Assert.assertEquals(random.getUser(), get.get().getUser());
        Assert.assertEquals(random.getBalance(), get.get().getBalance());
    }

    @Test
    public void update() {
        String tail = ".TAIL";
        long diff = 1000;
        Account random = randomAccount();
        Account save = dao.save(random);
        save.setUser(save.getUser() + tail);
        save.setBalance(save.getBalance() + diff);
        dao.update(save);
        Optional<Account> get = dao.get(save.getId());
        Assert.assertTrue(get.isPresent());
        Assert.assertEquals(random.getUser() + tail, get.get().getUser());
        Assert.assertEquals(random.getBalance() + diff, get.get().getBalance().longValue());
    }

    private Account randomAccount() {
        Account a = new Account();
        a.setUser(RandomStringUtils.randomAlphabetic(20));
        a.setBalance(RandomUtils.nextLong(1000, 100000));
        return a;
    }

}