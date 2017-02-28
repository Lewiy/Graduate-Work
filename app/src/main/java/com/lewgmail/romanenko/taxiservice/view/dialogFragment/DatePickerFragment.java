package com.lewgmail.romanenko.taxiservice.view.dialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import com.lewgmail.romanenko.taxiservice.view.activity.EditOrderActivity;

import java.util.Calendar;

/**
 * Created by Lev on 03.12.2016.
 */

public class DatePickerFragment extends DialogFragment {
    //@BindView(R.id.date_ride_act)
    // private EditText date_ride_act;


    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.date_picker, container);
        getDialog().setTitle("Pick date ");
        return view;
    }*/


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), (EditOrderActivity) getActivity(), year, month, day);
    }
}
