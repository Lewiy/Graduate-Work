package com.lewgmail.romanenko.taxiservice.presenter;

import com.lewgmail.romanenko.taxiservice.model.pojo.Order;

import java.util.List;

/**
 * Created by Lev on 20.11.2016.
 */

public class DriverPresenter {

    private List<Order> orders;
    private String responseMsg;
    private int responseCode;

    public String getResponseMsg() {
        return responseMsg;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void loadOrderByType(List<Order> orders) {

        this.orders = orders;
    }

    public void onFinishRequest(int responceCode, String responceMsg) {

        this.responseCode = responceCode;
        this.responseMsg = responceMsg;

    }

    // for testing

    public List<Order> getOrder() {
        return this.orders;
    }
}
