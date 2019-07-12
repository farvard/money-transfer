package com.taher.moneytransfer.dao;

import com.taher.moneytransfer.model.Transaction;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TransactionDaoTest {

    private TransactionDao dao = new TransactionDaoH2Impl();

    @Test
    public void get() {
        Transaction random = randomTransaction();
        Transaction save = dao.save(random);
        Optional<Transaction> get = dao.get(save.getId());
        Assert.assertTrue(get.isPresent());
        Assert.assertEquals(random.getTime(), save.getTime());
        Assert.assertEquals(random.getSrcAccountId(), save.getSrcAccountId());
        Assert.assertEquals(random.getDstAccountId(), save.getDstAccountId());
        Assert.assertEquals(random.getAmount(), save.getAmount());
        Assert.assertEquals(random.getTime(), get.get().getTime());
        Assert.assertEquals(random.getSrcAccountId(), get.get().getSrcAccountId());
        Assert.assertEquals(random.getDstAccountId(), get.get().getDstAccountId());
        Assert.assertEquals(random.getAmount(), get.get().getAmount());
        Assert.assertEquals(save, get.get());
    }

    @Test
    public void getAll() {
        List<Transaction> accounts = new ArrayList<>();
        List<Transaction> saves = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Transaction account = randomTransaction();
            accounts.add(account);
            saves.add(dao.save(account));
        }
        Assert.assertEquals(saves.size(), 10);
        for (int i = 0; i < 10; i++) {
            Optional<Transaction> get = dao.get(saves.get(i).getId());
//            Assert.assertTrue(get.isPresent());
//            Assert.assertEquals(accounts.get(i).getUser(), saves.get(i).getUser());
//            Assert.assertEquals(accounts.get(i).getBalance(), saves.get(i).getBalance());
//            Assert.assertEquals(accounts.get(i).getUser(), get.get().getUser());
//            Assert.assertEquals(accounts.get(i).getBalance(), get.get().getBalance());
        }
    }

    @Test
    public void save() {
        Transaction random = randomTransaction();
        Transaction save = dao.save(random);
        Optional<Transaction> get = dao.get(save.getId());
        Assert.assertTrue(get.isPresent());
//        Assert.assertEquals(random.getUser(), save.getUser());
//        Assert.assertEquals(random.getBalance(), save.getBalance());
//        Assert.assertEquals(random.getUser(), get.get().getUser());
//        Assert.assertEquals(random.getBalance(), get.get().getBalance());
    }

    @Test
    public void update() {
        String tail = ".TAIL";
        long diff = 1000;
        Transaction random = randomTransaction();
        Transaction save = dao.save(random);
//        save.setUser(save.getUser() + tail);
//        save.setBalance(save.getBalance() + diff);
//        dao.update(save);
        Optional<Transaction> get = dao.get(save.getId());
        Assert.assertTrue(get.isPresent());
//        Assert.assertEquals(random.getUser() + tail, get.get().getUser());
//        Assert.assertEquals(random.getBalance() + diff, get.get().getBalance().longValue());
    }

    private Transaction randomTransaction() {
        Transaction a = new Transaction();
        a.setTime(new Date());
        a.setSrcAccountId(RandomUtils.nextLong(1000, 100000));
        a.setDstAccountId(RandomUtils.nextLong(1000, 100000));
        a.setAmount(RandomUtils.nextLong(1000, 100000));
        return a;
    }

}