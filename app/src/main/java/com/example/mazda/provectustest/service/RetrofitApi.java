package com.example.mazda.provectustest.service;

import com.example.mazda.provectustest.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.Call;

/**
 * Created by mazda on 30.06.2017.
 */

public interface RetrofitApi {
    @GET("/api/")
    Call<User> getUser();
}
