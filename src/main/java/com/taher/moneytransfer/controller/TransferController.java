package com.taher.moneytransfer.controller;

import com.taher.moneytransfer.dao.TransactionDao;
import com.taher.moneytransfer.dao.TransactionDaoH2Impl;
import spark.Route;

import static com.taher.moneytransfer.Constants.TRANSFER_BASE_URL;
import static com.taher.moneytransfer.controller.JsonUtil.json;
import static spark.Spark.path;
import static spark.Spark.post;

/**
 *
 */
public class TransferController {

    private TransactionDao transactionDao = new TransactionDaoH2Impl();

    public TransferController() {
        path(TRANSFER_BASE_URL, () -> {
            post("", transfer(), json());
        });
    }

    private Route transfer() {
        return (request, response) -> {
            return "";

        };
    }
}

    