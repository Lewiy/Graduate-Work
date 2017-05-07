package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lev on 07.05.2017.
 */

public class SendEmailResetPassword {

    @SerializedName("email")
    @Expose()
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String taxiDriverId) {
        this.email = taxiDriverId;
    }
}
