package com.lewgmail.romanenko.taxiservice.presenter.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lev on 27.11.2016.
 */

public class TestDataForList {
    public static HashMap<String, List<String>> loadData() {
        HashMap<String, List<String>> expDetails = new HashMap<>();

        List<String> oopLanguages = new ArrayList<>();
        oopLanguages.add("Loading");
        oopLanguages.add("Loading");
        oopLanguages.add("Loading");
        oopLanguages.add("Loading");
        oopLanguages.add("Loading");

        expDetails.put("Loading", oopLanguages);

        return expDetails;
    }
}
