package com.taher.moneytransfer;

import com.taher.moneytransfer.controller.AccountController;

public class App {

    public static void main(String[] args) {
        new AccountController();
        DatabaseUtil.initDB();
        DatabaseUtil.insertSampleData();
    }
}
