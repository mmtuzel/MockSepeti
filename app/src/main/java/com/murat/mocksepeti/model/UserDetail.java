package com.murat.mocksepeti.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetail extends User {
    @SerializedName("birthDate")
    @Expose
    private String birthDate;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("location")
    @Expose
    private String location;

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
