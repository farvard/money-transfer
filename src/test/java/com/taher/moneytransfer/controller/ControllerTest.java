package com.taher.moneytransfer.controller;

import com.taher.moneytransfer.dao.DatabaseUtil;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 *
 */
public class ControllerTest {

    @BeforeClass
    public static void beforeClass() throws SQLException {
        new AccountController();
        new TransactionController();
        new TransferController();
        DatabaseUtil.initDB();
    }

    @AfterClass
    public static void afterClass() throws SQLException {
        DatabaseUtil.clearDB();
    }

}

    