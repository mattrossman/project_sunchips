package com.example.andrew.tutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/*
This displays a year's report's monthly reports.

Average Monthly Cost: $x.xx <- this would be the monthly average cost
        12/17: Distance, Cost
        11/17: Distance, Cost
        10/17: Distance, Cost
        ...
*/
public class DisplayYearlyReportOverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_yearly_report_overview);

        //display month data for given year, onclick should send you to the month
    }

    public void displayMonthlyReport(View view){
        Intent intent = new Intent(this, DisplayMonthlyReportOverviewActivity.class);
        startActivity(intent);
    }
}
