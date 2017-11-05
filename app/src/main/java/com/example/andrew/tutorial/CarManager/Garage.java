package com.example.andrew.tutorial.CarManager;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.andrew.tutorial.Vehicle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 11/5/2017.
 */

public class Garage {
    private Activity mActivity;

    public Garage(Activity activity) {
        mActivity = activity;
    }

    public void saveVehicle(Vehicle v) {
        List<Vehicle> cars = loadVehicles();
        if (cars==null)
            cars = new ArrayList<Vehicle>();
        cars.add(v);
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(mActivity.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cars);
        prefsEditor.putString("MyGarage", json);
        prefsEditor.commit();
    }

    public List<Vehicle> loadVehicles() {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(mActivity.getApplicationContext());
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("MyGarage", "");
        Type type = new TypeToken<List<Vehicle>>(){}.getType();
        List<Vehicle> cars = gson.fromJson(json, type);
        return cars;
    }

    public void clear() {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(mActivity.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.clear();
    }
}
