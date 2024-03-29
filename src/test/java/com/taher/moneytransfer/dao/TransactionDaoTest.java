package com.taher.moneytransfer.dao;

import static com.taher.moneytransfer.TestUtil.assertTransactionsEqualsNotConsideringId;
import static com.taher.moneytransfer.TestUtil.randomTransaction;

import com.taher.moneytransfer.BaseTest;
import com.taher.moneytransfer.exception.RecordNotFoundException;
import com.taher.moneytransfer.model.Transaction;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class TransactionDaoTest extends BaseTest {

    private TransactionDao dao = new TransactionDaoH2Impl();

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
        Assert.assertThat(all.size(), Matchers.equalTo(saves.size()));
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

}