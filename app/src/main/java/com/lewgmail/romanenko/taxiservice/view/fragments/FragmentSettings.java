package com.lewgmail.romanenko.taxiservice.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.presenter.SettinNotificPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

/**
 * Created by Lev on 18.05.2017.
 */


public class FragmentSettings extends android.support.v4.app.Fragment implements OperationFragment {
    @BindView(R.id.set_settings_notif)
    Switch setSetinNotif;
    private SettinNotificPresenter settinNotificPresenter = new SettinNotificPresenter(this);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings, container, false);
        ButterKnife.bind(this, view);
        settinNotificPresenter.getSettingNotific();
        return view;
    }

    @Override
    public boolean validitionFields() {
        return false;
    }

    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @OnCheckedChanged(R.id.set_settings_notif)
    public void onClockSwitch() {
        onCheckedChanged(setSetinNotif.isChecked());
    }

    private void onCheckedChanged(boolean isChecked) {
        Toast.makeText(getActivity(), "The Switch is " + (isChecked ? "on" : "off"),
                Toast.LENGTH_SHORT).show();
        settinNotificPresenter.setSettingNotific();
    }

    public boolean getSwitchValue() {
        return setSetinNotif.isChecked();
    }

    public void setNotifStatus(boolean value) {
        setSetinNotif.setChecked(value);
    }
}
