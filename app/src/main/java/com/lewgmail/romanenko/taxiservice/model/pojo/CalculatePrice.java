
package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CalculatePrice {

    @SerializedName("distance")
    @Expose
    private double distance;
    @SerializedName("additionalRequirements")
    @Expose
    private List<AdditionalRequirement> additionalRequirements = null;

    /**
     * @return The distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param distance The distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * @return The additionalRequirements
     */
    public List<AdditionalRequirement> getAdditionalRequirements() {
        return additionalRequirements;
    }

    /**
     * @param additionalRequirements The additionalRequirements
     */
    public void setAdditionalRequirements(List<AdditionalRequirement> additionalRequirements) {
        this.additionalRequirements = additionalRequirements;
    }

}
