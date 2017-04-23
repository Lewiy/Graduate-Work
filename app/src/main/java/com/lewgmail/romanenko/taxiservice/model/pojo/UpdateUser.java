package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lev on 23.04.2017.
 */

public class UpdateUser {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobileNumbers")
    @Expose(serialize = false, deserialize = false)
    private List<MobileNumbers> mobileNumbers = new ArrayList<MobileNumbers>();
    @SerializedName("car")
    @Expose(serialize = false, deserialize = false)
    private Car car;

    @SerializedName("driverLicense")
    @Expose(deserialize = false)
    private UpdateUserDriverLicense driverLicense;


    public UpdateUserDriverLicense getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(UpdateUserDriverLicense driverLicense) {
        this.driverLicense = driverLicense;
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
     * @return The car
     */
    public Car getCar() {
        if (car == null) {
            Car car = new Car();
            //car.setCarId(0);
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
