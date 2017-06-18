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
import com.lewgmail.romanenko.taxiservice.model.pojo.UpdateUser;
import com.lewgmail.romanenko.taxiservice.model.pojo.UpdateUserDriverLicense;
import com.lewgmail.romanenko.taxiservice.presenter.UserPresenter;
import com.lewgmail.romanenko.taxiservice.presenter.adapters.AdapterCodeFromServer;
import com.lewgmail.romanenko.taxiservice.view.ValidationOfFields;
import com.lewgmail.romanenko.taxiservice.view.activity.InfoUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lev on 13.12.2016.
 */

public class EditProfileFragmentDriver extends android.support.v4.app.Fragment implements InfoUser {
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
    @BindView(R.id.code_license)
    EditText codeLicense;
    @BindView(R.id.expirationTime_license)
    EditText expirationTimeLicense;
    @BindView(R.id.driver__personal_page)
    FrameLayout driverView;
    private String password;
    private Integer idMobile1, idMobile2;
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


    private boolean checkInputedInfo() {
        boolean checkFlag = true;
        String massege;
        massege = ValidationOfFields.checkCodeLicense(this.getContext(), codeLicense.getText().toString());
        if (!massege.equals("true")) {
            codeLicense.setError(massege);
            return false;
        }
        massege = ValidationOfFields.checkExpirationTime(this.getContext(), expirationTimeLicense.getText().toString());
        if (!massege.equals("true")) {
            expirationTimeLicense.setError(massege);
            return false;
        }
        if (!phoneNumberPersonalPage.getText().toString().matches("")) {
            massege = ValidationOfFields.checkPhoneNumber(this.getContext(), phoneNumberPersonalPage.getText().toString());
            if (!massege.equals("true")) {
                phoneNumberPersonalPage.setError(massege);
                return false;
            }
        }

        if (!phoneNumberSecondPersonalPage.getText().toString().matches("")) {
            massege = ValidationOfFields.checkPhoneNumber(this.getContext(), phoneNumberSecondPersonalPage.getText().toString());
            if (!massege.equals("true")) {
                phoneNumberSecondPersonalPage.setError(massege);
                return false;
            }
        }
        massege = ValidationOfFields.checkPlateNumber(this.getContext(), platePersonalPage.getText().toString());
        if (!massege.equals("true")) {
            platePersonalPage.setError(massege);
            return false;
        }
        checkFlag = validationEmptyField(codeLicense);
        if (checkFlag == false)
            return false;
        checkFlag = validationEmptyField(platePersonalPage);
        if (checkFlag == false)
            return false;
        checkFlag = validationEmptyField(phoneNumberPersonalPage);
        if (checkFlag == false)
            return false;
        checkFlag = validationEmptyField(expirationTimeLicense);
        if (checkFlag == false)
            return false;
        checkFlag = validationEmptyField(namePersonalPage);
        if (checkFlag == false)
            return false;
        checkFlag = validationEmptyField(brandPersonalPage);
        if (checkFlag == false)
            return false;
        checkFlag = validationEmptyField(model_PersonalPage);
        return checkFlag;
    }

    private boolean validationField(EditText editTextField) {
        String massege;
        massege = ValidationOfFields.checkExpirationTime(this.getContext(), editTextField.getText().toString());
        if (!massege.equals("true")) {
            editTextField.setError(massege);
            return false;
        }
        return true;
    }

    private boolean validationEmptyField(EditText editText) {
        if (editText.getText().toString().matches("")) {
            editText.setError(getContext().getResources().getString(R.string.validation_obligatory_field));
            return false;
        }

        return true;
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
        if (checkInputedInfo())
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
    public void showError(int code, String fromServer) {
        Toast.makeText(this.getActivity(), AdapterCodeFromServer.AdapterCode(code, this.getActivity(), fromServer), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doneOperation(int code, String fromServer) {
        Toast.makeText(this.getActivity(), AdapterCodeFromServer.AdapterCode(code, this.getActivity(), fromServer), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCodeLicense(String code) {
        codeLicense.setText(code);
    }

    @Override
    public void setExpirationTime(String expirationTime) {
        expirationTimeLicense.setText(expirationTime);
    }

    private UpdateUser criateUserUpdateObject() {
        UpdateUser user = new UpdateUser();
        // user.setUserId(Long.toString(LoggedUser.getmInstance().getUserId()));
        user.setName(namePersonalPage.getText().toString());
        // user.setEmail(emailPersonalPage.getText().toString());
        // user.setPassword(password);
        List<MobileNumbers> mobileNumberses = new ArrayList<>();

        if (!phoneNumberPersonalPage.getText().toString().matches("") && idMobile1 != null) {
            MobileNumbers mobileNumber1 = new MobileNumbers();
            mobileNumber1.setIdMobileNumber(idMobile1);
            mobileNumber1.setMobileNumber(phoneNumberPersonalPage.getText().toString());
            mobileNumberses.add(mobileNumber1);
        } else if (idMobile1 != null && phoneNumberPersonalPage.getText().toString().matches("")) {
            MobileNumbers mobileNumber1 = new MobileNumbers();
            mobileNumber1.setIdMobileNumber(idMobile1);
            mobileNumber1.setMobileNumber(null);
            mobileNumberses.add(mobileNumber1);
            idMobile1 = null;
        } else if (idMobile1 == null && !phoneNumberPersonalPage.getText().toString().matches("")) {
            MobileNumbers mobileNumber1 = new MobileNumbers();
            mobileNumber1.setIdMobileNumber(idMobile1);
            mobileNumber1.setMobileNumber(phoneNumberPersonalPage.getText().toString());
            mobileNumberses.add(mobileNumber1);
        }


        // не понятний айді
        if (!phoneNumberSecondPersonalPage.getText().toString().matches("") && idMobile2 != null) {
            MobileNumbers mobileNumber2 = new MobileNumbers();
            mobileNumber2.setIdMobileNumber(idMobile2);
            mobileNumber2.setMobileNumber(phoneNumberSecondPersonalPage.getText().toString());
            mobileNumberses.add(mobileNumber2);
        } else if (idMobile2 != null && phoneNumberSecondPersonalPage.getText().toString().matches("")) {
            MobileNumbers mobileNumber1 = new MobileNumbers();
            mobileNumber1.setIdMobileNumber(idMobile2);
            mobileNumber1.setMobileNumber(null);
            mobileNumberses.add(mobileNumber1);
            idMobile2 = null;
        } else if (idMobile2 == null && !phoneNumberSecondPersonalPage.getText().toString().matches("")) {
            MobileNumbers mobileNumber1 = new MobileNumbers();
            mobileNumber1.setIdMobileNumber(idMobile2);
            mobileNumber1.setMobileNumber(phoneNumberSecondPersonalPage.getText().toString());
            mobileNumberses.add(mobileNumber1);
        }


        user.setMobileNumbers(mobileNumberses);
        //   user.setUserType(LoggedUser.getmInstance().getUserType());
        if (LoggedUser.getmInstance().getUserType().equals("TAXI_DRIVER")) {
            Car car = new Car();
            // car.setCarId(carId);
            car.setManufacturer(brandPersonalPage.getText().toString());
            car.setModel(model_PersonalPage.getText().toString());
            car.setPlateNumber(platePersonalPage.getText().toString());
            car.setSeatsNumber(Integer.parseInt(mSpinnerNumPassenger.getSelectedItem().toString()));
            car.setCarType(mSpinnerCarType.getSelectedItem().toString());
            user.setCar(car);
            UpdateUserDriverLicense driverLicense = new UpdateUserDriverLicense();
            driverLicense.setCode(codeLicense.getText().toString());
            driverLicense.setExpirationTime(expirationTimeLicense.getText().toString());
            user.setDriverLicense(driverLicense);
        }
        return user;
    }
}
