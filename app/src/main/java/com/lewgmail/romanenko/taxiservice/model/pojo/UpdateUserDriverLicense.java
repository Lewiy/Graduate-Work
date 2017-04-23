package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lev on 23.04.2017.
 */

public class UpdateUserDriverLicense {
    @SerializedName("driverLicense")
    @Expose
    private String driverLicense;
    @SerializedName("expirationTime")
    @Expose
    private String expirationTime;

    public String getCode() {
        return driverLicense;
    }

    public void setCode(String code) {
        this.driverLicense = code;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }
}
