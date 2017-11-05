package com.example.andrew.tutorial;

import java.io.DataOutputStream;

/**
 * Created by Andrew on 11/4/2017.
 */

public class Vehicle {
    private String make; //make of the car
    private String model; //model of the car
    private String option; //specific configuration of the car
    private double mpg; //miles per gallon
    private int year;

    public Vehicle(){
        make = "";
        model = "";
        mpg = 0.0;
        year = 0;
        option = "";
    }

    public void setMake(String make){
        this.make = make;
    }

    public void setModel(String model){
        this.model = model;
    }

    public void setMpg(double mpg){
        this.mpg = mpg;
    }

    public void setYear(int year){
        this.year = year;
    }

    public double getMpg(){
        return mpg;
    }

    public String getOption() { return option; }

    public void setOption(String option) { this.option = option; }

    public String toString(){
        return Integer.toString(year) + " " + make + " " + model + " " + option;
    }
}
