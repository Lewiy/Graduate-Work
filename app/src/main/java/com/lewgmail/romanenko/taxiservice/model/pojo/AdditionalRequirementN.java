
package com.lewgmail.romanenko.taxiservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class AdditionalRequirementN {


    @SerializedName("reqId")
    @Expose
    private int reqId;
    @SerializedName("reqValueId")
    @Expose
    private int reqValueId;


    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public int getReqValueId() {
        return reqValueId;
    }

    public void setReqValueId(int reqValueId) {
        this.reqValueId = reqValueId;
    }


}
