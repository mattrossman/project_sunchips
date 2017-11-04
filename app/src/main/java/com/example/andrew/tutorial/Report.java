package com.example.andrew.tutorial;

import java.util.Vector;

/**
 * Created by Andrew on 11/4/2017.
 */

public class Report {
    private Vector<Report> myReports; //a report can contain several reports. For instance, a monthly report is made of ~31 reports
    private String date; //this will either be mm/dd/yyyy or mm/yyyy or yyyy depending on how many reports it has
    private Vector<Report> trips; //a report is made up of several trips, which will give the data necessary
    private double cost; //a cost is calculated based on the trips
}
