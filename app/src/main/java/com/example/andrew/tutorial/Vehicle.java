package com.example.andrew.tutorial;

/**
 * Created by Andrew on 11/4/2017.
 */

public class Vehicle {
    private String make; //make of the car
    private String model; //model of the car
    private double mileage; //miles per gallon
    private int year;

    public Vehicle(){
        make = "";
        model = "";
        mileage = 0.0;
        year = 0;
    }

    public void setMake(String make){
        this.make = make;
    }

    public void setModel(String model){
        this.model = model;
    }

    public void setMileage(double mileage){
        this.mileage = mileage;
    }

    public void setYear(int year){
        this.year = year;
    }

    public double getMileage(){
        return mileage;
    }


    public String toString(){
        return make + " " + model + " " + year;
    }
}
