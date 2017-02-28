
package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class User {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose(deserialize = false)
    private String password;
    @SerializedName("mobileNumbers")
    @Expose
    private List<MobileNumbers> mobileNumbers = new ArrayList<MobileNumbers>();
    @SerializedName("userType")
    @Expose
    private String userType;
    @SerializedName("car")
    @Expose(serialize = false, deserialize = false)
    private Car car;

    @SerializedName("userId")
    @Expose(deserialize = false)
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return The mobileNumbers
     */
    public List<MobileNumbers> getMobileNumbers() {
        return mobileNumbers;
    }

    /**
     * @param mobileNumbers The mobileNumbers
     */
    public void setMobileNumbers(List<MobileNumbers> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }

    /**
     * @return The userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType The userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return The car
     */
    public Car getCar() {
        if (car == null) {
            Car car = new Car();
            car.setCarId(0);
            car.setManufacturer("");
            car.setCarType("");
            car.setSeatsNumber(0);
            car.setPlateNumber("");
            car.setModel("");
            return car;
        } else
        return car;
    }

    /**
     * @param car The car
     */
    public void setCar(Car car) {
        this.car = car;
    }

}
