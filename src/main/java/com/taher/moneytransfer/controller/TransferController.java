package com.taher.moneytransfer.controller;

import static com.taher.moneytransfer.Constants.TRANSFER_BASE_URL;
import static com.taher.moneytransfer.controller.JsonUtil.json;
import static spark.Spark.path;
import static spark.Spark.post;

import com.google.gson.Gson;
import com.taher.moneytransfer.model.Transfer;
import com.taher.moneytransfer.service.TransferService;
import lombok.extern.slf4j.Slf4j;
import spark.Route;

/**
 *
 */
@Slf4j
public class TransferController {

    private TransferService transferService = new TransferService();

    public TransferController() {
        path(TRANSFER_BASE_URL, () -> {
            post("", transfer(), json());
        });
    }

    private Route transfer() {
        log.info("transfer called");
        return (request, response) -> {
            Transfer transfer = new Gson().fromJson(request.body(), Transfer.class);
            transferService.transfer(transfer);
            return "";
        };
    }
}

    