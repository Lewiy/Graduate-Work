package com.lewgmail.romanenko.taxiservice.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.lewgmail.romanenko.taxiservice.R;

import java.util.List;

/**
 * Created by Lev on 24.03.2017.
 */

public class AdapterAddPointOfRoute extends ArrayAdapter<String> {

    private Context context;

    public AdapterAddPointOfRoute(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        //View view = inflater.inflate(R.layout.address_point, null);
        if (convertView == null) {
            LayoutInflater lInflater = (LayoutInflater) context.getSystemService(
                    Activity.LAYOUT_INFLATER_SERVICE);

            convertView = lInflater.inflate(R.layout.address_point, null);
        }
        return convertView;
    }
}
