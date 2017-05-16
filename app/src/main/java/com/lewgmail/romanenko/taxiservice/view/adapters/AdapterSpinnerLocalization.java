package com.lewgmail.romanenko.taxiservice.view.adapters;

import com.lewgmail.romanenko.taxiservice.R;

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
            case 5:
                return "WAITING";
            case 6:
                return "PROCESSING";
        }
        return "ERROR";
    }

    public static String setChangeStatusOrder(int typeSpinerStatus, int pos, String typeUser) {

        if (typeUser.equals("TAXI_DRIVER")) {
            switch (typeSpinerStatus) {
                case R.array.change_status_driver_new:
                    switch (pos) {
                        case 0:
                            return "NEW";
                        case 1:
                            return "ACCEPTED";
                    }
                case R.array.change_status_driver_accept:
                    switch (pos) {
                        case 0:
                            return "ACCEPTED";
                        case 1:
                            return "WAITING";
                        case 2:
                            return "NEW";
                    }
                case R.array.change_status_driver_waiting:
                    switch (pos) {
                        case 0:
                            return "WAITING";
                        case 1:
                            return "PROCESSING";
                        case 2:
                            return "NEW";
                        case 3:
                            return "ACCEPTED";
                    }
                case R.array.change_status_driver_processing:
                    switch (pos) {
                        case 0:
                            return "PROCESSING";
                        case 1:
                            return "DONE";
                    }
            }
        } else {
            switch (typeSpinerStatus) {
                case R.array.change_status_client_add:
                    switch (pos) {
                        case 0:
                            return "NEW";
                    }
                case R.array.change_status_client_accepted:
                    switch (pos) {
                        case 0:
                            return "ACCEPTED";
                        case 1:
                            return "CANCELLED";
                    }
                case R.array.change_status_client_new:
                    switch (pos) {
                        case 0:
                            return "NEW";
                        case 1:
                            return "CANCELLED";
                    }
                case R.array.change_status_client_waiting:
                    switch (pos) {
                        case 0:
                            return "WAITING";
                        case 1:
                            return "CANCELLED";
                    }
            }
        }

        return "ERROR";
    }
}
