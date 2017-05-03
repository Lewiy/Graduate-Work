package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lev on 16.12.2016.
 */

public class LogInRequest {
    @SerializedName("username")
    @Expose
    private String userName;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("notificationToken")
    @Expose
    private String notificationToken;

    public String getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
