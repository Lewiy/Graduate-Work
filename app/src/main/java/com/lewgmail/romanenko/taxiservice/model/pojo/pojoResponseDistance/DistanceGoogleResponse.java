
package com.lewgmail.romanenko.taxiservice.model.pojo.pojoResponseDistance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class DistanceGoogleResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("origin_addresses")
    @Expose
    private List<String> originAddresses = new ArrayList<String>();
    @SerializedName("destination_addresses")
    @Expose
    private List<String> destinationAddresses = new ArrayList<String>();
    @SerializedName("rows")
    @Expose
    private List<Row> rows = new ArrayList<Row>();

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The originAddresses
     */
    public List<String> getOriginAddresses() {
        return originAddresses;
    }

    /**
     * @param originAddresses The origin_addresses
     */
    public void setOriginAddresses(List<String> originAddresses) {
        this.originAddresses = originAddresses;
    }

    /**
     * @return The destinationAddresses
     */
    public List<String> getDestinationAddresses() {
        return destinationAddresses;
    }

    /**
     * @param destinationAddresses The destination_addresses
     */
    public void setDestinationAddresses(List<String> destinationAddresses) {
        this.destinationAddresses = destinationAddresses;
    }

    /**
     * @return The rows
     */
    public List<Row> getRows() {
        return rows;
    }

    /**
     * @param rows The rows
     */
    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

}
