package com.lewgmail.romanenko.taxiservice.view.viewDriver;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.model.dataManager.LoggedUser;
import com.lewgmail.romanenko.taxiservice.presenter.UserPresenter;
import com.lewgmail.romanenko.taxiservice.presenter.adapters.AdapterCodeFromServer;
import com.lewgmail.romanenko.taxiservice.view.activity.InfoUser;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lev on 17.12.2016.
 */

public class AccountInfUserFrag extends DialogFragment implements InfoUser {

    @BindView(R.id.name_var)
    TextView name;
    @BindView(R.id.email_var)
    TextView email;
    @BindView(R.id.mobile_var)
    TextView mobile;
    @BindView(R.id.brand_var)
    TextView brand;
    @BindView(R.id.model_var)
    TextView model;
    @BindView(R.id.palte_num_var)
    TextView plateNumber;
    @BindView(R.id.seats_number_var)
    TextView seatsNumber;
    @BindView(R.id.car_type_var)
    TextView carType;
    @BindView(R.id.frame_driver_info)
    FrameLayout driverInfo;
    private UserPresenter userPresenter;
    private long customerId;
    private ProgressDialog progress1;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.fragment_passenger_info, null);
        builder.setView(view)
                .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        ButterKnife.bind(this, view);
        adapterUserSettings();
        userPresenter = new UserPresenter(this);
        customerId = getArguments().getLong("userId");
        userPresenter.getUserId(customerId);
        return builder.create();
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setEmail(String email) {
        this.email.setText(email);
        // progress1.dismiss();
    }

    public void setMobile(String mobile) {
        this.mobile.setText(mobile);
    }

    public void setBrand(String brand) {
        this.brand.setText(brand);
    }

    public void setModel(String model) {
        this.model.setText(model);
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber.setText(plateNumber);
    }

    public void setSeatsNumber(String seatsNumber) {
        this.seatsNumber.setText(seatsNumber);
    }

    public void setCarType(String carType) {
        this.carType.setText(carType);
    }

    private void adapterUserSettings() {
        if (LoggedUser.getmInstance().getUserType().equals("TAXI_DRIVER"))
            driverInfo.setVisibility(View.GONE);
    }

    @Override
    public void setUserName(String name) {
        this.name.setText(name);
    }

    @Override
    public void setUserEmail(String email) {
        this.email.setText(email);
    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public void setRepeatePassword(String password) {

    }

    @Override
    public void setUserMobile(String mobile) {
        this.mobile.setText(mobile);
    }

    @Override
    public void setUserMobileId(int id) {

    }

    @Override
    public void setUserMobile2(String mobile2) {

    }

    @Override
    public void setUserMobileId2(int id2) {

    }

    @Override
    public void setUserDriverBrand(String brand) {
        this.brand.setText(brand);
    }

    @Override
    public void setUserDriverModel(String model) {
        this.model.setText(model);
    }

    @Override
    public void setUserDriverPlateNumber(String plateNumber) {
        this.plateNumber.setText(plateNumber);
    }

    @Override
    public void setUserDriverSeatsNumber(String seatsNumber) {
        this.seatsNumber.setText(seatsNumber);
    }

    @Override
    public void setUserDriverCarType(String carType) {
        this.carType.setText(carType);
    }

    @Override
    public void setCarId(int carId) {

    }

    @Override
    public void setUserDriverId() {
    }

    @Override
    public void showError(int code) {
        Toast.makeText(this.getActivity(), AdapterCodeFromServer.AdapterCode(code, this.getActivity()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doneOperation(int code) {
        Toast.makeText(this.getActivity(), AdapterCodeFromServer.AdapterCode(code, this.getActivity()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCodeLicense(String code) {

    }

    @Override
    public void setExpirationTime(String expirationTime) {

    }


}
