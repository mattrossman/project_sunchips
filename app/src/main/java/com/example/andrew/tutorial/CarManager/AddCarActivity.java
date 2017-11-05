package com.example.andrew.tutorial.CarManager;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.andrew.tutorial.R;

public class AddCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        new YearsRequest(this).request();
    }

    @Override
    protected void onStart() {
        super.onStart();
        spinnerListeners(this);
    }

    public void submitCar(View view){
        
    }


    private void spinnerListeners(final Activity mActivity) {
        final Spinner years = (Spinner) findViewById(R.id.yearsSpinner);
        final Spinner makes = (Spinner) findViewById(R.id.makesSpinner);
        final Spinner models = (Spinner) findViewById(R.id.modelsSpinner);
        final Spinner options = (Spinner) findViewById(R.id.optionsSpinner);

        years.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //year = years.getSelectedItem().toString();
                new MakesRequest(mActivity,
                        years.getSelectedItem().toString()
                ).request();
                System.out.println("Requesting makes from "+years.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) { }
        });
        makes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                new ModelsRequest(mActivity,
                        years.getSelectedItem().toString(),
                        makes.getSelectedItem().toString()
                ).request();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) { }
        });
        models.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                OptionsRequest req = new OptionsRequest(mActivity,
                        years.getSelectedItem().toString(),
                        makes.getSelectedItem().toString(),
                        models.getSelectedItem().toString()
                );

                req.request();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) { }
        });
        options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                System.out.println(id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) { }
        });
    }
}
