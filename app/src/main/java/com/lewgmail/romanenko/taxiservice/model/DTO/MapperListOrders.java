package com.lewgmail.romanenko.taxiservice.model.DTO;

import com.lewgmail.romanenko.taxiservice.model.pojo.Order;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Lev on 10.12.2016.
 */

public class MapperListOrders {

    private List<Order> orderList;
    private ListOrdersData listOrdersData = new ListOrdersData();

    public MapperListOrders(List<Order> orderList) {
        this.orderList = orderList;
    }

    public HashMap<String, List<String>> getVOObject() {

        for (int i = 0; i < orderList.size(); i++) {
            listOrdersData.addItemOfList(orderList.get(i));
        }
        return listOrdersData.getOrdersList();
    }
}
