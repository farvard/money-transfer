package com.taher.moneytransfer.dao;

import com.taher.moneytransfer.exception.RecordNotFoundException;
import com.taher.moneytransfer.model.Transaction;
import org.apache.commons.lang3.RandomUtils;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDaoTest {

    private TransactionDao dao = new TransactionDaoH2Impl();

    @BeforeClass
    public static void beforeClass() throws SQLException {
        DatabaseUtil.initDB();
    }

    @Test
    public void get() throws RecordNotFoundException {
        Transaction random = randomTransaction();
        Transaction save = dao.save(random);
        Transaction get = dao.get(save.getId());
        assertTransactionsEqualsNotConsideringId(random, save);
        assertTransactionsEqualsNotConsideringId(random, get);
        Assert.assertEquals(save, get);
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
        List<Transaction> all = dao.getAll();
        for (int i = 0; i < 10; i++) {
            assertTransactionsEqualsNotConsideringId(trans.get(i), saves.get(i));
        }
        Assert.assertThat(all.size(), Matchers.greaterThan(saves.size()));
    }

    @Test
    public void save() throws RecordNotFoundException {
        Transaction random = randomTransaction();
        Transaction save = dao.save(random);
        Transaction get = dao.get(save.getId());
        assertTransactionsEqualsNotConsideringId(random, save);
        assertTransactionsEqualsNotConsideringId(random, get);
    }

    @Test
    public void getAllByAccountId() {
        List<Transaction> trans = new ArrayList<>();
        List<Transaction> saves = new ArrayList<>();
        int count = 30;
        long id = 1;
        for (int i = 0; i < count; i++) {
            Transaction account = randomTransaction();
            if (i % 3 == 0) {
                account.setSrcAccountId(id);
            } else if (i % 3 == 1) {
                account.setSrcAccountId(id);
            }
            trans.add(account);
            saves.add(dao.save(account));
        }
        Assert.assertEquals(saves.size(), count);
        List<Transaction> all = dao.getAllByAccountId(id);
        Assert.assertEquals(all.size(), count / 3 * 2);
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
        Assert.assertEquals(expected.getTime().getTime(), actual.getTime().getTime());
        Assert.assertEquals(expected.getSrcAccountId(), actual.getSrcAccountId());
        Assert.assertEquals(expected.getDstAccountId(), actual.getDstAccountId());
        Assert.assertEquals(expected.getAmount(), actual.getAmount());
    }

}