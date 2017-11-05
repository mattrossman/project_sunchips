package com.example.andrew.tutorial;

import android.location.Location;

import java.util.Calendar;

/**
 * Created by Andrew on 11/4/2017.
 */

public class Trip {
    private Vehicle vehicle; //the active vehicle used for this trip
    private double distance; //the distance traveled in this trip
    private Calendar time;
    private Location locStart;
    private Location locFinish;

    private static final String provider = MainActivity.locationManager.GPS_PROVIDER;
    private static double gasPrice = 3.00;


    public Trip(Vehicle vehicle){
        this.vehicle = vehicle;
        distance = 0;
        locStart = new Location(provider);
        //time = Calendar.getInstance();
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
        return (double)(distance/vehicle.getMpg()*gasPrice);
    }

    public void addDistance(){
        distance += locStart.distanceTo(locFinish);
        setStart();
    }
    public void setStart(){
        locStart = new Location(provider);
    }
    public void setFinish(){
        locFinish = new Location(provider);
    }

    public double getDistance(){
        return distance;
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
