package com.lewgmail.romanenko.taxiservice.view.adapters;

/**
 * Created by Lev on 09.03.2017.
 */

public class AdapterSpinnerLocalization {

    public static String adaptSpinnerTypeCar(int index) {
        switch (index) {
            case 0:
                return "PASSENGER_CAR";
            case 1:
                return "TRUCK";
            case 2:
                return "MINIBUS";
        }
        return "ERROR";
    }

    public static String adaptSpinnerTypeOrder(int index) {
        switch (index) {
            case 0:
                return "ALL";
            case 1:
                return "ACCEPTED";
            case 2:
                return "CANCELLED";
            case 3:
                return "DONE";
            case 4:
                return "NEW";
        }
        return "ERROR";
    }
}
