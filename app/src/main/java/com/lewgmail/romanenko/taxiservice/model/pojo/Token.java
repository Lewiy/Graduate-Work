package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lev on 15.11.2016.
 */

public class Token {

    @SerializedName("token_type")
    @Expose
    private String tokenType;

    @SerializedName("user_type")
    @Expose
    private String userType;

    @SerializedName("token")
    @Expose
    private String mToken;

    @SerializedName("id")
    @Expose
    private long userId;

    public Token() {
    }

    public String getmToken() {
        return mToken;
    }

    public void setmToken(String mToken) {
        this.mToken = mToken;
    }


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getUserid() {
        return userId;
    }

    public void setUserid(long userid) {
        userId = userid;
    }

}
