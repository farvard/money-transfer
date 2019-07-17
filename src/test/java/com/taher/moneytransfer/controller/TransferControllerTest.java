package com.taher.moneytransfer.controller;

import static com.taher.moneytransfer.Constants.ACCOUNTS_BASE_URL;
import static com.taher.moneytransfer.Constants.DEFAULT_APP_PATH;
import static com.taher.moneytransfer.Constants.HEADER_LOCATION;
import static com.taher.moneytransfer.Constants.TRANSFER_BASE_URL;
import static com.taher.moneytransfer.TestUtil.randomAccount;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.taher.moneytransfer.model.Account;
import com.taher.moneytransfer.model.Transfer;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

public class TransferControllerTest extends ControllerTest {

    private final static String TRANSFER_TEST_URL = DEFAULT_APP_PATH + TRANSFER_BASE_URL;
    private final static String ACCOUNT_TEST_URL = DEFAULT_APP_PATH + ACCOUNTS_BASE_URL;

    @Test
    public void simpleTransferTest() {
        Account src = saveNewAccount();
        Account dst = saveNewAccount();
        long amount = RandomUtils.nextLong(1, src.getBalance());
        Transfer transfer = new Transfer(src.getId(), dst.getId(), amount);
        transferTest(src, dst, transfer);
    }

    private void transferTest(Account src, Account dst, Transfer transfer) {
        given().body(transfer).post(TRANSFER_TEST_URL);
        Account srcAfter = get(ACCOUNT_TEST_URL + "/" + src.getId()).as(Account.class);
        Account dstAfter = get(ACCOUNT_TEST_URL + "/" + dst.getId()).as(Account.class);
        assertThat(src.getBalance() - transfer.getAmount(), equalTo(srcAfter.getBalance()));
        assertThat(dst.getBalance() + transfer.getAmount(), equalTo(dstAfter.getBalance()));
    }

    @Test
    public void concurrentTransferTest() throws InterruptedException {
        int count = 1000;
        List<Account> accounts = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            accounts.add(saveNewAccount());
        }
        for (int i = 0; i < count; i += 2) {
            Account src = accounts.get(i);
            Account dst = accounts.get(i + 1);
            threads.add(new Thread(() -> transferTest(src, dst, new Transfer(src.getId(), dst.getId(), 5L))));
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    private Account saveNewAccount() {
        Response postSrc = given().body(randomAccount()).post(ACCOUNT_TEST_URL);
        String locationSrc = postSrc.header(HEADER_LOCATION);
        return get(locationSrc).as(Account.class);
    }
}