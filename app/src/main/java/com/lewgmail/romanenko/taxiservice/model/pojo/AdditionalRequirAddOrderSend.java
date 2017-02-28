
package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class AdditionalRequirAddOrderSend {

    @SerializedName("reqId")
    @Expose
    private int reqId;
    @SerializedName("reqValueId")
    @Expose
    private int reqValueId;

    /**
     * @return The reqId
     */
    public int getReqId() {
        return reqId;
    }

    /**
     * @param reqId The reqId
     */
    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    /**
     * @return The reqValueId
     */
    public int getReqValueId() {
        return reqValueId;
    }

    /**
     * @param reqValueId The reqValueId
     */
    public void setReqValueId(int reqValueId) {
        this.reqValueId = reqValueId;
    }

}
