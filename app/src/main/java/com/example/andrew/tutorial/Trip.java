package com.example.andrew.tutorial;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by Andrew on 11/4/2017.
 */

public class Trip {
    private Vehicle vehicle; //the active vehicle used for this trip
    private double distance; //the distance traveled in this trip
    private Calendar time;
    private Location locStart;
    private Location locFinish;
    private boolean hasBegun;

    private static final String provider = LocationManager.GPS_PROVIDER;
    private static float gasPrice = (float)3.00;


    public Trip(Vehicle vehicle){
        this.vehicle = vehicle;
        distance = 0;
        locStart = new Location(provider);
        locFinish = new Location(provider);
        //time = Calendar.getInstance();
    }

    public boolean hasBegun(){
        return hasBegun;
    }

    public void begin(){
        hasBegun = true;
    }

    public void end(){
        hasBegun = false;
    }

    public int getYear(){
        return 0;
    }

    public int getMonth(){
        return 0;
    }

    public int getDay(){
        return 0;
    }

    public double getCost(){
        double cost = (double)(distance/vehicle.getMileage()*gasPrice);
        return (double)Math.round(cost*100)/100;
    }

    public void addDistance(){
        distance += (double) locStart.distanceTo(locFinish) * 0.000621371;
    }
    public void setStart(Location location){
        locStart = location;
    }
    public void setFinish(Location location){
        locFinish = location;
    }

    public Location getFinish(){
        return locFinish;
    }

    public double getDistance(){
        return (double)Math.round(distance*100)/100;
    }

    double distanceBetweenTwoPoint(double srcLat, double srcLng, double desLat, double desLng) {
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(desLat - srcLat);
        double dLng = Math.toRadians(desLng - srcLng);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(srcLat))
                * Math.cos(Math.toRadians(desLat)) * Math.sin(dLng / 2)
                * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        return (double) (dist);
    }
}
