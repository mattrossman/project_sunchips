package com.example.andrew.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
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
    public static Report activeReport;
    private ListView list;
    private ArrayAdapter<String> arrayList;
    private List<Report> reports;
    private List<String> texts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_report_overview);
        texts = new ArrayList<String>();
        reports = new ArrayList<Report>();
        //display all years, onclick should send you to given
            if(MainActivity.overallReport != null) {
                texts.add("Monthly Reports");
                for (Report report : MainActivity.overallReport.getReports()) {
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
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(arrayList);

       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if(position > 0) {
                   if (reports.get(position - 1) != null) {
                       activeReport = reports.get(position-1);
                       displayMonthlyReport();
                   }
               }
           }
       });

    }


    public void displayMonthlyReport(){
        Intent intent = new Intent(this, DisplayMonthlyReportOverviewActivity.class);
        startActivity(intent);
    }

}

