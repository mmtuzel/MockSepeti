package com.murat.mocksepeti.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Credential {
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("username")
    @Expose
    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
