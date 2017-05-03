
package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetOrder {

    @SerializedName("orderId")
    @Expose
    private long orderId;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("routePoints")
    @Expose
    private List<RoutePoint> routePoint = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("customerId")
    @Expose
    private int customerId;
    @SerializedName("driverId")
    @Expose
    private int driverId;
    @SerializedName("distance")
    @Expose
    private double distance;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("extraPrice")
    @Expose
    private double extraPrice;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("additionalRequirements")
    @Expose
    private List<AdditionalRequirementN> additionalRequirements = null;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public List<RoutePoint> getRoutePoint() {
        return routePoint;
    }

    public void setRoutePoint(List<RoutePoint> routePoint) {
        this.routePoint = routePoint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(double extraPrice) {
        this.extraPrice = extraPrice;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<AdditionalRequirementN> getAdditionalRequirements() {
        return additionalRequirements;
    }

    public void setAdditionalRequirements(List<AdditionalRequirementN> additionalRequirements) {
        this.additionalRequirements = additionalRequirements;
    }

}
