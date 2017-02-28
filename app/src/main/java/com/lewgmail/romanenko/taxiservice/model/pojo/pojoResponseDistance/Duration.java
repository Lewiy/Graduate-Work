
package com.lewgmail.romanenko.taxiservice.model.pojo.pojoResponseDistance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Duration {

    @SerializedName("value")
    @Expose
    private int value;
    @SerializedName("text")
    @Expose
    private String text;

    /**
     * @return The value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value The value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return The text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text The text
     */
    public void setText(String text) {
        this.text = text;
    }

}
