package com.example.andrew.tutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/*
This displays a month's report's daily reports.

Average Daily Cost: $x.xx <- this would be the daily average cost
        12/31/17: Total Distance, Cost
        12/30/17: Total Distance, Cost
        12/29/17: Total Distance, Cost
        ...
*/

public class DisplayMonthlyReportOverviewActivity extends AppCompatActivity {
    public static Report activeReport;
    private ListView list;
    private ArrayAdapter<String> arrayList;
    private List<Report> reports;
    private List<String> texts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_monthly_report_overview);
        texts = new ArrayList<String>();
        reports = new ArrayList<Report>();
        //display all years, onclick should send you to given
        if(DisplayReportOverviewActivity.activeReport != null) {
            texts.add("Daily Reports");
            for (Report report : DisplayReportOverviewActivity.activeReport.getReports()) {
                if(report != null) {
                    texts.add(report.toString());
                    reports.add(report);
                }
            }
        }
        else{
            String text = "No reports found";
            texts.add(text);
        }
        arrayList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, texts );
        list = (ListView) findViewById(R.id.MonthlyList);
        list.setAdapter(arrayList);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    if (reports.get(position - 1) != null) {
                        activeReport = reports.get(position-1);
                        displayDailyReport();
                    }
                }
            }
        });

    }

    public void displayDailyReport(){
        Intent intent = new Intent(this, DisplayDailyReportOverviewActivity.class);
        startActivity(intent);
    }
}
