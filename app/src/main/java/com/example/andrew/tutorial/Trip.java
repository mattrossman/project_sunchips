package com.example.andrew.tutorial;

import android.location.Location;
import android.location.LocationManager;


import java.text.DateFormat;

import java.util.Calendar;

/**
 * Created by Andrew on 11/4/2017.
 */

public class Trip {
    private Vehicle vehicle; //the active vehicle used for this trip
    private double distance; //the distance traveled in this trip
    private DateFormat time;
    private Location locStart;
    private Location locFinish;
    private boolean hasBegun;
    String date;

    private static final String provider = LocationManager.GPS_PROVIDER;
    private static float gasPrice = (float)3.00;


    public Trip(Vehicle vehicle){
        this.vehicle = vehicle;
        distance = 0;
        locStart = new Location(provider);
        locFinish = new Location(provider);
        time = DateFormat.getInstance();
        date = getDate();
    }

    public String toString(){
        return date + ", Cost: " + getCost();
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

    public int getMonth(){
        return time.getCalendar().get(Calendar.MONTH);
    }

    public int getDay(){
        return time.getCalendar().get(Calendar.DAY_OF_MONTH);
    }

    public String getDate(){
        return time.getCalendar().toString();
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
