package com.lewgmail.romanenko.taxiservice.presenter;

import com.lewgmail.romanenko.taxiservice.model.dataManager.ManagerGoogleMaps;
import com.lewgmail.romanenko.taxiservice.model.pojo.localAddress.LocalAddress;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by Lev on 23.05.2017.
 */

public class LocalizeAddress {
    private ManagerGoogleMaps managerGoogleMaps = new ManagerGoogleMaps();

    public String LocalizeAddress(String latLng) {
        Call<LocalAddress> call = null;
        LocalAddress result = null;
        try {
            call = managerGoogleMaps.getLocalAddress(latLng);

            result = call.execute().body();
            if (result != null) {
                return result.getResults().get(0).getFormattedAddress();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
        return "ERROR";
    }
}
