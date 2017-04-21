
package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CalculatePrice {
    @SerializedName("routePoints")
    @Expose
    private List<RoutePointN> routePoint = null;
    @SerializedName("additionalRequirements")
    @Expose
    private List<AdditionalRequirementN> additionalRequirementN;

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
