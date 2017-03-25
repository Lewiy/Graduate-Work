package com.lewgmail.romanenko.taxiservice.view.viewDriver;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.model.dataManager.LoggedUser;
import com.lewgmail.romanenko.taxiservice.model.pojo.Car;
import com.lewgmail.romanenko.taxiservice.model.pojo.MobileNumbers;
import com.lewgmail.romanenko.taxiservice.model.pojo.User;
import com.lewgmail.romanenko.taxiservice.presenter.UserPresenter;
import com.lewgmail.romanenko.taxiservice.view.activity.UserOperationInterfaceInfoCustom;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lev on 13.12.2016.
 */

public class EditProfileFragmentDriver extends android.support.v4.app.Fragment implements UserOperationInterfaceInfoCustom {
    // @BindView(R.id.driver__personal_page)
    // FrameLayout checkBoxDriverRegistration;
    @BindView(R.id.set_num_passenger_personal_page)
    Spinner mSpinnerCarType;
    @BindView(R.id.set_car_type_personal_page)
    Spinner mSpinnerNumPassenger;
    @BindView(R.id.name_personal_page)
    EditText namePersonalPage;
    @BindView(R.id.email_personal_page)
    EditText emailPersonalPage;
    @BindView(R.id.number_personal_page)
    EditText phoneNumberPersonalPage;
    @BindView(R.id.secondNumber_personal_page)
    EditText phoneNumberSecondPersonalPage;
    @BindView(R.id.model_personal_page)
    EditText model_PersonalPage;
    @BindView(R.id.plate_personal_page)
    EditText platePersonalPage;
    @BindView(R.id.brand_personal_page)
    EditText brandPersonalPage;
    @BindView(R.id.driver__personal_page)
    FrameLayout driverView;
    private String password;
    private int idMobile1, idMobile2;
    private int carId;
    private ArrayAdapter<CharSequence> adapterSpinnerNumPassenger, adapterSpinnerCarType;
    private UserPresenter userPresenter;
    private ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_page, container, false);
        ButterKnife.bind(this, view);
        progress = new ProgressDialog(getActivity());
        progress.setTitle(getResources().getString(R.string.main_theme_loading));
        progress.setMessage(getResources().getString(R.string.text_of_loading));
        progress.setCancelable(true); // disable dismiss by tapping outside of the dialog
        progress.show();
        initialiseComponent();
        userPresenter = new UserPresenter(this);
        userPresenter.getUserId(LoggedUser.getmInstance().getUserId());
        if (LoggedUser.getmInstance().getUserType().equals("CUSTOMER"))
            driverView.setVisibility(View.GONE);
        return view;
    }

    private void initialiseComponent() {

        initializeSpinner();
    }

    private void initializeSpinner() {
        adapterSpinnerCarType =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.set_car_type, android.R.layout.simple_spinner_item);
        adapterSpinnerCarType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerCarType.setAdapter(adapterSpinnerCarType);
        adapterSpinnerNumPassenger =
                ArrayAdapter.createFromResource(this.getActivity(), R.array.set_num_passenger, android.R.layout.simple_spinner_item);
        adapterSpinnerCarType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerNumPassenger.setAdapter(adapterSpinnerNumPassenger);
    }

    @OnClick(R.id.personal_page_button_ok)
    public void onClickButtOk() {
    }

    @OnClick(R.id.personal_page_button_update)
    public void onClickButtUpDate() {
        userPresenter.updateUser(criateUserUpdateObject());
    }
    //////////////////////////////////update view ////////////////////////////////////

    @Override
    public void setUserName(String name) {
        this.namePersonalPage.setText(name);
    }

    @Override
    public void setUserEmail(String email) {
        progress.dismiss();
        this.emailPersonalPage.setText(email);
    }

    @Override
    public void setPassword(String password) {
    }

    @Override
    public void setRepeatePassword(String password) {
        this.password = new String(password);
    }

    @Override
    public void setUserMobile(String mobile) {
        this.phoneNumberPersonalPage.setText(mobile);
    }

    @Override
    public void setUserMobileId(int id) {
        this.idMobile1 = id;
    }

    @Override
    public void setUserMobile2(String mobile2) {
        this.phoneNumberSecondPersonalPage.setText(mobile2);
    }

    @Override
    public void setUserMobileId2(int id2) {
        this.idMobile2 = id2;
    }

    @Override
    public void setUserDriverBrand(String brand) {
        this.brandPersonalPage.setText(brand);
    }

    @Override
    public void setUserDriverModel(String model) {
        this.model_PersonalPage.setText(model);
    }

    @Override
    public void setUserDriverPlateNumber(String plateNumber) {
        this.platePersonalPage.setText(plateNumber);
    }

    @Override
    public void setUserDriverSeatsNumber(String seatsNumber) {

        this.mSpinnerNumPassenger.setSelection(adapterSpinnerNumPassenger.getPosition(seatsNumber));
    }

    @Override
    public void setUserDriverCarType(String carType) {
        this.mSpinnerCarType.setSelection(adapterSpinnerCarType.getPosition(carType));
    }

    @Override
    public void setCarId(int carId) {
        this.carId = carId;
    }

    @Override
    public void setUserDriverId() {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this.getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doneOperation(String done) {
        Toast.makeText(this.getActivity(), done, Toast.LENGTH_SHORT).show();
    }

    private User criateUserUpdateObject() {
        User user = new User();
        user.setUserId(Long.toString(LoggedUser.getmInstance().getUserId()));
        user.setName(namePersonalPage.getText().toString());
        user.setEmail(emailPersonalPage.getText().toString());
        // user.setPassword(password);
        List<MobileNumbers> mobileNumberses = new ArrayList<>();
        MobileNumbers mobileNumber1 = new MobileNumbers();
        mobileNumber1.setIdMobileNumber(idMobile1);
        mobileNumber1.setMobileNumber(phoneNumberPersonalPage.getText().toString());
        MobileNumbers mobileNumber2 = new MobileNumbers();
        mobileNumber2.setIdMobileNumber(idMobile2);
        mobileNumber2.setMobileNumber(phoneNumberSecondPersonalPage.getText().toString());
        mobileNumberses.add(mobileNumber1);
        mobileNumberses.add(mobileNumber2);
        user.setMobileNumbers(mobileNumberses);
        user.setUserType(LoggedUser.getmInstance().getUserType());
        if (LoggedUser.getmInstance().getUserType().equals("TAXI_DRIVER")) {
            Car car = new Car();
            car.setCarId(carId);
            car.setManufacturer(brandPersonalPage.getText().toString());
            car.setModel(model_PersonalPage.getText().toString());
            car.setPlateNumber(platePersonalPage.getText().toString());
            car.setSeatsNumber(Integer.parseInt(mSpinnerNumPassenger.getSelectedItem().toString()));
            car.setCarType(mSpinnerCarType.getSelectedItem().toString());
            user.setCar(car);
        }
        return user;
    }
}
