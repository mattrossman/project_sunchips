package com.example.andrew.tutorial.CarManager;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Matt on 11/4/2017.
 */

public class Dropdown {
    private Spinner spinner;
    private Activity mActivity;

    public Dropdown(Activity activity, View v) {
        mActivity = activity;
        spinner = (Spinner) v;
    }

    public void updateOptions(String[] options) {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                mActivity, android.R.layout.simple_spinner_dropdown_item, options );
        spinner.setAdapter(spinnerArrayAdapter);
    }
}