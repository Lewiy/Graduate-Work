
package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class UpdateOrder {

    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("startPoint")
    @Expose
    private String startPoint;
    @SerializedName("endPoint")
    @Expose
    private String endPoint;
    @SerializedName("additionalRequirements")
    @Expose
    private List<AdditionalRequirAddOrderSend> additionalRequirements = new ArrayList<AdditionalRequirAddOrderSend>();

    /**
     * @return The startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime The startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return The startPoint
     */
    public String getStartPoint() {
        return startPoint;
    }

    /**
     * @param startPoint The startPoint
     */
    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    /**
     * @return The endPoint
     */
    public String getEndPoint() {
        return endPoint;
    }

    /**
     * @param endPoint The endPoint
     */
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * @return The additionalRequirements
     */
    public List<AdditionalRequirAddOrderSend> getAdditionalRequirements() {
        return additionalRequirements;
    }

    /**
     * @param additionalRequirements The additionalRequirements
     */
    public void setAdditionalRequirements(List<AdditionalRequirAddOrderSend> additionalRequirements) {
        this.additionalRequirements = additionalRequirements;
    }

}
