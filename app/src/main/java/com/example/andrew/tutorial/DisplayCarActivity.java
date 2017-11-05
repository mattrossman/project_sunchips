package com.example.andrew.tutorial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.andrew.tutorial.CarManager.AddCarActivity;
import com.example.andrew.tutorial.CarManager.Garage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

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

        loadGarage();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadGarage();
    }

    public void loadGarage(){
        System.out.println("Loading cars");
        List<Vehicle> cars = new Garage(this).loadVehicles();
        System.out.println(cars);
    }

    public void addCar(View view){
        Intent intent = new Intent(this, AddCarActivity.class);
        startActivity(intent);
    }
}
