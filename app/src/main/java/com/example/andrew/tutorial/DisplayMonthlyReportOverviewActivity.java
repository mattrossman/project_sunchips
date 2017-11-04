package com.example.andrew.tutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/*
This displays a month's report's daily reports.

Average Daily Cost: $x.xx <- this would be the daily average cost
        12/31/17: Total Distance, Cost
        12/30/17: Total Distance, Cost
        12/29/17: Total Distance, Cost
        ...
*/

public class DisplayMonthlyReportOverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_monthly_report_overview);

        //load day data for given month, onclick should send you to the day
    }

    public void displayDailyReport(View view){
        Intent intent = new Intent(this, DisplayDailyReportOverviewActivity.class);
        startActivity(intent);
    }
}
