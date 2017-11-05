package com.example.andrew.tutorial;

import java.util.Vector;

/**
 * Created by Andrew on 11/4/2017.
 */

public class Report {
    private Report[] reports; //a report can contain several reports. For instance, a monthly report is made of ~31 reports
    private String date; //this will either be mm/dd/yyyy or mm/yyyy or yyyy depending on how many reports it has
    private Vector<Trip> trips; //a report is made up of several trips, which will give the data necessary
    private double cost; //a cost is calculated based on the trips
    int reportsIndex;

    public Report(){
        reports = new Report[100];
        date = "";
        trips = new Vector<Trip>();
        cost = 0;
        reportsIndex = 0;
    }

    public Report(int size){
        reports = new Report[size];
        date = "";
        trips = new Vector<Trip>();
        cost = 0;
        reportsIndex = 0;
    }

    public Vector<Trip> getTrips(){
        return trips;
    }

    public String toString(){
        return (date + ", Average Cost: " + getAvgCost());
    }

    public void setDate(String date){
        this.date = date;
    }

    public double getCost(){
        return cost;
    }

    public double getAvgCost(){
        int count = 0;
        double cost = 0;
        for (Report report : reports) {
            if(report != null){
                count++;
                cost += report.getAvgCost();
            }
        }
        for(Trip trip : trips){
           if(trip != null) {
               count++;
               cost += trip.getCost();
           }
        }

        return (double)(cost/count);
    }

    public void calculateTotalCost(){
        double total = 0.00;
        for (Trip trip: trips) {
            total += (double)trip.getCost();
        }
        cost = total;
    }

    public void addTrip(Trip trip){
        trips.add(trip);
        calculateTotalCost();
    }

    public Report getReport(int i){
        return reports[i];
    }

    public void addReport(Report report){
        this.reports[reportsIndex] = report;
        reportsIndex ++;
    }
    public void addReport(int i, Report report){
        this.reports[i] = report;
    }
    public Report[] getReports(){
        return reports;
    }
}
