package com.taher.moneytransfer;

import com.taher.moneytransfer.dao.DatabaseUtil;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseTest {

    @BeforeClass
    public static void beforeClass() throws SQLException {
        DatabaseUtil.initDB();
    }

    @AfterClass
    public static void afterClass() throws SQLException {
        DatabaseUtil.clearDB();
    }

}
