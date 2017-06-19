package com.lewgmail.romanenko.taxiservice.presenter.adapters;

import android.content.Context;
import android.content.Intent;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.view.activity.LoginActivity;

import static android.R.attr.value;

/**
 * Created by Lev on 16.06.2017.
 */

public class AdapterCodeFromServer {
    public static String AdapterCode(int code, Context context, String fromServer) {
        switch (code) {
            case 200:
                return context.getResources().getString(R.string.operation_completed);
            case 400:
                return context.getResources().getString(R.string.cant_perform) +
                        " " + fromServer;
            case 404:
                return context.getResources().getString(R.string.cant_perform) +
                        " " + fromServer;
            case 500:
                return context.getResources().getString(R.string.problems_on_the_server) +
                        " " + fromServer;
            case 401:
                Intent myIntent = new Intent(context, LoginActivity.class);
                myIntent.putExtra("key", value); //Optional parameters
                context.startActivity(myIntent);
                return context.getResources().getString(R.string.session_time);
        }
        return context.getResources().getString(R.string.something_happened);
    }
}
