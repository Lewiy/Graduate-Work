package com.lewgmail.romanenko.taxiservice.view.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.model.dataManager.LoggedUser;
import com.lewgmail.romanenko.taxiservice.presenter.BasePresenter;
import com.lewgmail.romanenko.taxiservice.presenter.CustomerPresenter;
import com.lewgmail.romanenko.taxiservice.presenter.MapGooglePresenter;
import com.lewgmail.romanenko.taxiservice.view.adapters.AdapterStatusController;
import com.lewgmail.romanenko.taxiservice.view.dialogFragment.DatePickerFragment;
import com.lewgmail.romanenko.taxiservice.view.dialogFragment.TimePickerFragment;
import com.lewgmail.romanenko.taxiservice.view.viewDriver.PassengerAccountInfFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lev on 29.11.2016.
 */

public class EditOrderActivity extends FragmentActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, EditOrderInterface {

    @BindView(R.id.start_point_act)
    EditText startPointAct;
    @BindView(R.id.end_point_act)
    EditText endPointAct;
    @BindView(R.id.date_ride_act)
    EditText dateRideAct;
    @BindView(R.id.time_ride_act)
    EditText timeRideAct;
    @BindView(R.id.set_status_order_act)
    Spinner mSpinnerStatusType;
    @BindView(R.id.value_requirements_car)
    Spinner mSpinnerCarType;
    @BindView(R.id.additional_requirements_value_reckoning)
    Spinner mSpinnerReckoning;
    @BindView(R.id.value_distance)
    TextView textViewValueDistanse;
    @BindView(R.id.value_price)
    TextView textViewValuePrice;
    @BindView(R.id.value_duration)
    TextView textViewValueDuration;
    @BindView(R.id.value_customer_taxi_name)
    TextView valueCastomerName;
    @BindView(R.id.value_driver_taxi_name)
    TextView valueTaxiDriverName;
    @BindView(R.id.decline_edit_order)
    Button declineButton;
    @BindView(R.id.order_status_customer_update)
    TextView orderStatusValue;
    @BindView(R.id.customer_button_profile)
    Button driverAccountButton;
    @BindView(R.id.calculate_price_edit_order)
    Button buttonCulculate;

    private double latitude1, longetude1, latitude2, longetude2, latitude11, longetude11, latitude22, longetude22;
    private MapGooglePresenter mapGooglePresenter;
    private CustomerPresenter customerPresenter;
    private BasePresenter basePresenter;
    private Intent intentMy;
    private long orderId, driverId;
    private ProgressDialog progress;


    private ArrayAdapter<CharSequence> adapterSpinnerCarType, adapterSpinnerTypeStatusOrder,
            adapterSpinnerTypeReckoning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_oreder_client);
        ButterKnife.bind(this);
        mapGooglePresenter = new MapGooglePresenter(this);
        customerPresenter = new CustomerPresenter(this);
        //  basePresenter = new BasePresenter(this);
        intentMy = getIntent();
        declineButton.setEnabled(false);
        initializeSpinner("ADD");
        orderStatusValue.setText("NEW");
        driverAccountButton.setEnabled(false);
        if (intentMy.getStringExtra("keyUpdate") != null) {
            progress = new ProgressDialog(this);
            progress.setTitle(R.string.main_theme_loading);
            progress.setMessage(getResources().getString(R.string.text_of_loading));
            progress.setCancelable(true); // disable dismiss by tapping outside of the dialog
            progress.show();
            basePresenter.loadOrderSpecificId(Integer.parseInt(intentMy.getStringExtra("keyNumberOfOrder")));
            declineButton.setEnabled(true);
            buttonCulculate.setEnabled(false);
        }
    }

    @OnClick(R.id.start_point_act)
    public void onClickStartPoint() {
        Intent myIntent = new Intent(EditOrderActivity.this, MapActivity.class);
        myIntent.putExtra("keyAddressFromMarker", "StartPoint");//Optional parameters
        EditOrderActivity.this.startActivityForResult(myIntent, 1);
    }

    @OnClick(R.id.end_point_act)
    public void onClickEndPoint() {
        Intent myIntent = new Intent(EditOrderActivity.this, MapActivity.class);
        myIntent.putExtra("keyAddressFromMarker", "EndPoint"); //Optional parameters
        EditOrderActivity.this.startActivityForResult(myIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (longetude1 == 0.0) {
            longetude1 = data.getDoubleExtra("longitude", 1.0);

            latitude1 = data.getDoubleExtra("latitude", 1.0);
        } else {
            longetude2 = data.getDoubleExtra("longitude", 1.0);

            latitude2 = data.getDoubleExtra("latitude", 1.0);
        }
        String address = data.getStringExtra("StartPoint");
        if (address != null)
            startPointAct.setText(address);

        address = data.getStringExtra("EndPoint");
        if (address != null)
            endPointAct.setText(address);

    }

    @OnClick(R.id.date_ride_act)
    public void onClickDataPiker() {
        DatePickerFragment dialog = new DatePickerFragment();
        dialog.show(getFragmentManager(), "datePicker");
        // dialog.show(fm, DIALOG_DATE);
    }

    @OnClick(R.id.time_ride_act)
    public void onClickTimePicker() {
        TimePickerFragment dialog = new TimePickerFragment();
        dialog.show(getSupportFragmentManager(), "timePicker");
    }

    @OnClick(R.id.customer_button_profile)
    public void onClickProfileDriver() {
        PassengerAccountInfFragment passengerInfFragment = new PassengerAccountInfFragment();
        Bundle args = new Bundle();
        args.putLong("customerId", driverId);
        passengerInfFragment.setArguments(args);
        passengerInfFragment.show(getFragmentManager(), "ldld");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        dateRideAct.setText(Integer.toString(dayOfMonth) + "/" + Integer.toString(monthOfYear)
                + "/" + Integer.toString(year));
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        timeRideAct.setText(Integer.toString(hourOfDay) + ":" + Integer.toString(minute));
    }

    @OnClick(R.id.calculate_price_edit_order)
    public void onClickCalculatePrise() {

        if (chekInputedFieldForCalculated()) {
            mapGooglePresenter.calculatePrice(longetude1, latitude1, longetude2, latitude2, customerPresenter);
            longetude11 = longetude1;
            latitude11 = latitude1;
            longetude22 = longetude2;
            latitude22 = latitude2;
            longetude1 = 0.0;
        }
    }

    @OnClick(R.id.calendar_button)
    public void onClickbuttoncalnedar() {
        DatePickerFragment dialog = new DatePickerFragment();
        dialog.show(getFragmentManager(), "datePicker");
    }

    @OnClick(R.id.time_button_picker)
    public void onClickTimeButton() {
        TimePickerFragment dialog = new TimePickerFragment();
        // dialog.show(getFragmentManager(), "timePicker");
    }

    @OnClick(R.id.point1_map)
    public void onClickpoint1Button() {
        Intent myIntent = new Intent(EditOrderActivity.this, MapActivity.class);
        myIntent.putExtra("keyAddressFromMarker", "StartPoint");//Optional parameters
        EditOrderActivity.this.startActivityForResult(myIntent, 1);
    }

    @OnClick(R.id.point2_map)
    public void onClickpoint2Button() {
        Intent myIntent = new Intent(EditOrderActivity.this, MapActivity.class);
        myIntent.putExtra("keyAddressFromMarker", "EndPoint"); //Optional parameters
        EditOrderActivity.this.startActivityForResult(myIntent, 1);
    }

    @OnClick(R.id.accept_edit_order)
    public void onClickAcceptOrder() {
       /* if (intentMy.getStringExtra("keyUpdate") != null) {
            if (intentMy.getStringExtra("keyUpdate").equals("update")) {
                if (chekInputedFieldAll())
                    customerPresenter.updateOrder();
            }
        } else {
            if (chekInputedFieldAll())
                if (longetude1 == 0.0)
                    customerPresenter.addOrder(longetude11, latitude11, longetude22, latitude22);
                else customerPresenter.addOrder(longetude1, latitude1, longetude2, latitude2);


        }*/
    }

    @OnClick(R.id.ok_edit_order)
    public void onClickOk() {
        finish();
    }

    @OnClick(R.id.decline_edit_order)
    public void onClickDecline() {

        customerPresenter.deleteOrder(orderId);
    }


    private void initializeSpinner(String status) {

        adapterSpinnerTypeStatusOrder =
                ArrayAdapter.createFromResource(this,
                        new AdapterStatusController(LoggedUser.getmInstance().getUserType(), status).typeStatus(), android.R.layout.simple_spinner_item);
        adapterSpinnerTypeStatusOrder.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerStatusType.setAdapter(adapterSpinnerTypeStatusOrder);

        adapterSpinnerCarType =
                ArrayAdapter.createFromResource(this, R.array.set_car_type, android.R.layout.simple_spinner_item);
        adapterSpinnerCarType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerCarType.setAdapter(adapterSpinnerCarType);

        adapterSpinnerTypeReckoning =
                ArrayAdapter.createFromResource(this, R.array.set_type_reckoning, android.R.layout.simple_spinner_item);
        adapterSpinnerTypeReckoning.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerReckoning.setAdapter(adapterSpinnerTypeReckoning);

        mSpinnerStatusType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                       long id) {
                // basePresenter.loadOrdersList(parent.getItemAtPosition(pos).toString());
                // basePresenter.changeStatusOrder(7);
                //Log.d("My loooooog","click");
                if (parent.getItemAtPosition(pos).toString().equals("Cancelled")) {
                    basePresenter.changeStatusOrder(orderId, LoggedUser.getmInstance().getUserId(), parent.getItemAtPosition(pos).toString().toUpperCase());
                    finish();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    private boolean chekInputedFieldForCalculated() {
        if (startPointAct.getText().toString().matches("") ||
                endPointAct.getText().toString().matches("") ||
                mSpinnerReckoning.getSelectedItem() == null ||
                mSpinnerCarType.getSelectedItem() == null) {
            Toast.makeText(this, getResources().getString(R.string.input_all_fields), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean chekInputedFieldAll() {
        if (!chekInputedFieldForCalculated() ||
                dateRideAct.getText().toString().matches("") ||
                timeRideAct.getText().toString().matches("") ||
                mSpinnerStatusType.getSelectedItem() == null) {
            Toast.makeText(this, getResources().getString(R.string.input_all_fields), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    /////////////////////////////////////update view////////////////////////////////////////////////
    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    public void setDistance(String value) {
        textViewValueDistanse.setText(value);
    }

    @Override
    public void setDuration(String duration) {
        textViewValueDuration.setText(duration);
    }

    @Override
    public void setPrice(double price) {
        textViewValuePrice.setText(Double.toString(price));
    }

    @Override
    public void setDateRide(String date) {
        dateRideAct.setText(date);
    }

    @Override
    public void setTimeRide(String time) {
        timeRideAct.setText(time);
    }

    @Override
    public void setNameCastomer(String name) {
        valueCastomerName.setText(name);
    }

    @Override
    public void setNameDriver(String name) {
        valueTaxiDriverName.setText(name);
    }

    @Override
    public void setTypeOrder(String typeOrder) {
        initializeSpinner(typeOrder);
        mSpinnerStatusType.setSelection(adapterSpinnerTypeStatusOrder.getPosition(typeOrder));
        orderStatusValue.setText(typeOrder);
        progress.dismiss();
    }

    public void dateRide(String date) {

    }

    public String getStartPoint() {
        return startPointAct.getText().toString();
    }

    @Override
    public void setStartPoint(String startPoint) {
        startPointAct.setText(startPoint);
    }

    public String getEndPoint() {
        return endPointAct.getText().toString();
    }

    @Override
    public void setEndPoint(String endPoint) {
        endPointAct.setText(endPoint);
    }


    /////////////////////////////////////read fields ///////////////////////////////////////////////

    public String getTypeCar() {
        return mSpinnerCarType.getSelectedItem().toString();
    }

    @Override
    public void setTypeCar(String typeCar) {
        mSpinnerCarType.setSelection(adapterSpinnerCarType.getPosition(typeCar));
    }

    public String getTypeReckoning() {
        String sdf = mSpinnerReckoning.getSelectedItem().toString();
        return sdf;
    }

    @Override
    public void setTypeReckoning(String typeReckoning) {
        mSpinnerReckoning.setSelection(adapterSpinnerTypeReckoning.getPosition(typeReckoning));
    }

    public String getTime() {
        return timeRideAct.getText().toString();
    }

    public String getDate() {
        return dateRideAct.getText().toString();
    }

    @Override
    public void setCustomerId(long id) {

    }

    @Override
    public void setOrderId(long id) {
        this.orderId = id;
    }

    @Override
    public void setDriverId(long id) {
        this.driverId = id;
        if (driverId != 0)
            driverAccountButton.setEnabled(true);
    }

}
