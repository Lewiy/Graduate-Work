package com.lewgmail.romanenko.taxiservice.model.DTO;

import com.lewgmail.romanenko.taxiservice.model.pojo.pojoResponseDistance.DistanceGoogleResponse;

/**
 * Created by Lev on 01.12.2016.
 */

public class Mapper {

    private DataGoogleMapDTO dataGoogleMapDTO = new DataGoogleMapDTO();
    private DistanceGoogleResponse distanceGoogleResponse;

    private int distance;
    private String duration;
    private int durationSec;

    public Mapper(DistanceGoogleResponse distanceGoogleResponse) {
        this.distanceGoogleResponse = distanceGoogleResponse;
    }


    public DataGoogleMapDTO getDTO() {
        try {
            dataGoogleMapDTO.setDistance(distanceGoogleResponse.getRows().get(0).
                    getElements().get(0).getDistance().getValue());
            dataGoogleMapDTO.setDuration(distanceGoogleResponse.getRows().get(0).
                    getElements().get(0).getDuration().getText());
            dataGoogleMapDTO.setDistanceTx(distanceGoogleResponse.getRows().get(0).
                    getElements().get(0).getDistance().getText());
            dataGoogleMapDTO.setOriginAddresses(distanceGoogleResponse.
                    getOriginAddresses().get(0));
            dataGoogleMapDTO.setDestinationAddresses(distanceGoogleResponse.
                    getDestinationAddresses().get(0));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return dataGoogleMapDTO;
    }

}
