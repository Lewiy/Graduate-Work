package com.lewgmail.romanenko.taxiservice.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.lewgmail.romanenko.taxiservice.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration_driver_license, container, false);
        ButterKnife.bind(this, view);
        // regEX("ФФ 2312 СВ");
        return view;
    }

    public String getDriverLicense() {
        return driverLicense.getText().toString();
    }

    public String getLicenseExpirationDate() {
        return licenseExpirationDate.getText().toString();
    }


    @Override
    public boolean validitionFields() {
        if(checkInputedFields())
        return true;
        else return false;
    }

    private boolean checkInputedFields() {
        if (!driverLicense.getText().toString().equals("")
                && !licenseExpirationDate.getText().toString().equals(""))
            return true;
        else {
            Toast.makeText(this.getActivity(), R.string.dont_inputed_fields, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void regEX(String string) {
        Pattern p = Pattern.compile("([А-Я]{2})/([0-9]{4})/([А-Я]{2})");
        Matcher m = p.matcher(string);

        String str;
        if (m.matches())
            str = string;
    }
}
