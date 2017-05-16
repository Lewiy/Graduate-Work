package com.lewgmail.romanenko.taxiservice.view.adapters;

import android.content.Context;

import com.lewgmail.romanenko.taxiservice.R;

/**
 * Created by Lev on 15.05.2017.
 */

public class AdapterAdditionalReqDriverView {

    public static String getReq(int id, int valueReq, Context context) {
        String[] typeReq;
        switch (id) {
            case 1:
                return
                        context.getResources().getStringArray(R.array.type_car)[valueReq];
            case 2:
                return
                        context.getResources().getStringArray(R.array.type_reconing)[valueReq];
            case 3:
                return
                        context.getResources().getStringArray(R.array.type_pets)[valueReq];
            case 4:
                return
                        context.getResources().getStringArray(R.array.type_baggage)[valueReq];
            case 5:
                return
                        context.getResources().getStringArray(R.array.type_extra_price)[valueReq];
            case 6:
                return
                        context.getResources().getStringArray(R.array.type_driver_services)[valueReq];
            case 7:
                return
                        context.getResources().getStringArray(R.array.type_number_passengers)[valueReq];
            default:
                return "Error";
        }
    }

}
