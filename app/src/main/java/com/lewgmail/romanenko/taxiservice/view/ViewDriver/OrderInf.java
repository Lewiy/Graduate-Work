package com.lewgmail.romanenko.taxiservice.view.viewDriver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.model.dataManager.LoggedUser;
import com.lewgmail.romanenko.taxiservice.presenter.LoadOrderForDriverPresenter;
import com.lewgmail.romanenko.taxiservice.view.activity.EditOrderInterface;
import com.lewgmail.romanenko.taxiservice.view.adapters.AdapterStatusController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lev on 16.12.2016.
 */

public class OrderInf extends AppCompatActivity implements EditOrderInterface {

    @BindView(R.id.spinner_driver_inf_order)
    Spinner spinnerStatusChange;
    @BindView(R.id.inf_time_ride)
    TextView timeOfRideField;
    @BindView(R.id.inf_driver_route)
    ListView route;
    @BindView(R.id.value_distance)
    TextView distance;
    @BindView(R.id.value_duration)
    TextView duration;
    @BindView(R.id.value_price)
    TextView price;
    @BindView(R.id.text_view_addit_baggage)
    TextView baggage;
    @BindView(R.id.text_view_type_reckoning)
    TextView reckoning;
    @BindView(R.id.text_view_extra_price)
    TextView extraPrice;
    @BindView(R.id.text_view_pets)
    TextView pets;
    @BindView(R.id.text_view_driver_service)
    TextView service;
    @BindView(R.id.text_view_type_car)
    TextView typeCar;
    @BindView(R.id.text_view_number_passengers)
    TextView numberOfPassengers;
    @BindView(R.id.comment_element)
    EditText comment;
    private LoadOrderForDriverPresenter loadOrderForDriverPresenter;
    private Intent intentMy;
    private int counterMy;
    private long customerId, orderId;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_inf_driver);
        ButterKnife.bind(this);
        loadOrderForDriverPresenter = new LoadOrderForDriverPresenter(this);
        intentMy = getIntent();
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(true); // disable dismiss by tapping outside of the dialog
        progress.show();
        // sdf = intentMy.getStringExtra("keyNumberOfOrder");
        // lol = Integer.parseInt(intentMy.getStringExtra("keyNumberOfOrder"));
        loadOrderForDriverPresenter.loadOrderSpecificId(Integer.parseInt(intentMy.getStringExtra("keyNumberOfOrder")));


    }

    private void init() {
        /*addresess = new ArrayList<>();
        addresessAdapter = new AdapterAddPointOfRoute(this, R.layout.address_point, addresess, route);
        /*SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        route,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {

                                    addresess.remove(position);
                                    addresessAdapter.notifyDataSetChanged();
                                    addOrderGatherDataFirstWindow.removeAddress(position);

                                }
                            }
                        });*/
        //  addresessAdapter.setActivityCallBack();
       /* route.setAdapter(addresessAdapter);
        route.setOnTouchListener(touchListener);*/

    }

    @OnClick(R.id.buttn_order_driv_ok)
    public void okClickButton() {

    }

    @OnClick(R.id.customer_account_info)
    public void customerInfoClickButton() {
        PassengerAccountInfFragment passengerInfFragment = new PassengerAccountInfFragment();
        Bundle args = new Bundle();
        args.putLong("customerId", customerId);
        passengerInfFragment.setArguments(args);
        passengerInfFragment.show(getFragmentManager(), "ldld");
    }

    public void setSpinnerStatusChange(String spinnerStatusChange) {
        //   this.spinnerStatusChange = spinnerStatusChange;
    }



    public void setDistanceInf(String distanceInf) {
    }

    @Override
    public void setDistance(String value) {
        this.distance.setText(value);
    }

    @Override
    public void setDuration(String duration) {
        this.duration.setText(duration);
    }

    @Override
    public void setPrice(double price) {
        this.price.setText(Double.toString(price));
    }

    @Override
    public void setDateRide(String date) {
        this.timeOfRideField.setText(date);
    }

    @Override
    public void setTimeRide(String time) {

    }

    @Override
    public void setNameCastomer(String info) {

    }

    @Override
    public void setNameDriver(String name) {
        //this.driverInf.setText(name);
    }

    @Override
    public void setTypeOrder(String status) {
        //  this.statusInf.setText(status);
        initializeComponent(status);
        progress.dismiss();
    }

    @Override
    public String getStartPoint() {
        return null;
    }

    @Override
    public void setStartPoint(String startPoint) {
        // this.point1Inform.setText(startPoint);
    }

    @Override
    public String getEndPoint() {
        return null;
    }

    @Override
    public void setEndPoint(String endPoint) {
        //this.point2Inform.setText(endPoint);
    }

    @Override
    public String getTypeCar() {
        return null;
    }

    @Override
    public void setTypeCar(String typeCar) {
        // this.typeCarInf.setText(typeCar);
    }

    @Override
    public String getTypeReckoning() {
        return null;
    }

    @Override
    public void setTypeReckoning(String typeReckoning) {
        //this.typeReckoningInf.setText(typeReckoning);
    }

    @Override
    public String getTime() {
        return null;
    }

    @Override
    public String getDate() {
        return null;
    }

    @Override
    public void setCustomerId(long id) {
        this.customerId = id;
    }

    @Override
    public void setOrderId(long id) {
        this.orderId = id;
    }

    @Override
    public void setDriverId(long id) {

    }

    private void initializeComponent(String status) {

        ArrayAdapter<CharSequence> adapterSpinnerTypeReckoning =
                ArrayAdapter.createFromResource(this,
                        new AdapterStatusController(LoggedUser.getmInstance().getUserType(), status).typeStatus(),
                        android.R.layout.simple_spinner_item);
        adapterSpinnerTypeReckoning.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Log.d(" caunter ", Integer.toString(counterMy));
        spinnerStatusChange.setAdapter(adapterSpinnerTypeReckoning);

        spinnerStatusChange.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                       long id) {
                Log.d(" caunter ", Integer.toString(counterMy));
                if (!spinnerStatusChange.getSelectedItem().toString().equals("Nothing selected"))
                    if (spinnerStatusChange.getSelectedItem().toString().equals("Cancel")) {
                        // basePresenter.changeStatusOrder(orderId, 2, "NEW");
                    }
                // basePresenter.changeStatusOrder(orderId, LoggedUser.getmInstance().getUserId(), parent.getItemAtPosition(pos).toString().toUpperCase());

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
