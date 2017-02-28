package com.lewgmail.romanenko.taxiservice.model.DTO;

/**
 * Created by Lev on 01.12.2016.
 */

public class DataGoogleMapDTO {

    private int distanceInt;
    private String distanceTx;
    private String duration;
    private int durationSec;
    private String destinationAddresses;
    private String originAddresses;

    public String getDestinationAddresses() {
        return destinationAddresses;
    }

    public void setDestinationAddresses(String destinationAddresses) {
        this.destinationAddresses = destinationAddresses;
    }

    public String getOriginAddresses() {
        return originAddresses;
    }

    public void setOriginAddresses(String originAddresses) {
        this.originAddresses = originAddresses;
    }

    public String getDistanceTx() {
        return distanceTx;
    }

    public void setDistanceTx(String distanceTx) {
        this.distanceTx = distanceTx;
    }

    public int getDistance() {
        return distanceInt;
    }

    public void setDistance(int distanceInt) {
        this.distanceInt = distanceInt;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getDurationSec() {
        return durationSec;
    }

    public void setDurationSec(int durationSec) {
        this.durationSec = durationSec;
    }


}
