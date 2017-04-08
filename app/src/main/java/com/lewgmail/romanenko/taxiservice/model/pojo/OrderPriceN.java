
package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderPriceN {

    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("additionalRequirements")
    @Expose
    private List<AdditionalRequirementN> additionalRequirements = null;

    /**
     * @return The distance
     */
    public Double getDistance() {
        return distance;
    }

    /**
     * @param distance The distance
     */
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    /**
     * @return The AdditionalRequirementsViewTransfer
     */
    public List<AdditionalRequirementN> getAdditionalRequirements() {
        return additionalRequirements;
    }

    /**
     * @param additionalRequirements The AdditionalRequirementsViewTransfer
     */
    public void setAdditionalRequirements(List<AdditionalRequirementN> additionalRequirements) {
        this.additionalRequirements = additionalRequirements;
    }

}
