package com.lewgmail.romanenko.taxiservice.view.fragments.addOrder;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.lewgmail.romanenko.taxiservice.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lev on 18.03.2017.
 */

public class FragmentPage1 extends Fragment {

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

    private void initializeView() {

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
        LayoutInflater vi = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = vi.inflate(R.layout.address_point, null);
        addressesLinear.addView(view);
    }


}
