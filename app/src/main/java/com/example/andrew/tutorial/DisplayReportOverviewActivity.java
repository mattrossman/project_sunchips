package com.example.andrew.tutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Vector;

/*
This displays the overall average cost of the past years' overviews and then displays links to
those years' report overviews. Recent reports are displayed first, vertically descending
downwards. If you click on a year, it should transport you to DisplayReportActivity for that year.
Example
Average Yearly Cost: $x.xx <- this would be the yearly average cost
2017: Distance, Cost
2016: Distance, Cost
2015: Distance, Cost
2014: Distance, Cost
...

 */
public class DisplayReportOverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_report_overview);

        //display all years, onclick should send you to given year
    }

    public void displayYearlyReport(View view){
        Intent intent = new Intent(this, DisplayYearlyReportOverviewActivity.class);
        startActivity(intent);
    }
}
