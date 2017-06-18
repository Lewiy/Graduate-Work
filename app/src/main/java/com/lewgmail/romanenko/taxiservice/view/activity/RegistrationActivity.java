package com.lewgmail.romanenko.taxiservice.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.model.pojo.UserRegistration;
import com.lewgmail.romanenko.taxiservice.model.pojo.UserRegistrationCar;
import com.lewgmail.romanenko.taxiservice.presenter.UserPresenter;
import com.lewgmail.romanenko.taxiservice.presenter.adapters.AdapterCodeFromServer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity implements UserOperationInterface {


    private static final int INDEX_OF_CATEGORY_SPINNER_CAR_TYPE = 1;
    private static final int INDEX_OF_CATEGORY_SPINNER_NUM_PASSENGER = 1;

    @BindView(R.id.name_registration)
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
    Button registrationButton;
    UserPresenter userPresenter;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        userPresenter = new UserPresenter(this);
        initialiseComponent();
    }

   /* private void setVisibleRegistrationDriverOption(){

    }*/

    @OnClick(R.id.check_box_registration)
    public void setVisibleRegistrationDriverOption() {
        hideShowTexiDriverRegisterInform(frameLayoutDriver);

    }

    @OnClick(R.id.registration_button)
    public void registrationButtonClick() {

        if (checkInputedFields()) {
            userPresenter.registerUser(createUser());
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(true); // disable dismiss by tapping outside of the dialog*/
            progress.show();
        }

    }

    private boolean checkInputedFields() {
        if (checkBoxDriverUser.isChecked()) {
            if (checkInputedFieldsDriver()) {
                Toast.makeText(this, getResources().getString(R.string.input_all_fields), Toast.LENGTH_LONG).show();
                return false;
            }
        } else if (checkInputedFieldsCustomer()) {
            Toast.makeText(this, getResources().getString(R.string.input_all_fields), Toast.LENGTH_LONG).show();
            return false;
        }

        if (checkPasswordsEquals()) {
            Toast.makeText(this, "Please check your password fields", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void hideShowTexiDriverRegisterInform(FrameLayout frameLayout) {
        if (frameLayout.getVisibility() != View.VISIBLE) {

            frameLayout.setVisibility(View.VISIBLE);
        } else {

            frameLayout.setVisibility(View.GONE);
        }
    }

    private void initialiseComponent() {

        initializeSpinner();
    }

    private void initializeSpinner() {
        ArrayAdapter<CharSequence> adapterSpinnerCarType =
                ArrayAdapter.createFromResource(this, R.array.set_car_type, android.R.layout.simple_spinner_item);
        adapterSpinnerCarType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerCarType.setAdapter(adapterSpinnerCarType);
        ArrayAdapter<CharSequence> adapterSpinnerNumPassenger =
                ArrayAdapter.createFromResource(this, R.array.set_num_passenger, android.R.layout.simple_spinner_item);
        adapterSpinnerCarType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerNumPassenger.setAdapter(adapterSpinnerNumPassenger);
    }

    public String getName() {
        return nameRegistration.getText().toString();
    }

    public String getEmail() {
        return emailRegistration.getText().toString();
    }

    public String getPassword() {
        return passwordRegistration.getText().toString();
    }

    public String getMobileNambers1() {
        return mobNamberRegistration.getText().toString();
    }

    public String getMobileNambers2() {
        return mobSecondNamber.getText().toString();
    }

    public String getTypeUser() {
        if (checkBoxDriverUser.isChecked())
            return "TAXI_DRIVER";
        else
            return "CUSTOMER";
    }

    public String getManufacturer() {
        return brendRegistration.getText().toString();
    }

    public String getModel() {
        return modelRegistration.getText().toString();
    }

    public String getPlateNumber() {
        return plateNumberRegistration.getText().toString();
    }

    public String getSeatsNumber() {
        return mSpinnerNumPassenger.getSelectedItem().toString();
    }

    public String getCarType() {
        return mSpinnerCarType.getSelectedItem().toString();
    }

    private boolean checkInputedFieldsDriver() {
        if (checkInputedFieldsCustomer() || modelRegistration.getText().toString().matches("")
                || brendRegistration.getText().toString().matches("")
                || plateNumberRegistration.getText().toString().matches("")
                || modelRegistration.getText().toString().matches("")
                || mSpinnerCarType.getSelectedItem() == null
                || mSpinnerNumPassenger.getSelectedItem() == null) {
            return true;
        }
        return false;
    }

    private boolean checkInputedFieldsCustomer() {
        if (nameRegistration.getText().toString().matches("")
                || emailRegistration.getText().toString().matches("")
                || passwordRegistration.getText().toString().matches("")
                || mobNamberRegistration.getText().toString().matches("")) {
            return true;
        } else return false;
    }

    private boolean checkPasswordsEquals() {
        if (passwordRegistration.getText().toString().equals(repeatPassword.getText().toString()))
            return false;
        else return true;
    }

    private UserRegistration createUser() {
        UserRegistration userRegistration = new UserRegistration();

        userRegistration.setName(nameRegistration.getText().toString());
        userRegistration.setUsername(emailRegistration.getText().toString());
        userRegistration.setPassword(passwordRegistration.getText().toString());
        List<String> mobiles = new ArrayList();
        mobiles.add(mobNamberRegistration.getText().toString());
        mobiles.add(mobSecondNamber.getText().toString());
        // userRegistration.setMobileNumbers(mobiles);

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
    public void doneOperation(int responseCod, String fromServer) {
        progress.dismiss();
        Toast.makeText(this, AdapterCodeFromServer.AdapterCode(responseCod, this, fromServer), Toast.LENGTH_LONG).show();
    }
}
