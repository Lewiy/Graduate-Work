package com.lewgmail.romanenko.taxiservice.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.view.fragments.addOrder.FragmentPage1;

import java.util.List;

/**
 * Created by Lev on 24.03.2017.
 */

public class AdapterAddPointOfRoute extends ArrayAdapter<String> {

    private Context context;
    private TextView addressLine;
    private ImageButton openMap;
    private FragmentPage1.AddOrderGatherDataFirstWindow activityCallBack;
    private List<String> listAddresses;
    private int position;
    private long itemId;
    private ListView route;
    private View convertViewCustom;

    public AdapterAddPointOfRoute(Context context, int resource, List<String> objects, ListView route) {
        super(context, resource, objects);
        this.context = context;
        this.listAddresses = objects;
        this.route = route;
        this.activityCallBack = activityCallBack;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        //View view = inflater.inflate(R.layout.address_point, null);
        if (convertView == null) {
            LayoutInflater Inflater = (LayoutInflater) context.getSystemService(
                    Activity.LAYOUT_INFLATER_SERVICE);

            convertView = Inflater.inflate(R.layout.address_point, null);
        }

        findByIdElemntOfItem(convertView, position);
        addressLine.setText(getItem(position));
        return convertView;
    }

    private View findByIdElemntOfItem(final View convertViewCustom, final int position) {
        addressLine = (TextView) convertViewCustom.findViewById(R.id.address_point);
        openMap = (ImageButton) convertViewCustom.findViewById(R.id.point1_map);
        this.convertViewCustom = convertViewCustom;
        //this.position = position;
        addressLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "position" + position, Toast.LENGTH_SHORT).show();
                activityCallBack.runAutoCompliteReplaceAddress(position);
            }
        });
        openMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent myIntent = new Intent(context, MapActivity.class);
                //Optional parameters
                //  context.startActivity(myIntent);
                activityCallBack.startActivityForResultMapRoute(position);
            }
        });
        return convertViewCustom;
    }

    public void setActivityCallBack(FragmentPage1.AddOrderGatherDataFirstWindow activityCallBack) {
        this.activityCallBack = activityCallBack;
    }

    public void myAddList(String string) {
        listAddresses.add(string);
        this.notifyDataSetChanged();
    }

    public void replaseItem(String address, int position) {

        listAddresses.set(position, address);

        this.notifyDataSetChanged();
    }
}
