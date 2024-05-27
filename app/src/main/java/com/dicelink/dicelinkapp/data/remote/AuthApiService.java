package com.dicelink.dicelinkapp.data.remote;

import com.dicelink.dicelinkapp.model.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthApiService {

    // Register a new user
    @Headers("x-mock-response-name: registerOK")
    @POST("register")
    Call<AuthResponse> registerUser(@Body RegistrationRequest request);

    // Login a user
    @Headers("x-mock-response-name: loginOK")
    @POST("login")
    Call<AuthResponse> loginUser(@Body LoginRequest request);

}
