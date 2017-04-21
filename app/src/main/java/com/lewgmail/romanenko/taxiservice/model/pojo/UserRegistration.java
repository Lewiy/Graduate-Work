
package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserRegistration {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("mobileNumbers")
    @Expose
    private List<MobileNumber> mobileNumbers = null;
    @SerializedName("userType")
    @Expose
    private String userType;
    @SerializedName("car")
    @Expose(serialize = false)
    private UserRegistrationCar car;
    @SerializedName("driverLicense")
    @Expose(serialize = false)
    private DriverLicense driverLicense;

    public DriverLicense getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(DriverLicense driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<MobileNumber> getMobileNumbers() {
        return mobileNumbers;
    }

    public void setMobileNumbers(List<MobileNumber> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public UserRegistrationCar getCar() {
        return car;
    }

    public void setCar(UserRegistrationCar car) {
        this.car = car;
    }

}
