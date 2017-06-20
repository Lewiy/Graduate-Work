package com.lewgmail.romanenko.taxiservice.presenter;

import com.lewgmail.romanenko.taxiservice.model.dataManager.ManagerGoogleMaps;
import com.lewgmail.romanenko.taxiservice.model.pojo.RoutePoint;
import com.lewgmail.romanenko.taxiservice.model.pojo.localAddress.LocalAddress;

import java.io.IOException;
import java.util.List;

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
                return normalAddress(result.getResults().get(0).getFormattedAddress());
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
        return "ERROR";
    }

    public List<RoutePoint> LocalizeAddress(List<RoutePoint> listRoute) {
        for (RoutePoint point : listRoute) {
            point.setStreet(LocalizeAddress(point.getLatitude() + "," + point.getLongtitude()));
        }
        return listRoute;
    }

    private String normalAddress(String address) {
        String addresses = null;
        int index = 0;
        for (int i = 0; i < 3; i++) {
            index = address.indexOf(",", index + 1);

        }

        addresses = address.substring(0, index);
        return addresses;
    }
}
