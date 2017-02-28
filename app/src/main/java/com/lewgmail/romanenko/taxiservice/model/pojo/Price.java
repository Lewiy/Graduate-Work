
package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("price")
    @Expose
    private Double price;

    /**
     * @return The price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price The price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

}
