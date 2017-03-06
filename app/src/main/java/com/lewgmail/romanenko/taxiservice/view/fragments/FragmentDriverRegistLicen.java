package com.lewgmail.romanenko.taxiservice.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lewgmail.romanenko.taxiservice.R;

import butterknife.ButterKnife;

/**
 * Created by Lev on 04.03.2017.
 */

public class FragmentDriverRegistLicen extends android.support.v4.app.Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration_driver_license, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
