package com.taher.moneytransfer;

import com.taher.moneytransfer.controller.ControllerInitiator;
import com.taher.moneytransfer.dao.DatabaseUtil;

import java.sql.SQLException;

public class App {

    public static void main(String[] args) throws SQLException {
        ControllerInitiator.init();
        DatabaseUtil.initDB();
        if (args == null || args.length == 0) {
            DatabaseUtil.insertSampleData();
        }
    }
}
