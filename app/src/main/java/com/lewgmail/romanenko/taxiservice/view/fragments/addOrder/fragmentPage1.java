package com.lewgmail.romanenko.taxiservice.view.fragments.addOrder;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

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
    @BindView(R.id.end_point_act)
    EditText endPointText;

    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    private String dateAndTime;
    // 0 - naw, 1- today, 2- tomorrow;
    private int date;

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
        date = 0;
        uncheckOtherButton(1);
        showHideClock();
    }

    private boolean check2Addresses() {

        if (startPointText.getText().toString().matches("")) {
            startPointText.setError(getResources().getString(R.string.not_inputted_2_point));
            return false;
        }
        if (endPointText.getText().toString().matches("")) {
            endPointText.setError(getResources().getString(R.string.not_inputted_2_point));
            return false;
        } else return true;
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
        date = 1;
        uncheckOtherButton(2);
        showHideClock();
    }

    @OnClick(R.id.radio_button_tomorrow)
    public void onClickButtonTonorrow() {
        date = 2;
        uncheckOtherButton(3);
        showHideClock();
    }

    @OnClick(R.id.start_point_act)
    public void onClickStartPoint() {
        //startAutocompleteFragment();
        addOrderGatherDataFirstWindow.runAutoComplete(R.id.start_point_act, 0);
    }

    @OnClick(R.id.end_point_act)
    public void onClickEndPoint() {
        addOrderGatherDataFirstWindow.runAutoComplete(R.id.end_point_act, 1);
    }


    @OnClick(R.id.add_address_btn)
    public void addAddress() {
        if (check2Addresses())
        addAddressPoint();
    }

    @OnClick(R.id.time_text)
    public void onClickTimePicker() {
        showTimePicker();
    }


    private void showTimePicker() {
        TimePickerDialog.OnTimeSetListener onTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time_text.setText(convertTimeToShow(hourOfDay, minute));

                addOrderGatherDataFirstWindow.setActivityStartTime(
                        convertDateTime(Integer.toString(hourOfDay) + ":" + Integer.toString(minute), date));
            }
        };
        TimePickerFragment time = new TimePickerFragment();
        time.setCallBack(onTimeListener);
        time.show(getFragmentManager(), "Time Picker");
    }

    private String convertTimeToShow(int hour, int minute) {
        String formattedTime = "";
        String sHour = "00";
        if (hour < 10) {
            sHour = "0" + hour;
        } else {
            sHour = String.valueOf(hour);
        }
        String sMinute = "00";
        if (minute < 10) {
            sMinute = "0" + minute;
        } else {
            sMinute = String.valueOf(minute);
        }

        return formattedTime = sHour + ":" + sMinute;
    }

    private String convertDateTime(String dateTime, int dateFlag) {
        String date = null;
        Calendar calendar = new GregorianCalendar();

        if (dateFlag == 0)
            return null;

        if (dateFlag == 1) {
            // today
            SimpleDateFormat dateToday = new SimpleDateFormat("yyyy-MM-dd");
            date = dateToday.format(calendar.getTime());
        }

        if (dateFlag == 2) {
            // tomorrow
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            SimpleDateFormat dateToday = new SimpleDateFormat("yyyy-MM-dd");
            date = dateToday.format(calendar.getTime());
        }

        SimpleDateFormat formatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dateTransfer = null;
        try {
            dateTransfer = formatOne.parse(date + " " + dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat formatTwo = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        String result = formatTwo.format(dateTransfer).toString();

        return result;
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
                                    route.setLayoutParams(new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                                            (route.getAdapter().getCount() - 1) * 200));
                                    addresess.remove(position);
                                    addresessAdapter.notifyDataSetChanged();
                                    addOrderGatherDataFirstWindow.removeAddress(position);

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
        route.setLayoutParams(new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                (route.getAdapter().getCount() + 1) * 200));
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


        void runAutoComplete(int viewId, int position);

        void runAutoComplete(AdapterAddPointOfRoute addapterListAddresses);

        // void runAutoCompliteReplaceAddress(int position);

        void startActivityForResultMap(int editTextId);

        // void startActivityForResultMapRoute(int position);

        void removeAddress(int position);

    }
}
