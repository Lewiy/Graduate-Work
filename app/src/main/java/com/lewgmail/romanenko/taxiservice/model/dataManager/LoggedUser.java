package com.lewgmail.romanenko.taxiservice.model.dataManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.lewgmail.romanenko.taxiservice.model.pojo.Car;

/**
 * Created by Lev on 19.11.2016.
 */

public class LoggedUser {

    private static LoggedUser mInstance;
    private String name;
    private long userId;
    private String email;
    private String password;
    private String userType;
    private Car car;
    private String token;
    private String PREF_DEVICE_TOKEN = null;

    private LoggedUser() {

    }

    public static LoggedUser getmInstance() {
        if (mInstance == null)
            mInstance = new LoggedUser();
        return mInstance;
    }

    public String getPREF_DEVICE_TOKEN(Context context) {
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        PREF_DEVICE_TOKEN = settings.getString("TOKEN", "ERROR");
        userId = settings.getLong("userId", 0);
        return PREF_DEVICE_TOKEN;
    }

    public void setTokenFromPref_Device_Token() {
        token = PREF_DEVICE_TOKEN;
    }

    public String getPrefToken() {
        return PREF_DEVICE_TOKEN;
    }



    public void setPREF_DEVICE_TOKEN(String PREF_DEVICE_TOKEN, Context context, long userId) {
        this.PREF_DEVICE_TOKEN = PREF_DEVICE_TOKEN;
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("TOKEN", PREF_DEVICE_TOKEN);
        editor.putLong("userId", userId);
        editor.commit();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void logOut(Context context) {
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("TOKEN", "ERROR");
        editor.putLong("userId", 0);
        editor.commit();
        mInstance = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }



}
