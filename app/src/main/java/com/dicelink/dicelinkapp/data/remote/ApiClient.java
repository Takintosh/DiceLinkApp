package com.dicelink.dicelinkapp.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // Base URL for the API
    private static final String BASE_URL = "https://fa7d97bb-3b6e-4a09-8f2e-2f932b3e8676.mock.pstmn.io"; // CAMBIAR
    private static Retrofit retrofit = null;

    // Obtains the instance of the Retrofit client for making API calls
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
