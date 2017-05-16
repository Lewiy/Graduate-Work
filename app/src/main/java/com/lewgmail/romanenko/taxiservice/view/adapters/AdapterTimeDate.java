package com.lewgmail.romanenko.taxiservice.view.adapters;

import android.content.Context;

import com.lewgmail.romanenko.taxiservice.R;

/**
 * Created by Lev on 16.05.2017.
 */

public class AdapterTimeDate {

    private String dateTime;
    private Context context;

    private String date;
    private String time;

    public AdapterTimeDate(String dateTime, Context context) {
        this.dateTime = dateTime;
        this.context = context;
        devideDataTime();
    }

    private void devideDataTime() {
        date = dateTime.substring(0, dateTime.indexOf("T") - 1);
        time = dateTime.substring(dateTime.indexOf("T") + 1, 16);
        if (time.equals("00:00"))
            time = context.getResources().getString(R.string.time_order_for_now);

    }

    public String getData() {
        return date;
    }

    public String getTime() {
        return time;
    }

}
