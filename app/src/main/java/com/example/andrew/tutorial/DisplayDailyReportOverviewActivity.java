package com.example.andrew.tutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/*
This displays a day's trip reports.

Average Trip Cost: $x.xx <- this would be the daily average cost
        12:00pm: Distance, Cost
        11:00pm: Distance, Cost
        10:00pm: Distance, Cost
        ...
*/
public class DisplayDailyReportOverviewActivity extends AppCompatActivity {
    public static Report activeReport;
    private ListView list;
    private ArrayAdapter<String> arrayList;
    private List<Trip> trips;
    private List<String> texts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_daily_report_overview);

        texts = new ArrayList<String>();
        trips = new ArrayList<Trip>();
        //display all years, onclick should send you to given
        if(DisplayMonthlyReportOverviewActivity.activeReport != null) {
            texts.add("Day's Trips");
            for (Trip trip : DisplayMonthlyReportOverviewActivity.activeReport.getTrips()) {
                if(trip != null) {
                    texts.add(trip.toString());
                    trips.add(trip);
                }
            }
        }
        else{
            String text = "No records found";
            texts.add(text);
        }
        arrayList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, texts );
        list = (ListView) findViewById(R.id.DailyList);
        list.setAdapter(arrayList);
    }

}
