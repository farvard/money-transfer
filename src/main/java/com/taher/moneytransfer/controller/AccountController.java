package com.taher.moneytransfer.controller;

import static com.taher.moneytransfer.Constants.ACCOUNTS_BASE_URL;
import static com.taher.moneytransfer.Constants.CONTENT_TYPE_JSON;
import static com.taher.moneytransfer.Constants.EMPTY_STRING;
import static com.taher.moneytransfer.Constants.HEADER_LOCATION;
import static com.taher.moneytransfer.Constants.TRANSACTIONS_BASE_URL;
import static com.taher.moneytransfer.controller.JsonUtil.json;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;

import com.google.gson.Gson;
import com.taher.moneytransfer.dao.TransactionDao;
import com.taher.moneytransfer.dao.TransactionDaoH2Impl;
import com.taher.moneytransfer.model.Account;
import com.taher.moneytransfer.service.AccountService;
import org.eclipse.jetty.http.HttpStatus;
import spark.Route;

/**
 *
 */
public class AccountController {

    private AccountService accountService = new AccountService();
    private TransactionDao transactionDao = new TransactionDaoH2Impl();

    public AccountController() {
        path(ACCOUNTS_BASE_URL, () -> {
            get("/:id", getOneAccount(), json());
            get("", getAllAccounts(), json());
            post("", saveAccount(), json());
            get("/:id" + TRANSACTIONS_BASE_URL, getAccountsTransactions(), json());
        });
    }

    private Route getOneAccount() {
        return (request, response) -> {
            response.type(CONTENT_TYPE_JSON);
            return accountService.get(Long.parseLong(request.params(":id")));
        };
    }

    private Route getAllAccounts() {
        return (request, response) -> {
            response.type(CONTENT_TYPE_JSON);
            return accountService.getAll();
        };
    }

    private Route saveAccount() {
        return (request, response) -> {
            Account save = accountService.save(new Gson().fromJson(request.body(), Account.class));
            String location = request.raw().getRequestURL().toString() + "/" + save.getId();
            response.header(HEADER_LOCATION, location);
            response.status(HttpStatus.CREATED_201);
            return save.getId();
        };
    }

    private Route getAccountsTransactions() {
        return (request, response) -> {
            transactionDao.getAllByAccountId(Long.parseLong(request.params(":id")));
            return EMPTY_STRING;
        };
    }
}
