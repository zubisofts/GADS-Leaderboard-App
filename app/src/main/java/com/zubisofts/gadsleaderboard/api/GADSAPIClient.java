package com.zubisofts.gadsleaderboard.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GADSAPIClient {

    private static Retrofit INSTATNCE;

    public static Retrofit getInstance(String baseUrl) {
        if (INSTATNCE == null) {
            INSTATNCE = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return INSTATNCE;
    }

}
