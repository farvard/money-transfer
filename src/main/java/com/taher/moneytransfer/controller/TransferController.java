package com.taher.moneytransfer.controller;

import com.google.gson.Gson;
import com.taher.moneytransfer.model.Transfer;
import com.taher.moneytransfer.service.TransferService;
import spark.Route;

import static com.taher.moneytransfer.Constants.TRANSFER_BASE_URL;
import static com.taher.moneytransfer.controller.JsonUtil.json;
import static spark.Spark.path;
import static spark.Spark.post;

/**
 *
 */
public class TransferController {

    private TransferService transferService = new TransferService();

    public TransferController() {
        path(TRANSFER_BASE_URL, () -> {
            post("", transfer(), json());
        });
    }

    private Route transfer() {
        return (request, response) -> {
            Transfer transfer = new Gson().fromJson(request.body(), Transfer.class);
            transferService.transfer(transfer);
            return "";
        };
    }
}

    