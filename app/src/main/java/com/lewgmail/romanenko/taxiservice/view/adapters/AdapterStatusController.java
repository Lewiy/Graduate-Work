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
            }
        } else {
            switch (status) {
                case "ADD":
                    return R.array.change_status_client_add;
                case "ACCEPTED":
                    return R.array.change_status_client_new_accepted;
                case "NEW":
                    return R.array.change_status_client_new_accepted;
            }
        }
        return R.array.change_status_driver_done;
    }
}
