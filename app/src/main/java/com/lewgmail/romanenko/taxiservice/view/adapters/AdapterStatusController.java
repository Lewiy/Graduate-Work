package com.lewgmail.romanenko.taxiservice.view.adapters;

import com.lewgmail.romanenko.taxiservice.R;

/**
 * Created by Lev on 17.12.2016.
 */

public class AdapterStatusController {

    private String typeUser, status;

    public AdapterStatusController(String typeUser, String status) {
        this.typeUser = typeUser;
        this.status = status;
    }

    public int typeStatus() {
        if (typeUser.equals("TAXI_DRIVER")) {
            switch (status) {
                case "NEW":
                    return R.array.change_status_driver_new;
                case "ACCEPTED":
                    return R.array.change_status_driver_accept;
                case "WAITING":
                    return R.array.change_status_driver_waiting;
                case "PROCESSING":
                    return R.array.change_status_driver_processing;
            }
        }
        if (typeUser.equals("CUSTOMER")) {
            switch (status) {
                case "ADD":
                    return R.array.change_status_client_add;
                case "ACCEPTED":
                    return R.array.change_status_client_accepted;
                case "NEW":
                    return R.array.change_status_client_new;
                case "WAITING":
                    return R.array.change_status_client_waiting;
            }
        }

        return R.array.change_status_driver_done;
    }

    public int typeStatusDriver() {

        switch (status) {
            case "NEW":
                return 2;
            case "ACCEPTED":
                return R.array.change_status_driver_accept;
            case "WAITING":
                return R.array.change_status_driver_waiting;
            case "PROCESSING":
                return R.array.change_status_driver_processing;
            default:
                return R.array.change_status_driver_done;
        }


    }
}
