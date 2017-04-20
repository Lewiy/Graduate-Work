package com.lewgmail.romanenko.taxiservice.view.fragments.addOrder;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.view.adapters.AdapterAddPointOfRoute;
import com.lewgmail.romanenko.taxiservice.view.adapters.SwipeDismissListViewTouchListener;
import com.lewgmail.romanenko.taxiservice.view.dialogFragment.TimePickerFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by Lev on 18.03.2017.
 */

public class FragmentPage1 extends android.support.v4.app.Fragment {

    @BindView(R.id.radio_button_now)
    RadioButton radioButtonNow;
    @BindView(R.id.radio_button_today)
    RadioButton radioButtonToday;
    @BindView(R.id.radio_button_tomorrow)
    RadioButton radioButtonTomorrow;
    @BindView(R.id.frame_clock)
    FrameLayout frameLayoutClock;
    @BindView(R.id.address_linear_point)
    LinearLayout addressLinear;
    @BindView(R.id.addresses_liner)
    LinearLayout addressesLinear;
    @BindView(R.id.route)
    ListView route;
    @BindView(R.id.time_text)
    TextView time_text;

    @BindView(R.id.start_point_act)
    EditText startPointText;


    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    private AddOrderGatherDataFirstWindow addOrderGatherDataFirstWindow;
    private AdapterAddPointOfRoute addresessAdapter;
    private ArrayList<String> addresess;
    private LocationManager locationManager;
    private LocationListener locationListener;
    public FragmentPage1() {
    }


    public static FragmentPage1 newInstance(int sectionNumber) {
        FragmentPage1 fragment = new FragmentPage1();
        Bundle args = new Bundle();
        args.putInt("key", sectionNumber);
        fragment.setArguments(args);
        // FragmentManager fragmentManager =  getFragmentManager();
        // fragmentManager.beginTransaction().add(fragment1,"fragmentAddOrder1").commit();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_order_1, container, false);
        ButterKnife.bind(this, rootView);
        // init();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity addOrder;
        if (context instanceof Activity) {
            addOrder = (Activity) context;
            addOrderGatherDataFirstWindow = (AddOrderGatherDataFirstWindow) addOrder;
        }
    }

    @OnClick(R.id.radio_button_now)
    public void onClickButtonNow() {
        uncheckOtherButton(1);
        showHideClock();
    }

    @OnClick(R.id.point1_map)
    public void onClickMap() {
        startMapActivity(R.id.start_point_act);

    }

    @OnClick(R.id.point2_map)
    public void onClickMap2() {
        startMapActivity(R.id.end_point_act);

    }

    @OnClick(R.id.my_location_button)
    public void onClickMyLocation() {
        //  Location location = new Location();
        // location.init();
        initLocationManager();
    }

    @OnClick(R.id.radio_button_today)
    public void onClickButtonToday() {
        uncheckOtherButton(2);
        showHideClock();
    }

    @OnClick(R.id.radio_button_tomorrow)
    public void onClickButtonTonorrow() {
        uncheckOtherButton(3);
        showHideClock();
    }

    @OnClick(R.id.start_point_act)
    public void onClickStartPoint() {
        //startAutocompleteFragment();
        addOrderGatherDataFirstWindow.runAutoComplete(R.id.start_point_act);
    }

    @OnClick(R.id.end_point_act)
    public void onClickEndPoint() {
        addOrderGatherDataFirstWindow.runAutoComplete(R.id.end_point_act);
    }


    @OnClick(R.id.add_address_btn)
    public void addAddress() {
        addAddressPoint();
    }

    @OnClick(R.id.time_button_picker)
    public void onClickTimePicker() {
        showTimePicker();
    }

    private void showTimePicker() {
        TimePickerDialog.OnTimeSetListener onTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time_text.setText(Integer.toString(hourOfDay) + ":" + Integer.toString(minute));
            }
        };
        TimePickerFragment time = new TimePickerFragment();
        time.setCallBack(onTimeListener);
        time.show(getFragmentManager(), "Time Picker");
    }

    private void initializeView() {

    }

    public void initLocationManager() {
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(android.location.Location location) {
                location.getLatitude();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

    }

    private void init() {
        addresess = new ArrayList<>();
        addresessAdapter = new AdapterAddPointOfRoute(getActivity(), R.layout.address_point, addresess, route);
        SwipeDismissListViewTouchListener touchListener =
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

                                }
                            }
                        });
        //  addresessAdapter.setActivityCallBack();
        route.setAdapter(addresessAdapter);
        route.setOnTouchListener(touchListener);

    }

    private void showHideClock() {
        if (radioButtonTomorrow.isChecked() || radioButtonToday.isChecked())
            frameLayoutClock.setVisibility(View.VISIBLE);
        else
            frameLayoutClock.setVisibility(View.GONE);
    }

    private void uncheckOtherButton(int index) {
        switch (index) {
            case 1:
                radioButtonTomorrow.setChecked(false);
                radioButtonToday.setChecked(false);
                break;
            case 2:
                radioButtonTomorrow.setChecked(false);
                radioButtonNow.setChecked(false);
                break;
            case 3:
                radioButtonNow.setChecked(false);
                radioButtonToday.setChecked(false);
        }
    }

    private void addAddressPoint() {
        //run
        //  addresessAdapter.add("sdfklnfg");
        addOrderGatherDataFirstWindow.runAutoComplete(addresessAdapter);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    // getLocation();
                    locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
                return;
        }
    }

    private void startMapActivity(int editTextId) {
       /* Intent myIntent = new Intent(getActivity(), MapActivity.class);
        myIntent.putExtra("keyAddressFromMarker", "StartPoint"); //Optional parameters
        getActivity().startActivity(myIntent);*/
        addOrderGatherDataFirstWindow.startActivityForResultMap(editTextId);
    }

    public interface AddOrderGatherDataFirstWindow {
        void setActivityStartTime(String startTime);

        void setActivityAdminArea(String adminArea);

        void setActivityLatitude(String latitude);

        void setActivityLongetude(String longetude);

        void setActivityStreet(String street);

        void setActivityHouseNumber(String houseNumber);

        void setActivityCity(String city);

        void runAutoComplete(int viewId);

        void runAutoComplete(AdapterAddPointOfRoute addapterListAddresses);

        void runAutoCompliteReplaceAddress(int position);

        void startActivityForResultMap(int editTextId);

        void startActivityForResultMapRoute(int position);

    }
}
