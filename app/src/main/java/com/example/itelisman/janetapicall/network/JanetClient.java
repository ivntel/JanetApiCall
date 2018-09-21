package com.example.itelisman.janetapicall.network;

import com.google.gson.Gson;

import io.techery.janet.ActionService;
import io.techery.janet.HttpActionService;
import io.techery.janet.Janet;
import io.techery.janet.gson.GsonConverter;
import io.techery.janet.okhttp.OkClient;

public class JanetClient {

    private static Janet janet;
    public static final String ITUNES_URL = "https://itunes.apple.com";

    private JanetClient(){
        // Constructor
    }

    static {
        // Setup Janet client instance
        ActionService httpService = new HttpActionService(ITUNES_URL, new OkClient(), new GsonConverter(new Gson()));
        janet = new Janet.Builder().addService(httpService).build();
    }

    public static Janet getJanet(){
        return janet;
    }
}
