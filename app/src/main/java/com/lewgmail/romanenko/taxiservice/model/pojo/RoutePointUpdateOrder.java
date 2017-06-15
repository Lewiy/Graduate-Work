package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lev on 04.05.2017.
 */

public class RoutePointUpdateOrder {
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longtitude")
    @Expose
    private String longtitude;
    @SerializedName("routePointId")
    @Expose
    private Long routePointId;

    @SerializedName("routePointIndex")
    @Expose
    private Long routePointIndex;

    public Long getRoutePointId() {
        return routePointId;
    }

    public void setRoutePointId(Long routePointId) {
        this.routePointId = routePointId;
    }

    public Long getRoutePointIndex() {
        return routePointIndex;
    }

    public void setRoutePointIndex(Long routePointIndex) {
        this.routePointIndex = routePointIndex;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }
}
