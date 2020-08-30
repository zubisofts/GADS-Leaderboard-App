package com.zubisofts.gadsleaderboard.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GADSAPIClient {

    private static Retrofit INSTATNCE;

    public static Retrofit initInstance() {
        if (INSTATNCE == null) {
            INSTATNCE = new Retrofit.Builder()
                    .baseUrl("https://gadsapi.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return INSTATNCE;
    }

    public static Retrofit getInstance(String newApiBaseUrl) {
        return initInstance().newBuilder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(newApiBaseUrl).build();
    }

}
