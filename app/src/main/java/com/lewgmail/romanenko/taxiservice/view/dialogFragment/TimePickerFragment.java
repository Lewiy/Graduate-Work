package com.lewgmail.romanenko.taxiservice.view.dialogFragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;

import com.lewgmail.romanenko.taxiservice.view.activity.EditOrderActivity;

import java.util.Calendar;

/**
 * Created by Lev on 05.12.2016.
 */

public class TimePickerFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int minutes = c.get(Calendar.MINUTE);
        int hours = c.get(Calendar.HOUR);

        return new TimePickerDialog(getActivity(), (EditOrderActivity) getActivity(), minutes, hours, true);
    }
}
