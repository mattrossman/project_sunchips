package com.example.andrew.tutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static Vehicle myCar;
    public static Trip activeTrip;
    public static boolean isOnTrip;
    private static Report myReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(myCar != null) {
            TextView carName = (TextView) findViewById(R.id.currentCarName);
            carName.setText(myCar.toString());
            TextView carMileage = (TextView) findViewById(R.id.currentCarMileage);
            carMileage.setText(Double.toString(myCar.getMileage()));
        }
        else{
            TextView carName = (TextView) findViewById(R.id.currentCarName);
            carName.setText("No car selected");
        }
    }

    public void displayCar(View view) {
        Intent intent = new Intent(this, DisplayCarActivity.class);
        startActivity(intent);
    }

    public void startTrip(View view){
        isOnTrip = true;
        //initializes activeTrip to a new trip
        //changes button to stopTrip button
        //runTrip()
        Button startStop = (Button) findViewById(R.id.startStopButton);
        startStop.setText("Stop");
        startStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTrip(view);
            }
        });
    }

    public void runTrip(){
        //adds distance to trip
        //adds time to trip
        //should call itself every second
        //stops when isOnTrip is False;
    }

    public void stopTrip(View view){
        //stops trip
        isOnTrip = false;
        Button startStop = (Button) findViewById(R.id.startStopButton);
        startStop.setText("Start");
        startStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTrip(view);
            }
        });
    }

    public void displayReportOverview(View view){
        Intent intent = new Intent(this, DisplayReportOverviewActivity.class);
        startActivity(intent);
    }

}
