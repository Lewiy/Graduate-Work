package com.lewgmail.romanenko.taxiservice.view.fragmentClient;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Lev on 13.12.2016.
 */

public interface OrderListFragmentInterface {
    void showEror(String error);

    void setOrderList(HashMap<String, List<String>> list);
}
