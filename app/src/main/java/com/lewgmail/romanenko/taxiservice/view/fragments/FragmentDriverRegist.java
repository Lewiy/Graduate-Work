package com.lewgmail.romanenko.taxiservice.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.view.ValidationOfFields;
import com.lewgmail.romanenko.taxiservice.view.adapters.AdapterSpinnerLocalization;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lev on 03.03.2017.
 */

public class FragmentDriverRegist extends android.support.v4.app.Fragment implements OperationFragment {

    @BindView(R.id.plate_number_field)
    EditText plateNumberRegistration;
    @BindView(R.id.brend_registration_field)
    EditText brendRegistration;
    @BindView(R.id.model_registration)
    EditText modelRegistration;
    @BindView(R.id.set_car_type)
    Spinner mSpinnerCarType;
    @BindView(R.id.set_num_passenger)
    Spinner mSpinnerNumPassenger;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration_driver, container, false);
        ButterKnife.bind(this, view);
        initializeSpinner();
        return view;
    }

    public String getModel() {
        return brendRegistration.getText().toString();
    }

    public String getBrand() {
        return brendRegistration.getText().toString();
    }

    public String getPlateNumber() {
        return plateNumberRegistration.getText().toString();
    }

    public String getNumPassengers() {
        return mSpinnerNumPassenger.getSelectedItem().toString();
    }

    public String getTypeCar() {
        return AdapterSpinnerLocalization.adaptSpinnerTypeCar(mSpinnerCarType.getSelectedItemPosition());
    }

    @Override
    public boolean validitionFields() {
        if (checkInputedFields())
            return true;
        else return false;
    }

    private boolean checkInputedFields() {
        boolean checkFlag = true;
        String massege;
        if (modelRegistration.getText().toString().equals("")
                && plateNumberRegistration.getText().toString().equals("")
                && brendRegistration.getText().toString().equals("")) {
            modelRegistration.setError(this.getResources().getString(R.string.validation_obligatory_field));
            plateNumberRegistration.setError(this.getResources().getString(R.string.validation_obligatory_field));
            brendRegistration.setError(this.getResources().getString(R.string.validation_obligatory_field));
            return false;
        }

        massege = ValidationOfFields.checkPlateNumber(this.getContext(), plateNumberRegistration.getText().toString());
        if (!massege.equals("true")) {
            plateNumberRegistration.setError(massege);
            return false;
        }
        return checkFlag;
    }

    private void initializeSpinner() {
        ArrayAdapter<CharSequence> adapterSpinnerCarType =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.set_car_type, android.R.layout.simple_spinner_item);
        adapterSpinnerCarType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerCarType.setAdapter(adapterSpinnerCarType);
        ArrayAdapter<CharSequence> adapterSpinnerNumPassenger =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.set_num_passenger, android.R.layout.simple_spinner_item);
        adapterSpinnerCarType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerNumPassenger.setAdapter(adapterSpinnerNumPassenger);
    }
}
