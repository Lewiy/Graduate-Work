package com.lewgmail.romanenko.taxiservice.model.dataManager;

import com.lewgmail.romanenko.taxiservice.model.pojo.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lev on 19.11.2016.
 */

public class LoggedUser {

    private static LoggedUser mInstance;
    private String name;
    private long userId;
    private String email;
    private String password;
    private List<String> mobileNumbers = new ArrayList<String>();
    private String userType;
    private Car car;
    private String token;

    private LoggedUser() {

    }

    public static LoggedUser getmInstance() {
        if (mInstance == null)
            mInstance = new LoggedUser();
        return mInstance;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void logOut() {
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

    public List<String> getMobileNumbers() {
        return mobileNumbers;
    }

    public void setMobileNumbers(List<String> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }


}
