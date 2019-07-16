package com.taher.moneytransfer;

import com.taher.moneytransfer.controller.AccountController;
import com.taher.moneytransfer.controller.TransactionController;
import com.taher.moneytransfer.controller.TransferController;
import com.taher.moneytransfer.dao.DatabaseUtil;

import java.sql.SQLException;

public class App {

    public static void main(String[] args) throws SQLException {
        new AccountController();
        new TransactionController();
        new TransferController();
        DatabaseUtil.initDB();
        DatabaseUtil.insertSampleData();
    }
}
