package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

/**
 * Created by Lev on 19.12.2016.
 */
@Generated("org.jsonschema2pojo")
public class MobileNumbers {
    @SerializedName("idMobileNumber")
    @Expose
    private Integer idMobileNumber;
    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getIdMobileNumber() {
        return idMobileNumber;
    }

    public void setIdMobileNumber(Integer idMobileNumber) {
        this.idMobileNumber = idMobileNumber;
    }

}
