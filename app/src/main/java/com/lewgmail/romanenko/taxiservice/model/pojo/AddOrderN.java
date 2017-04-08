
package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddOrderN {

    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("routePoint")
    @Expose
    private List<RoutePointN> routePoint = null;
    @SerializedName("orderPrice")
    @Expose
    private OrderPriceN orderPrice;

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

    public OrderPriceN getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(OrderPriceN orderPrice) {
        this.orderPrice = orderPrice;
    }

}
