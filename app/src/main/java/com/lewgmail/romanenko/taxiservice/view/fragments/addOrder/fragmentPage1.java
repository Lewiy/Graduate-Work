package com.lewgmail.romanenko.taxiservice.view.fragments.addOrder;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    @BindView((R.id.time_text))
    TextView time_text;

    private ArrayAdapter<String> addresessAdapter;
    private ArrayList<String> addresess;
    public FragmentPage1() {
    }

    public static FragmentPage1 newInstance(int sectionNumber) {
        FragmentPage1 fragment = new FragmentPage1();
     /*   Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);*/
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

    @OnClick(R.id.radio_button_now)
    public void onClickButtonNow() {
        uncheckOtherButton(1);
        showHideClock();
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

    @OnClick(R.id.add_address_btn)
    public void addAddress() {
        addAddressPoint();
    }

    @OnClick(R.id.time_button_picker)
    public void onClickTimePicker(){
        showTimePicker();
    }

    private void showTimePicker(){
        TimePickerDialog.OnTimeSetListener onTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time_text.setText(Integer.toString(hourOfDay) + ":" + Integer.toString(minute));
            }
        };
        TimePickerFragment time = new TimePickerFragment();
        time.setCallBack(onTimeListener);
        time.show(getFragmentManager(),"Time Picker");
    }
    private void initializeView() {

    }

    private void init(){
        addresess = new ArrayList<>();
        addresess.add("sdfklnfg");
        addresess.add("sdfklnfg");
       addresessAdapter = new AdapterAddPointOfRoute(getActivity(),R.layout.address_point,addresess);
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
        addresessAdapter.add("sdfklnfg");
    }

    @Override
   public void  onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        init();
    }
}
