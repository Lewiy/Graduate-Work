package com.lewgmail.romanenko.taxiservice.view.fragments;

import android.graphics.Path;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.lewgmail.romanenko.taxiservice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lev on 04.03.2017.
 */

public class FragmentDriverRegistLicen extends android.support.v4.app.Fragment implements OperationFragment {

    @BindView(R.id.driver_license)
    EditText driverLicense;
    @BindView(R.id.license_expiration_date)
    EditText licenseExpirationDate;
    @BindView(R.id.category_license)
    EditText categoryLicense;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration_driver_license, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public String getDriverLicense() {
        return driverLicense.getText().toString();
    }

    public String getLicenseExpirationDate() {
        return licenseExpirationDate.getText().toString();
    }

    public String getCategoryLicense() {
        return categoryLicense.getText().toString();
    }

    @Override
    public boolean validitionFields() {
        if(checkInputedFields())
        return true;
        else return false;
    }

    private boolean checkInputedFields() {
        if (!driverLicense.getText().toString().equals("")
                && !licenseExpirationDate.getText().toString().equals("")
                && !categoryLicense.getText().toString().equals(""))
            return true;
        else {
            Toast.makeText(this.getActivity(), R.string.dont_inputed_fields, Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
