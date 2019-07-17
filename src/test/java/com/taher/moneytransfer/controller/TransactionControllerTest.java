package com.taher.moneytransfer.controller;

import static com.taher.moneytransfer.Constants.DEFAULT_APP_PATH;
import static com.taher.moneytransfer.Constants.HEADER_LOCATION;
import static com.taher.moneytransfer.Constants.TRANSACTIONS_BASE_URL;
import static com.taher.moneytransfer.TestUtil.randomTransaction;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

import com.taher.moneytransfer.model.Transaction;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import java.util.List;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

public class TransactionControllerTest extends ControllerTest {

    private final static String TRANSACTION_TEST_URL = DEFAULT_APP_PATH + TRANSACTIONS_BASE_URL;

    @Test
    public void getTest() {
        String location = given().body(randomTransaction()).post(TRANSACTION_TEST_URL).header(HEADER_LOCATION);
        get(location).then().statusCode(HttpStatus.OK_200);
    }

    @Test
    public void getInvalidIdTest() {
        get(TRANSACTION_TEST_URL + "/" + 123456).then().statusCode(HttpStatus.NOT_FOUND_404);
    }

    @Test
    public void getAllTest() {
        int count = 10;
        for (int i = 0; i < count; i++) {
            given().body(randomTransaction()).post(TRANSACTION_TEST_URL);
        }
        List<Transaction> transactions = get(TRANSACTION_TEST_URL).as(new TypeRef<List<Transaction>>() {
        });
        assertThat(transactions, hasSize(greaterThan(count)));
    }

    @Test
    public void save() {
        Response post = given().body(randomTransaction()).post(TRANSACTION_TEST_URL);
        post.then().statusCode(HttpStatus.CREATED_201);
        String location = post.header(HEADER_LOCATION);
        get(location).then().statusCode(HttpStatus.OK_200);
    }

}