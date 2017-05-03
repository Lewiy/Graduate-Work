package com.lewgmail.romanenko.taxiservice.model.DTO;

import com.lewgmail.romanenko.taxiservice.model.pojo.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lev on 10.12.2016.
 */

public class ListOrdersData {

    private HashMap<String, List<String>> ordersList = new HashMap<>();

    public ListOrdersData() {

    }

    public void addItemOfList(Order order) {

        List<String> itemOfList = new ArrayList<>();
        itemOfList.add(order.getStartTime());
        itemOfList.add(order.getStartPoint());
        itemOfList.add(order.getEndPoint());
        itemOfList.add(Double.toString(order.getPrice()));
        itemOfList.add(Long.toString(order.getOrderId()));
        ordersList.put(order.getStartPoint() + "|" + order.getOrderId(), itemOfList);
    }

    public HashMap<String, List<String>> getOrdersList() {
        return ordersList;
    }
}
