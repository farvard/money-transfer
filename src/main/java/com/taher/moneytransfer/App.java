package com.taher.moneytransfer;

import static spark.Spark.exception;

import com.taher.moneytransfer.controller.AccountController;
import com.taher.moneytransfer.controller.TransactionController;
import com.taher.moneytransfer.controller.TransferController;
import com.taher.moneytransfer.dao.DatabaseUtil;

import com.taher.moneytransfer.exception.RecordNotFoundException;
import java.sql.SQLException;
import org.eclipse.jetty.http.HttpStatus;

public class App {

    public static void main(String[] args) throws SQLException {
        new AccountController();
        new TransactionController();
        new TransferController();

        exception(RecordNotFoundException.class, (e, request, response) -> {
            response.status(HttpStatus.NOT_FOUND_404);
        });

        DatabaseUtil.initDB();
        DatabaseUtil.insertSampleData();
    }
}
