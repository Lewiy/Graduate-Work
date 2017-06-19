package com.lewgmail.romanenko.taxiservice.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.model.pojo.DriverLicense;
import com.lewgmail.romanenko.taxiservice.model.pojo.MobileNumber;
import com.lewgmail.romanenko.taxiservice.model.pojo.UserRegistration;
import com.lewgmail.romanenko.taxiservice.model.pojo.UserRegistrationCar;
import com.lewgmail.romanenko.taxiservice.presenter.UserPresenter;
import com.lewgmail.romanenko.taxiservice.presenter.adapters.AdapterCodeFromServer;
import com.lewgmail.romanenko.taxiservice.view.fragments.FragmentDriverRegist;
import com.lewgmail.romanenko.taxiservice.view.fragments.FragmentDriverRegistLicen;
import com.lewgmail.romanenko.taxiservice.view.fragments.FragmentPersonRegist;
import com.lewgmail.romanenko.taxiservice.view.fragments.OperationFragment;
import com.lewgmail.romanenko.taxiservice.view.fragments.ReadInform;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.value;

/**
 * Created by Lev on 03.03.2017.
 */

public class RegistrationActivityFragm extends AppCompatActivity implements ReadInform, UserOperationInterface {

    @BindView(R.id.registration_button_person)
    Button registrationButton;
    UserRegistration userRegistration = new UserRegistration();
    private Fragment fragment = null;
    private Class fragmentClass = null;
    private boolean checkBoxDriver = false;
    private UserPresenter userPresenter;
    private int indexCurrentFragment = 1;
    private boolean flagRunActivity = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_fragment_registration);
        ButterKnife.bind(this);
        initializationFragments();
        userPresenter = new UserPresenter(this);
    }


    @OnClick(R.id.button_registration_back)
    public void onClickButtonBack() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            fragment = getSupportFragmentManager().findFragmentByTag(Integer.toString(--indexCurrentFragment));
            getSupportFragmentManager().popBackStack();
            if (getSupportFragmentManager().getBackStackEntryCount() == 2
                    || getSupportFragmentManager().getBackStackEntryCount() == 3)
                registrationButton.setText(R.string.button_next_to_driver_registration);
        }
    }

    @OnClick(R.id.registration_button_person)
    public void onClickButtonRegestNext() {
        createUser(fragment);
        switch (getSupportFragmentManager().getBackStackEntryCount()) {
            case 1:
                if (!checkBoxDriver && validationFragment(fragment)) {
                    submit();
                    flagRunActivity = true;
                } else
                    fragmentClass = FragmentDriverRegist.class;
                break;
            case 2:
                fragmentClass = FragmentDriverRegistLicen.class;
                break;
            case 3:
                if (validationFragment(fragment)) {
                    submit();
                    flagRunActivity = true;
                }
                break;
        }
        if (getSupportFragmentManager().getBackStackEntryCount() < 3
                && validationFragment(fragment) && flagRunActivity == false) {
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (fragment instanceof FragmentDriverRegistLicen)
                registrationButton.setText(R.string.regist);

            // Вставляем фрагмент, заменяя текущий фрагмент
            FragmentManager fragmentManager = getSupportFragmentManager();
            ++indexCurrentFragment;
            fragmentManager.beginTransaction().replace(R.id.conteiner_registration,
                    fragment, Integer.toString(indexCurrentFragment))
                    .addToBackStack(Integer.toString(indexCurrentFragment)).commit();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
    }

    /**
     * Interface for communicate activity with fragments
     */

    @Override
    public void readFieldOther(boolean checkBox) {
        checkBoxDriver = checkBox;
        if (checkBoxDriver)
            registrationButton.setText(R.string.button_next_to_driver_registration);
        else
            registrationButton.setText(R.string.regist);
    }

    private void submit() {
        userPresenter.registerUser(userRegistration);
    }

    @Override
    public void setNameSideBar(String name) {

    }

    @Override
    public void setEmailSideBar(String email) {

    }

    @Override
    public void showError(int code, String fromServer) {
        Toast.makeText(this, AdapterCodeFromServer.AdapterCode(code, this, fromServer), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doneOperation(int responceCod, String fromServer) {
        Toast.makeText(this, AdapterCodeFromServer.AdapterCode(responceCod, this, fromServer), Toast.LENGTH_SHORT).show();
        if (responceCod == 200)
            runLogInActyvity();
    }

    /**
     * Interface for communicate activity with userPresenter
     */

    private void initializationFragments() {

        fragment = new FragmentPersonRegist();

        // Вставляем фрагмент, заменяя текущий фрагмент

        FragmentManager fragmentManager = getSupportFragmentManager();
        ++indexCurrentFragment;
        fragmentManager.beginTransaction().replace(R.id.conteiner_registration, fragment, Integer.toString(indexCurrentFragment))
                .addToBackStack(Integer.toString(indexCurrentFragment)).commit();
    }

    private void createUser(Fragment fragment) {

        if (fragment instanceof FragmentPersonRegist) {
            FragmentPersonRegist fragmentPassengerRegist = (FragmentPersonRegist) fragment;
            userRegistration.setName(fragmentPassengerRegist.getName());
            userRegistration.setUsername(fragmentPassengerRegist.getEmail());
            userRegistration.setPassword(fragmentPassengerRegist.getPassword());
            List<MobileNumber> mobiles = new ArrayList();
            MobileNumber mobileNumber1 = new MobileNumber();
            MobileNumber mobileNumber2 = new MobileNumber();
            mobileNumber1.setMobileNumber(fragmentPassengerRegist.getPhone1());
            mobiles.add(mobileNumber1);
            if (!fragmentPassengerRegist.getPhone2().matches("")) {
            mobileNumber2.setMobileNumber(fragmentPassengerRegist.getPhone2());
                mobiles.add(mobileNumber2);
            }
            userRegistration.setMobileNumbers(mobiles);
            userRegistration.setUserType("CUSTOMER");
        }

        if (fragment instanceof FragmentDriverRegist) {
            userRegistration.setUserType("TAXI_DRIVER");
            FragmentDriverRegist fragmentDriverRegist = (FragmentDriverRegist) fragment;
            UserRegistrationCar car = new UserRegistrationCar();
            car.setManufacturer(fragmentDriverRegist.getBrand());
            car.setModel(fragmentDriverRegist.getModel());
            car.setPlateNumber(fragmentDriverRegist.getPlateNumber());
            car.setSeatsNumber(Integer.parseInt(fragmentDriverRegist.getNumPassengers()));
            car.setCarType(fragmentDriverRegist.getTypeCar());
            fragmentDriverRegist.getTypeCar();
            userRegistration.setCar(car);
        }

        if (fragment instanceof FragmentDriverRegistLicen) {
            FragmentDriverRegistLicen fragmentDriverRegistLicen
                    = (FragmentDriverRegistLicen) fragment;
            DriverLicense driverLicense = new DriverLicense();
            driverLicense.setCode(fragmentDriverRegistLicen.getDriverLicense());
            driverLicense.setExpirationTime(fragmentDriverRegistLicen.getLicenseExpirationDate());
            userRegistration.setDriverLicense(driverLicense);
        }
    }


    private void runLogInActyvity() {
        Intent myIntent = new Intent(this, LoginActivity.class);
        myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);

    }

    private boolean validationFragment(Fragment fragment) {
        if (fragment instanceof OperationFragment) {

            OperationFragment operationFragment = (OperationFragment) fragment;

            if (operationFragment.validitionFields())
                return true;
            else
                return false;
        } else
            return false;
    }

}
