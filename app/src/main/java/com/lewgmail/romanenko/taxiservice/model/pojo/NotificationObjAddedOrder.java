package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.lewgmail.romanenko.taxiservice.view.adapters.AdapterTimeDate;

/**
 * Created by Lev on 21.05.2017.
 */

public class NotificationObjAddedOrder {

    private long orderId;
    private String startPoint, endPoint, price;
    private AdapterTimeDate startTime;
    private double startPointLat, getStartPointLng, endPointLat, getEndPointLng;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public AdapterTimeDate getStartTime() {
        return startTime;
    }

    public void setStartTime(AdapterTimeDate startTime) {
        this.startTime = startTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getStartPointLat() {
        return startPointLat;
    }

    public void setStartPointLat(double startPointLat) {
        this.startPointLat = startPointLat;
    }

    public double getGetStartPointLng() {
        return getStartPointLng;
    }

    public void setGetStartPointLng(double getStartPointLng) {
        this.getStartPointLng = getStartPointLng;
    }

    public double getEndPointLat() {
        return endPointLat;
    }

    public void setEndPointLat(double endPointLat) {
        this.endPointLat = endPointLat;
    }

    public double getGetEndPointLng() {
        return getEndPointLng;
    }

    public void setGetEndPointLng(double getEndPointLng) {
        this.getEndPointLng = getEndPointLng;
    }


}
