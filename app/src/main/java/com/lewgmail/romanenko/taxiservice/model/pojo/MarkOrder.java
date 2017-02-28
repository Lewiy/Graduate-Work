
package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class MarkOrder {

    @SerializedName("userId")
    @Expose
    private long userId;
    @SerializedName("orderStatus")
    @Expose
    private String type;

    /**
     * @return The userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @param userId The userId
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

}
