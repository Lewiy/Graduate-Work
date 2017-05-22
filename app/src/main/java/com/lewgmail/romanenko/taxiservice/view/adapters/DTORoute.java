package com.lewgmail.romanenko.taxiservice.view.adapters;

import java.io.Serializable;

/**
 * Created by Lev on 19.05.2017.
 */

public class DTORoute implements Serializable {
    private double lat, lng;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

}
