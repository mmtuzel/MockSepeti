package com.murat.mocksepeti.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private ApiService apiService;

    public RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mocksepeti.firebaseio.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}
