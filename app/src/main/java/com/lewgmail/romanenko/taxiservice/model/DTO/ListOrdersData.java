package com.lewgmail.romanenko.taxiservice.model.DTO;

import com.lewgmail.romanenko.taxiservice.model.pojo.Order;
import com.lewgmail.romanenko.taxiservice.presenter.LocalizeAddress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Lev on 10.12.2016.
 */

public class ListOrdersData {

    private HashMap<String, List<String>> ordersList = new HashMap<>();

    public ListOrdersData() {

    }

    public void addItemOfList(Order order) {
        LocalizeAddress localizeAddress = new LocalizeAddress();
        List<String> itemOfList = new ArrayList<>();
        itemOfList.add(order.getStartTime());
        if (Locale.getDefault().getLanguage() == "en") {
            itemOfList.add(order.getStartPoint());
            itemOfList.add(order.getEndPoint());
        } else {
            itemOfList.add(localizeAddress.LocalizeAddress(Double.toString(order.getStartPointCords().getLatitude())
                    + "," + Double.toString(order.getStartPointCords().getLongtitude())));
            itemOfList.add(localizeAddress.LocalizeAddress(Double.toString(order.getEndPointCords().getLatitude())
                    + "," + Double.toString(order.getEndPointCords().getLongtitude())));
        }

        itemOfList.add(Double.toString(order.getPrice()));
        itemOfList.add(Long.toString(order.getOrderId()));
        ordersList.put(order.getStartPoint() + "|" + order.getOrderId(), itemOfList);
    }

    public HashMap<String, List<String>> getOrdersList() {
        return ordersList;
    }
}
