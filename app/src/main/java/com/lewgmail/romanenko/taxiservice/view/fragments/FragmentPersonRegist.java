package com.lewgmail.romanenko.taxiservice.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.model.pojo.UserRegistration;
import com.lewgmail.romanenko.taxiservice.model.pojo.UserRegistrationCar;
import com.lewgmail.romanenko.taxiservice.view.activity.RegistrationActivityFragm;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lev on 03.03.2017.
 */

public class FragmentPersonRegist extends android.support.v4.app.Fragment {
    /*@BindView(R.id.name_registration)
    EditText nameRegistration;
    @BindView(R.id.email_registration)
    EditText emailRegistration;
    @BindView(R.id.password_registration)
    EditText passwordRegistration;
    @BindView(R.id.number_registration)
    EditText mobNamberRegistration;
    @BindView(R.id.repeatPassword)
    EditText repeatPassword;
    @BindView(R.id.second_number_registration)
    EditText mobSecondNamber;
    @BindView(R.id.model_registration)
    EditText modelRegistration;
    @BindView(R.id.brend_registration_field)
    EditText brendRegistration;
    @BindView(R.id.plate_number_field)
    EditText plateNumberRegistration;
    @BindView(R.id.driver_registration)
    FrameLayout frameLayoutDriver;
    @BindView(R.id.set_car_type)
    Spinner mSpinnerCarType;
    @BindView(R.id.set_num_passenger)
    Spinner mSpinnerNumPassenger;
    @BindView(R.id.check_box_registration)
    CheckBox checkBoxDriverUser;
    @BindView(R.id.registration_button)
    Button registrationButton;*/

    RegistrationActivityFragm registrActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration_person, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

   /* public UserRegistration createUserPassenger() {
        UserRegistration userRegistration = new UserRegistration();

        userRegistration.setName(nameRegistration.getText().toString());
        userRegistration.setUsername(emailRegistration.getText().toString());
        userRegistration.setPassword(passwordRegistration.getText().toString());
        List<String> mobiles = new ArrayList();
        mobiles.add(mobNamberRegistration.getText().toString());
        mobiles.add(mobSecondNamber.getText().toString());
        userRegistration.setMobileNumbers(mobiles);
        userRegistration.setUserType("CUSTOMER");
        return userRegistration;
    }*/

    public void createUserDriver() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof RegistrationActivityFragm) {
            registrActivity = (RegistrationActivityFragm) context;
        }

    }

    @OnClick(R.id.check_box_registration)
    public void setNextOperationButtonRegist() {

        registrActivity.readFieldOther();
    }
}
