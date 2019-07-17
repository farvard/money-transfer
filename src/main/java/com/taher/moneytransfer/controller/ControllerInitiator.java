package com.taher.moneytransfer.controller;

import static spark.Spark.exception;

import com.taher.moneytransfer.exception.RecordNotFoundException;
import org.eclipse.jetty.http.HttpStatus;

public abstract class ControllerInitiator {

    public static void init() {
        new AccountController();
        new TransactionController();
        new TransferController();
        exception(RecordNotFoundException.class, (e, request, response) -> {
            response.status(HttpStatus.NOT_FOUND_404);
        });
    }

}
