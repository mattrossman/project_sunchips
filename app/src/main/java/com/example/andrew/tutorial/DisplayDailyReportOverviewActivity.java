package com.example.andrew.tutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
This displays a day's trip reports.

Average Trip Cost: $x.xx <- this would be the daily average cost
        12:00pm: Distance, Cost
        11:00pm: Distance, Cost
        10:00pm: Distance, Cost
        ...
*/
public class DisplayDailyReportOverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_daily_report_overview);

        //load trip data for given day
    }

}
