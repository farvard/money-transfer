package com.taher.moneytransfer.controller;

import com.google.gson.Gson;
import com.taher.moneytransfer.dao.TransactionDao;
import com.taher.moneytransfer.dao.TransactionDaoH2Impl;
import com.taher.moneytransfer.model.Transaction;
import org.eclipse.jetty.http.HttpStatus;
import spark.Route;

import java.util.Optional;

import static com.taher.moneytransfer.Constants.*;
import static com.taher.moneytransfer.JsonUtil.json;
import static spark.Spark.*;

/**
 *
 */
public class TransactionController {

    private TransactionDao transactionDao = new TransactionDaoH2Impl();

    public TransactionController() {
        path(TRANSACTIONS_BASE_URL, () -> {
            get("/:id", getOneTransaction(), json());
            get("", getAllTransactions(), json());
            post("", saveTransaction(), json());
        });
    }

    private Route getOneTransaction() {
        return (request, response) -> {
            response.type(CONTENT_TYPE_JSON);
            Optional<Transaction> transaction = transactionDao.get(Long.parseLong(request.params(":id")));
            return transaction.get();
        };
    }

    private Route getAllTransactions() {
        return (request, response) -> {
            response.type(CONTENT_TYPE_JSON);
            return transactionDao.getAll();
        };
    }

    private Route saveTransaction() {
        return (request, response) -> {
            Transaction save = transactionDao.save(new Gson().fromJson(request.body(), Transaction.class));
            String location = request.raw().getRequestURL().toString() + "/" + save.getId();
            response.header(HEADER_LOCATION, location);
            response.status(HttpStatus.CREATED_201);
            return save.getId();
        };
    }
}

    