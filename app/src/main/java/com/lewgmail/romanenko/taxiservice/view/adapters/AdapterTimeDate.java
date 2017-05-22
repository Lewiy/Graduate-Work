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
    private String shortDate;


    public AdapterTimeDate(String dateTime, Context context) {
        this.dateTime = dateTime;
        this.context = context;
        devideDataTime();
    }

    public String getShortDate() {
        return shortDate;
    }

    public void setShortDate(String shortDate) {
        this.shortDate = shortDate;
    }

    private void devideDataTime() {
        date = dateTime.substring(0, dateTime.indexOf("T") - 1);
        time = dateTime.substring(dateTime.indexOf("T") + 1, 16);
        shortDate = date.substring(date.indexOf("-") + 1);
        if (time.equals("00:00"))
            time = context.getResources().getString(R.string.time_order_for_now);

    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

}
