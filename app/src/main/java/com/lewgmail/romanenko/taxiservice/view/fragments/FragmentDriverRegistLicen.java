package com.lewgmail.romanenko.taxiservice.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.view.ValidationOfFields;

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
        boolean checkFlag = true;
        String massege;
        massege = ValidationOfFields.checkCodeLicense(this.getContext(), driverLicense.getText().toString());
        if (!massege.equals("true")) {
            driverLicense.setError(massege);
            return false;
        }

        massege = ValidationOfFields.checkExpirationTime(this.getContext(), licenseExpirationDate.getText().toString());
        if (!massege.equals("true")) {
            licenseExpirationDate.setError(massege);
            return false;
        }


        checkFlag = validationEmptyField(driverLicense);
        if (checkFlag == false)
            return false;
        checkFlag = validationEmptyField(licenseExpirationDate);
        return checkFlag;
    }

    private boolean validationEmptyField(EditText editText) {
        if (editText.getText().toString().matches("")) {
            editText.setError(getContext().getResources().getString(R.string.validation_obligatory_field));
            return false;
        }

        return true;
    }
}
