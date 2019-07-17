package com.taher.moneytransfer.controller;

import static com.taher.moneytransfer.Constants.ACCOUNTS_BASE_URL;
import static com.taher.moneytransfer.Constants.DEFAULT_APP_PATH;
import static com.taher.moneytransfer.Constants.HEADER_LOCATION;
import static com.taher.moneytransfer.TestUtil.randomAccount;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

import com.taher.moneytransfer.model.Account;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import java.util.List;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

public class AccountControllerTest extends ControllerTest {

    private final static String ACCOUNT_TEST_URL = DEFAULT_APP_PATH + ACCOUNTS_BASE_URL;

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
    public void getAccountsTransactionsTest() {
        //TODO
    }

    @Test
    public void save() {
        Response post = given().body(randomAccount()).post(ACCOUNT_TEST_URL);
        post.then().statusCode(HttpStatus.CREATED_201);
        String location = post.header(HEADER_LOCATION);
        get(location).then().statusCode(HttpStatus.OK_200);
    }

}