package com.lewgmail.romanenko.taxiservice.view.dialogFragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import java.util.Calendar;

/**
 * Created by Lev on 05.12.2016.
 */

public class TimePickerFragment extends android.support.v4.app.DialogFragment {
    TimePickerDialog.OnTimeSetListener ondateSet;

    public void setCallBack(TimePickerDialog.OnTimeSetListener ondate) {
        ondateSet = ondate;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int minutes = c.get(Calendar.MINUTE);
        int hours = c.get(Calendar.HOUR);

        return new TimePickerDialog(getActivity(), ondateSet, minutes, hours, DateFormat.is24HourFormat(getActivity()));
    }

}
