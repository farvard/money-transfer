package com.taher.moneytransfer.dao;

import com.taher.moneytransfer.DatabaseUtil;
import com.taher.moneytransfer.model.Transaction;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TransactionDaoTest {

    private TransactionDao dao = new TransactionDaoH2Impl();

    @BeforeClass
    public static void beforeClass() {
        DatabaseUtil.initDB();
    }

    @Test
    public void get() {
        Transaction random = randomTransaction();
        Transaction save = dao.save(random);
        Optional<Transaction> get = dao.get(save.getId());
        Assert.assertTrue(get.isPresent());
        assertTransactionsEqualsNotConsideringId(random, save);
        assertTransactionsEqualsNotConsideringId(random, get.get());
        Assert.assertEquals(save, get.get());
    }

    @Test
    public void getAll() {
        List<Transaction> trans = new ArrayList<>();
        List<Transaction> saves = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Transaction account = randomTransaction();
            trans.add(account);
            saves.add(dao.save(account));
        }
        Assert.assertEquals(saves.size(), 10);
        for (int i = 0; i < 10; i++) {
            Optional<Transaction> get = dao.get(saves.get(i).getId());
            Assert.assertTrue(get.isPresent());
            assertTransactionsEqualsNotConsideringId(trans.get(i), saves.get(i));
            assertTransactionsEqualsNotConsideringId(trans.get(i), get.get());
        }
    }

    @Test
    public void save() {
        Transaction random = randomTransaction();
        Transaction save = dao.save(random);
        Optional<Transaction> get = dao.get(save.getId());
        Assert.assertTrue(get.isPresent());
        assertTransactionsEqualsNotConsideringId(random, save);
        assertTransactionsEqualsNotConsideringId(random, get.get());
    }

    private Transaction randomTransaction() {
        Transaction a = new Transaction();
        a.setTime(new Date());
        a.setSrcAccountId(RandomUtils.nextLong(1000, 100000));
        a.setDstAccountId(RandomUtils.nextLong(1000, 100000));
        a.setAmount(RandomUtils.nextLong(1000, 100000));
        return a;
    }

    private void assertTransactionsEqualsNotConsideringId(Transaction expected, Transaction actual) {
        Assert.assertEquals(expected.getTime(), actual.getTime());
        Assert.assertEquals(expected.getSrcAccountId(), actual.getSrcAccountId());
        Assert.assertEquals(expected.getDstAccountId(), actual.getDstAccountId());
        Assert.assertEquals(expected.getAmount(), actual.getAmount());
    }

}