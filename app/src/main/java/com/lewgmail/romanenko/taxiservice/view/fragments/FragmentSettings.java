package com.lewgmail.romanenko.taxiservice.view.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.lewgmail.romanenko.taxiservice.R;
import com.lewgmail.romanenko.taxiservice.presenter.SettinNotificPresenter;
import com.lewgmail.romanenko.taxiservice.presenter.adapters.AdapterCodeFromServer;

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
    private ProgressDialog progress;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings, container, false);
        ButterKnife.bind(this, view);
        settinNotificPresenter.getSettingNotific();
        progress = new ProgressDialog(getContext());
        progress.setTitle(getResources().getString(R.string.main_theme_loading));
        progress.setMessage(getResources().getString(R.string.text_of_loading));
        progress.setCancelable(true); // disable dismiss by tapping outside of the dialog
        progress.show();
        return view;
    }

    @Override
    public boolean validitionFields() {
        return false;
    }

    public void showError(int code) {
        Toast.makeText(getActivity(), AdapterCodeFromServer.AdapterCode(code, getContext()), Toast.LENGTH_LONG).show();
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
        progress.dismiss();
    }
}
