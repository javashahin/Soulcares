package com.soulcare.shahin.soulcare.app;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by shahin on 22-03-17.
 */

public class PregDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                this, year, month, day);
        return dpd;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the chosen date
        TextView tv = (TextView) getActivity().findViewById(R.id.tv);
        TextView pregnant_Date = (TextView) getActivity().findViewById(R.id.pregdate);
        int actualMonth = month + 1; // Because month index start from zero
        // Display the unformatted date to TextView

        pregnant_Date.setText(day+"/"+actualMonth+"/"+year);
    }
}