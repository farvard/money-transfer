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
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

public class TransferControllerTest extends ControllerTest {

    private final static String TRANSFER_TEST_URL = DEFAULT_APP_PATH + TRANSFER_BASE_URL;
    private final static String ACCOUNT_TEST_URL = DEFAULT_APP_PATH + ACCOUNTS_BASE_URL;

    @Test
    public void simpleTransferTest() {
        Response postSrc = given().body(randomAccount()).post(ACCOUNT_TEST_URL);
        String locationSrc = postSrc.header(HEADER_LOCATION);
        Account src = get(locationSrc).as(Account.class);
        Response postDst = given().body(randomAccount()).post(ACCOUNT_TEST_URL);
        String locationDst = postDst.header(HEADER_LOCATION);
        Account dst = get(locationDst).as(Account.class);
        long amount = RandomUtils.nextLong(1, src.getBalance());
        Transfer transfer = new Transfer(src.getId(), dst.getId(), amount);
        given().body(transfer).post(TRANSFER_TEST_URL);
        Account srcAfter = get(locationSrc).as(Account.class);
        Account dstAfter = get(locationDst).as(Account.class);
        assertThat(src.getBalance() - amount, equalTo(srcAfter.getBalance()));
        assertThat(dst.getBalance() + amount, equalTo(dstAfter.getBalance()));
    }

}