package com.taher.moneytransfer;

import com.taher.moneytransfer.model.Account;
import com.taher.moneytransfer.model.Transaction;
import java.util.Date;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;

public class TestUtil {

    public static Account randomAccount() {
        Account a = new Account();
        a.setUser(RandomStringUtils.randomAlphabetic(20));
        a.setBalance(RandomUtils.nextLong(1000, 100000));
        return a;
    }

    public static Transaction randomTransaction() {
        Transaction a = new Transaction();
        a.setTime(new Date());
        a.setSrcAccountId(RandomUtils.nextLong(1000, 100000));
        a.setDstAccountId(RandomUtils.nextLong(1000, 100000));
        a.setAmount(RandomUtils.nextLong(1000, 100000));
        return a;
    }

    public static void assertAccountsEqualsNotConsideringId(Account expected, Account actual) {
        Assert.assertEquals(expected.getUser(), actual.getUser());
        Assert.assertEquals(expected.getBalance(), actual.getBalance());
    }

    public static void assertTransactionsEqualsNotConsideringId(Transaction expected, Transaction actual) {
        Assert.assertEquals(expected.getTime().getTime(), actual.getTime().getTime());
        Assert.assertEquals(expected.getSrcAccountId(), actual.getSrcAccountId());
        Assert.assertEquals(expected.getDstAccountId(), actual.getDstAccountId());
        Assert.assertEquals(expected.getAmount(), actual.getAmount());
    }

}
