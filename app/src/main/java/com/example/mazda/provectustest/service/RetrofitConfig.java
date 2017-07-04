package com.example.mazda.provectustest.service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mazda on 30.06.2017.
 */

public class RetrofitConfig {
    private  static Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .client(getRequestHeader())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    private static OkHttpClient getRequestHeader() {
        OkHttpClient.Builder b = new OkHttpClient.Builder();
        b.readTimeout(60000, TimeUnit.MILLISECONDS);
        b.writeTimeout(60000, TimeUnit.MILLISECONDS);

        OkHttpClient httpClient = b.build();
        return httpClient;
    }

    public static RetrofitApi getService(){
        return getRetrofit().create(RetrofitApi.class);
    }
}
