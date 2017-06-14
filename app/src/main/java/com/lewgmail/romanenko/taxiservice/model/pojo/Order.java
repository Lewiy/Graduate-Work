
package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Order {

    @SerializedName("startPointCoordinates")
    @Expose(deserialize = false)
    ListOrdersCordinates startPointCords;
    @SerializedName("endPointCoordinates")
    @Expose(deserialize = false)
    ListOrdersCordinates endPointCords;
    @SerializedName("orderId")
    @Expose
    private long orderId;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("startPoint")
    @Expose(deserialize = false)
    private String startPoint;
    @SerializedName("endPoint")
    @Expose(deserialize = false)
    private String endPoint;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("status")
    @Expose
    private String status;

    public ListOrdersCordinates getStartPointCords() {
        return startPointCords;
    }

    public ListOrdersCordinates getEndPointCords() {
        return endPointCords;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The orderId
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId The orderId
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

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
     * @return The price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price The price
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
