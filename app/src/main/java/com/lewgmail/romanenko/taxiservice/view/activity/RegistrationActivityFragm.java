package com.lewgmail.romanenko.taxiservice.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.model.pojo.UserRegistration;
import com.lewgmail.romanenko.taxiservice.model.pojo.UserRegistrationCar;
import com.lewgmail.romanenko.taxiservice.view.fragmentClient.OrderListFragment;
import com.lewgmail.romanenko.taxiservice.view.fragments.FragmentDriverRegist;
import com.lewgmail.romanenko.taxiservice.view.fragments.FragmentDriverRegistLicen;
import com.lewgmail.romanenko.taxiservice.view.fragments.FragmentPersonRegist;
import com.lewgmail.romanenko.taxiservice.view.fragments.ReadInform;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lev on 03.03.2017.
 */

public class RegistrationActivityFragm extends AppCompatActivity implements ReadInform {
     @BindView(R.id.registration_button_person)
     Button registrationButton;
    Fragment fragment = null;
    Class fragmentClass = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_fragment_registration);
        ButterKnife.bind(this);
        initializationFragments();
    }


    @OnClick(R.id.button_registration_back)
    public void onClickButtonBack() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        }
    }

    @OnClick(R.id.registration_button_person)
    public void onClickButtonRegestNext() {
            switch (getSupportFragmentManager().getBackStackEntryCount()) {
                case 1:
                    fragmentClass = FragmentDriverRegist.class;
                    break;
                case 2:
                    fragmentClass = FragmentDriverRegistLicen.class;
                    break;
                case 3:
                    registrationButton.setText(R.string.sign_up);
                    break;
            }
        if (getSupportFragmentManager().getBackStackEntryCount()< 3) {
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Вставляем фрагмент, заменяя текущий фрагмент
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.conteiner_registration, fragment)
                    .addToBackStack(null).commit();
        }
    }

    private void initializationFragments() {

        fragment = new FragmentPersonRegist();

        // Вставляем фрагмент, заменяя текущий фрагмент
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.conteiner_registration, fragment)
                .addToBackStack(null).commit();
    }

   /* private UserRegistration createUser() {
        UserRegistration userRegistration = new UserRegistration();

        userRegistration.setName(nameRegistration.getText().toString());
        userRegistration.setUsername(emailRegistration.getText().toString());
        userRegistration.setPassword(passwordRegistration.getText().toString());
        List<String> mobiles = new ArrayList();
        mobiles.add(mobNamberRegistration.getText().toString());
        mobiles.add(mobSecondNamber.getText().toString());
        userRegistration.setMobileNumbers(mobiles);

        if (checkBoxDriverUser.isChecked()) {
            userRegistration.setUserType("TAXI_DRIVER");
            UserRegistrationCar car = new UserRegistrationCar();
            //  car.setCarId(0);
            car.setManufacturer(brendRegistration.getText().toString());
            car.setModel(modelRegistration.getText().toString());
            car.setPlateNumber(plateNumberRegistration.getText().toString());
            car.setSeatsNumber(Integer.parseInt(mSpinnerNumPassenger.getSelectedItem().toString()));
            car.setCarType(mSpinnerCarType.getSelectedItem().toString());

            userRegistration.setCar(car);
        } else
            userRegistration.setUserType("CUSTOMER");


        return userRegistration;
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
    }


    @Override
    public void readField1(String text) {

    }

    @Override
    public void readField2(String text) {

    }

    @Override
    public void readField3(String text) {

    }

    @Override
    public void readField4(String text) {

    }

    @Override
    public void readField5(String text) {

    }

    @Override
    public void readFieldOther() {

    }
}
