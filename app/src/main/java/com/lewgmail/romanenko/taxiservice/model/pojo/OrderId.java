
package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class OrderId {

    @SerializedName("orderId")
    @Expose
    private long orderId;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("startPoint")
    @Expose
    private String startPoint;
    @SerializedName("endPoint")
    @Expose
    private String endPoint;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("customer")
    @Expose
    private Customer customer;
    @SerializedName("taxiDriver")
    @Expose()
    private TaxiDriver taxiDriver;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("additionalRequirements")
    @Expose
    private List<AdditionalRequirement> additionalRequirements = new ArrayList<AdditionalRequirement>();

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
     * @return The customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer The customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return The taxiDriver
     */
    public TaxiDriver getTaxiDriver() {
        if (taxiDriver != null)
        return taxiDriver;
        else {
            TaxiDriver myTaxiDriver = new TaxiDriver();
            myTaxiDriver.setName("No driver");
            myTaxiDriver.setTaxiDriverId(0);
            return myTaxiDriver;
        }
    }

    /**
     * @param taxiDriver The taxiDriver
     */
    public void setTaxiDriver(TaxiDriver taxiDriver) {
        this.taxiDriver = taxiDriver;
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

    /**
     * @return The additionalRequirements
     */
    public List<AdditionalRequirement> getAdditionalRequirements() {
        if (additionalRequirements.size() == 0) {
            AdditionalRequirement additionalRequirement = new AdditionalRequirement();
            additionalRequirement.setReqId(0);
            additionalRequirement.setReqValueId(-1);
            AdditionalRequirement additionalRequirement2 = new AdditionalRequirement();
            additionalRequirement2.setReqId(1);
            additionalRequirement2.setReqValueId(-1);
            additionalRequirements.add(additionalRequirement);
            additionalRequirements.add(additionalRequirement2);
        }
        if (additionalRequirements.size() == 1) {
            AdditionalRequirement additionalRequirement = new AdditionalRequirement();
            additionalRequirement.setReqId(0);
            additionalRequirement.setReqValueId(-1);
            additionalRequirements.add(additionalRequirement);
        }

        return additionalRequirements;
    }

    /**
     * @param additionalRequirements The additionalRequirements
     */
    public void setAdditionalRequirements(List<AdditionalRequirement> additionalRequirements) {
        this.additionalRequirements = additionalRequirements;
    }

}
