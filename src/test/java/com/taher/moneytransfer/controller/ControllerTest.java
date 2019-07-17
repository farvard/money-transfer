package com.taher.moneytransfer.controller;

import com.taher.moneytransfer.App;
import com.taher.moneytransfer.dao.DatabaseUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.sql.SQLException;

/**
 *
 */
public class ControllerTest {

    @BeforeClass
    public static void beforeClass() throws SQLException {
        App.main(new String[]{"test"});
    }

    @AfterClass
    public static void afterClass() throws SQLException {
        DatabaseUtil.clearDB();
    }

}

    