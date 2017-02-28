
package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Car {

    @SerializedName("manufacturer")
    @Expose
    private String manufacturer;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("plateNumber")
    @Expose
    private String plateNumber;
    @SerializedName("seatsNumber")
    @Expose
    private int seatsNumber;
    @SerializedName("carType")
    @Expose
    private String carType;
    @SerializedName("carId")
    @Expose(serialize = false)
    private int carId;

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }


    /**
     * @return The manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer The manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return The model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model The model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return The plateNumber
     */
    public String getPlateNumber() {
        return plateNumber;
    }

    /**
     * @param plateNumber The plateNumber
     */
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    /**
     * @return The seatsNumber
     */
    public int getSeatsNumber() {
        return seatsNumber;
    }

    /**
     * @param seatsNumber The seatsNumber
     */
    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    /**
     * @return The carType
     */
    public String getCarType() {
        return carType;
    }

    /**
     * @param carType The carType
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }

}
