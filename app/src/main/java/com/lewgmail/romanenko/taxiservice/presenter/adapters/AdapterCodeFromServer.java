package com.lewgmail.romanenko.taxiservice.presenter.adapters;

import android.content.Context;

import com.lewgmail.romanenko.taxiservice.R;

/**
 * Created by Lev on 16.06.2017.
 */

public class AdapterCodeFromServer {
    public static String AdapterCode(int code, Context context) {
        switch (code) {
            case 200:
                return context.getResources().getString(R.string.operation_completed);
            case 400:
                return context.getResources().getString(R.string.cant_perform);
            case 404:
                return context.getResources().getString(R.string.cant_perform);
            case 500:
                return context.getResources().getString(R.string.problems_on_the_server);
        }
        return context.getResources().getString(R.string.something_happened);
    }
}