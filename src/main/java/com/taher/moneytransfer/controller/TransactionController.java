package com.taher.moneytransfer.controller;

import static com.taher.moneytransfer.Constants.CONTENT_TYPE_JSON;
import static com.taher.moneytransfer.Constants.HEADER_LOCATION;
import static com.taher.moneytransfer.Constants.TRANSACTIONS_BASE_URL;
import static com.taher.moneytransfer.controller.JsonUtil.json;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;

import com.google.gson.Gson;
import com.taher.moneytransfer.model.Transaction;
import com.taher.moneytransfer.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.http.HttpStatus;
import spark.Route;

/**
 *
 */
@Slf4j
public class TransactionController {

    private TransactionService transactionService = new TransactionService();

    public TransactionController() {
        path(TRANSACTIONS_BASE_URL, () -> {
            get("/:id", getOneTransaction(), json());
            get("", getAllTransactions(), json());
            post("", saveTransaction(), json());
        });
    }

    private Route getOneTransaction() {
        log.info("getOneTransaction called");
        return (request, response) -> {
            response.type(CONTENT_TYPE_JSON);
            return transactionService.get(Long.parseLong(request.params(":id")));
        };
    }

    private Route getAllTransactions() {
        log.info("getAllTransactions called");
        return (request, response) -> {
            response.type(CONTENT_TYPE_JSON);
            return transactionService.getAll();
        };
    }

    private Route saveTransaction() {
        log.info("saveTransaction called");
        return (request, response) -> {
            Transaction save = transactionService.save(new Gson().fromJson(request.body(), Transaction.class));
            String location = request.raw().getRequestURL().toString() + "/" + save.getId();
            response.header(HEADER_LOCATION, location);
            response.status(HttpStatus.CREATED_201);
            return save.getId();
        };
    }
}

    