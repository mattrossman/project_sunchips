package com.example.andrew.tutorial.CarManager;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.andrew.tutorial.R;

public class AddCarActivity extends AppCompatActivity {

    public static String year, make, model, option;
    private static Spinner years, makes, models, options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        // Save static references to the spinner dropdowns
        years = (Spinner) findViewById(R.id.yearsSpinner);
        makes = (Spinner) findViewById(R.id.makesSpinner);
        models = (Spinner) findViewById(R.id.modelsSpinner);
        options = (Spinner) findViewById(R.id.optionsSpinner);

        // Trigger population of the year dropdown
        new YearsRequest(this).request();
    }

    @Override
    protected void onStart() {
        super.onStart();
        spinnerListeners(this);
    }

    public void submitCar(View view){
        new IDRequest(this,year, make, model, option).request();
    }


    private void spinnerListeners(final Activity mActivity) {

        years.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                year = years.getSelectedItem().toString();
                new MakesRequest(mActivity, year).request();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) { }
        });
        makes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                make = makes.getSelectedItem().toString();
                new ModelsRequest(mActivity, year, make).request();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) { }
        });
        models.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                model = models.getSelectedItem().toString();
                new OptionsRequest(mActivity, year, make, model).request();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) { }
        });
        options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                option = options.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) { }
        });
    }
}
