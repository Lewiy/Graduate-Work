
package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class TaxiDriver {

    @SerializedName("taxiDriverId")
    @Expose()
    private long taxiDriverId;
    @SerializedName("name")
    @Expose()
    private String name;

    /**
     * @return The taxiDriverId
     */
    public long getTaxiDriverId() {
        return taxiDriverId;
    }

    /**
     * @param taxiDriverId The taxiDriverId
     */
    public void setTaxiDriverId(long taxiDriverId) {
        this.taxiDriverId = taxiDriverId;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

}
