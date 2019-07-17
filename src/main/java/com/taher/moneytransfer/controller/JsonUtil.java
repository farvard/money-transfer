package com.taher.moneytransfer.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import spark.ResponseTransformer;

@Slf4j
public class JsonUtil {

    public static String toJson(Object object) {
        log.debug("converting to json :" + object);
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }
}