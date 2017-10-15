package com.murat.mocksepeti.rest;

import com.murat.mocksepeti.model.Credential;
import com.murat.mocksepeti.model.User;
import com.murat.mocksepeti.model.UserDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/credentials.json")
    Call<Credential> getCredentials();

    @GET("/userDetails.json")
    Call<List<User>> getUsers();
}
