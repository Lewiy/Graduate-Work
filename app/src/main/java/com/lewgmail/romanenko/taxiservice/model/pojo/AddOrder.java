
package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddOrder {

    @SerializedName("customerId")
    @Expose
    private long customerId;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("startPoint")
    @Expose
    private String startPoint;
    @SerializedName("endPoint")
    @Expose
    private String endPoint;
    @SerializedName("orderPrice")
    @Expose
    private OrderPrice orderPrice;

    /**
     *
     * @return
     *     The customerId
     */
    public long getCustomerId() {
        return customerId;
    }

    /**
     *
     * @param customerId
     *     The customerId
     */
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    /**
     *
     * @return
     *     The startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     *
     * @param startTime
     *     The startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     *
     * @return
     *     The startPoint
     */
    public String getStartPoint() {
        return startPoint;
    }

    /**
     *
     * @param startPoint
     *     The startPoint
     */
    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    /**
     *
     * @return
     *     The endPoint
     */
    public String getEndPoint() {
        return endPoint;
    }

    /**
     *
     * @param endPoint
     *     The endPoint
     */
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    /**
     *
     * @return
     *     The orderPrice
     */
    public OrderPrice getOrderPrice() {
        return orderPrice;
    }

    /**
     *
     * @param orderPrice
     *     The orderPrice
     */
    public void setOrderPrice(OrderPrice orderPrice) {
        this.orderPrice = orderPrice;
    }

}
