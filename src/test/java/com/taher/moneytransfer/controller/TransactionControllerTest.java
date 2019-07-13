package com.taher.moneytransfer.controller;

import com.taher.moneytransfer.DatabaseUtil;
import com.taher.moneytransfer.model.Transaction;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;

import java.util.Date;
import java.util.List;

import static com.taher.moneytransfer.Constants.*;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class TransactionControllerTest extends ControllerTest {


    private final static String TRANSACTION_TEST_URL = DEFAULT_APP_PATH + TRANSACTIONS_BASE_URL;

    @BeforeClass
    public static void beforeClass() {
        new TransactionController();
        DatabaseUtil.initDB();
    }

    @Test
    public void getTest() {
        String location = given().body(randomTransaction()).post(TRANSACTION_TEST_URL).header(HEADER_LOCATION);
        get(location).then().statusCode(HttpStatus.OK_200);
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

    private Transaction randomTransaction() {
        Transaction a = new Transaction();
        a.setTime(new Date());
        a.setSrcAccountId(RandomUtils.nextLong(1000, 100000));
        a.setDstAccountId(RandomUtils.nextLong(1000, 100000));
        a.setAmount(RandomUtils.nextLong(1000, 100000));
        return a;
    }

}