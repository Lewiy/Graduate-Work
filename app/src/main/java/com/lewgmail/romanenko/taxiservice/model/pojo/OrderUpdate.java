package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lev on 26.04.2017.
 */

public class OrderUpdate {

    @SerializedName("quickRequest")
    @Expose
    private boolean quickRequest;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("routePoints")
    @Expose
    private List<RoutePointN> routePoint = null;
    @SerializedName("additionalRequirements")
    @Expose
    private List<AdditionalRequirementN> additionalRequirementN;


    public boolean isQuickRequest() {
        return quickRequest;
    }

    public void setQuickRequest(boolean quickRequest) {
        this.quickRequest = quickRequest;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<RoutePointN> getRoutePoint() {
        return routePoint;
    }

    public void setRoutePoint(List<RoutePointN> routePoint) {
        this.routePoint = routePoint;
    }

    public List<AdditionalRequirementN> getAdditionalRequirementN() {
        return additionalRequirementN;
    }

    public void setAdditionalRequirementN(List<AdditionalRequirementN> additionalRequirementN) {
        this.additionalRequirementN = additionalRequirementN;
    }
}
