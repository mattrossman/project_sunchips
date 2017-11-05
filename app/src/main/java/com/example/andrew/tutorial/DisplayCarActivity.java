package com.example.andrew.tutorial;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.andrew.tutorial.CarManager.AddCarActivity;
import com.example.andrew.tutorial.CarManager.Garage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
This activity displays the car make/model form. The user types in the make/model of the car and the activity
gets the data for that make/model from the database. If the make/model isn't in the database, it sends the
user to the DisplayCarFormActivity.
 */
public class DisplayCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_car);

        ListView lvGarage = (ListView) findViewById(R.id.garageList);
        lvGarage.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> adapter, View v, int position, long l){
                Vehicle car = (Vehicle) adapter.getItemAtPosition(position);
                System.out.println(car);

                fireAlert(car, position);
            }
        });
        loadGarage();
    }

    private void fireAlert(final Vehicle v, final int i) {
        final Activity mActivity = this;

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Selected:");
        alertDialog.setMessage(v.toString());
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Activate",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("Confirmed " + v.toString());
                        MainActivity.myCar = v;
                        System.out.println(v.getMileage());
                        finish();
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Delete",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new Garage(mActivity).remove(i);
                        loadGarage();
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadGarage();
    }

    public void loadGarage(){
        System.out.println("Loading cars");
        List<Vehicle> cars = new Garage(this).loadVehicles();
        fancyDisplay(cars);
        System.out.println(cars);
    }

    private void fancyDisplay(List<Vehicle> cars) {
        List<Map<String, String>> data = garageMap(cars);
        ListView carList = (ListView) findViewById(R.id.garageList);

        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[] {"title", "sub"},
                new int[] {android.R.id.text1, android.R.id.text2});
        carList.setAdapter(adapter);
    }

    private List<Map<String, String>> garageMap(List<Vehicle> vehicles) {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        for (Vehicle v : vehicles) {
            Map<String, String> datum = new HashMap<String, String>(2);
            String title = v.getMake()+" "+v.getModel();
            String sub = Integer.toString(v.getYear())+", "+v.getOption();

            datum.put("title", title);
            datum.put("sub", sub);
            data.add(datum);
        }

        return data;
    }

    private void display(List<Vehicle> cars) {
        if (cars!=null) {
            ArrayAdapter<Vehicle> adapter = new ArrayAdapter<Vehicle>(this,
                    android.R.layout.simple_list_item_1, cars);

            ListView carList = (ListView) findViewById(R.id.garageList);
            carList.setAdapter(adapter);
        }
    }

    public void addCar(View view){
        Intent intent = new Intent(this, AddCarActivity.class);
        startActivity(intent);
    }
}
