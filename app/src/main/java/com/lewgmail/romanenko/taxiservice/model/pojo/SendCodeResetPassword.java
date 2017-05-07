package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lev on 07.05.2017.
 */

public class SendCodeResetPassword {


    @SerializedName("password")
    @Expose()
    private String password;
    @SerializedName("code")
    @Expose()
    private String code;

    public String getPassworde() {
        return password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
