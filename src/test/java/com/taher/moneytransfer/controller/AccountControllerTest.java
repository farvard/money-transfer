package com.taher.moneytransfer.controller;

import com.taher.moneytransfer.dao.DatabaseUtil;
import com.taher.moneytransfer.model.Account;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static com.taher.moneytransfer.Constants.*;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AccountControllerTest extends ControllerTest {


    private final static String ACCOUNT_TEST_URL = DEFAULT_APP_PATH + ACCOUNTS_BASE_URL;

    @BeforeClass
    public static void beforeClass() {
        new AccountController();
        DatabaseUtil.initDB();
    }

    @Test
    public void getTest() {
        String location = given().body(randomAccount()).post(ACCOUNT_TEST_URL).header(HEADER_LOCATION);
        get(location).then().statusCode(HttpStatus.OK_200);
    }

    @Test
    public void getAllTest() {
        int count = 10;
        for (int i = 0; i < count; i++) {
            given().body(randomAccount()).post(ACCOUNT_TEST_URL);
        }
        List<Account> accounts = get(ACCOUNT_TEST_URL).as(new TypeRef<List<Account>>() {
        });
        assertThat(accounts, hasSize(greaterThan(count)));
    }

    @Test
    public void save() {
        Response post = given().body(randomAccount()).post(ACCOUNT_TEST_URL);
        post.then().statusCode(HttpStatus.CREATED_201);
        String location = post.header(HEADER_LOCATION);
        get(location).then().statusCode(HttpStatus.OK_200);
    }

    @Test
    public void update() {
        String userDiff = ".UPDATE";
        long balanceDiff = 1000;
        Response post = given().body(randomAccount()).post(ACCOUNT_TEST_URL);
        post.then().statusCode(HttpStatus.CREATED_201);
        String location = post.header(HEADER_LOCATION);
        Account account = get(location).as(Account.class);
        Account newAccount = new Account(account.getId(), account.getUser() + userDiff, account.getBalance() + balanceDiff);
        given().body(newAccount).put(location).then().statusCode(HttpStatus.OK_200);
        Account updated = get(location).as(new TypeRef<Account>() {
        });
        assertThat(updated.getUser(), equalTo(account.getUser() + userDiff));
        assertThat(updated.getBalance(), equalTo(account.getBalance() + balanceDiff));
    }

    private Account randomAccount() {
        Account a = new Account();
        a.setUser(RandomStringUtils.randomAlphabetic(20));
        a.setBalance(RandomUtils.nextLong(1000, 100000));
        return a;
    }
}