package com.lewgmail.romanenko.taxiservice.presenter.adapters;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lev on 09.12.2016.
 */

public class AdapterAdditionalRequiremnets {

    private Map<Integer, String> mapAdditionalRequirementsCAR = new HashMap<>();
    private Map<Integer, String> mapAdditionalRequirementsReconning = new HashMap<>();

    private Map<String, Integer> mapAdditionalRequirementsCARRev = new HashMap<>();
    private Map<String, Integer> mapAdditionalRequirementsReconningRev = new HashMap<>();

    public AdapterAdditionalRequiremnets() {
        setAdapter();
        setAdapterRev();
    }

    public String getCar(int value) {
        return mapAdditionalRequirementsCAR.get(value);
    }

    public String getRecon(int value) {
        return mapAdditionalRequirementsReconning.get(value);
    }

    public int getCarRev(String value) {
        return mapAdditionalRequirementsCARRev.get(value);
    }

    public int getRecRev(String value) {
        return mapAdditionalRequirementsReconningRev.get(value);
    }

    public void setAdapter() {
        mapAdditionalRequirementsCAR.put(-1, "Without additional requir");
        mapAdditionalRequirementsCAR.put(1, "TRUCK");
        mapAdditionalRequirementsCAR.put(2, "PASSENGER_CAR");
        mapAdditionalRequirementsCAR.put(3, "MINIBUS");
        mapAdditionalRequirementsReconning.put(-1, "Without additional requir");
        mapAdditionalRequirementsReconning.put(1, "Cash");
        mapAdditionalRequirementsReconning.put(2, "Credit Card");
    }

    public void setAdapterRev() {
        mapAdditionalRequirementsCARRev.put("TRUCK", 1);
        mapAdditionalRequirementsCARRev.put("PASSENGER_CAR", 2);
        mapAdditionalRequirementsCARRev.put("MINIBUS", 3);
        mapAdditionalRequirementsReconningRev.put("Cash", 1);
        mapAdditionalRequirementsReconningRev.put("Credit Card", 2);
    }

}
