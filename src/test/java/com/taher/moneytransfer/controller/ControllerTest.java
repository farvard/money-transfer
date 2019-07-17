package com.taher.moneytransfer.controller;

import com.taher.moneytransfer.App;
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
        App.main(null);
    }

    @AfterClass
    public static void afterClass() throws SQLException {
        DatabaseUtil.clearDB();
    }

}

    