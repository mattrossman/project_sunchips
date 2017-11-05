package com.example.andrew.tutorial;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements LocationListener{
    public static Vehicle myCar;
    public static Trip activeTrip;
    public static boolean isOnTrip;
    public static Report overallReport; //overall has yearly reports, which have monthly reports, which have daily reports
    public static LocationListener locationListener;
    public static LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(this, new String[]{
        Manifest.permission.ACCESS_FINE_LOCATION }, 1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(myCar != null) {
            TextView carName = (TextView) findViewById(R.id.currentCarName);
            carName.setText(myCar.toString());
            TextView carMileage = (TextView) findViewById(R.id.currentCarMileage);
            carMileage.setText(Double.toString(myCar.getMileage()));
        }
        else {
            TextView carName = (TextView) findViewById(R.id.currentCarName);
            carName.setText("No car selected");
        }
        }

    public void onProviderEnabled(String provider){

    }
    public void onStatusChanged(String s, int i, Bundle b){
    }

    public void onProviderDisabled(String provider){

    }

    public void addTrip(){
        //if the year and month and day exist, add trip to Overall.report.report.report
        overallReport.getReport(activeTrip.getYear()).getReport(activeTrip.getMonth()).getReport(activeTrip.getDay()).addTrip(activeTrip);
    }

    public void onLocationChanged(Location location){
        System.out.println("Location changed");
        if(isOnTrip) {
            if (activeTrip.hasBegun()) {
                activeTrip.setStart(activeTrip.getFinish());
                activeTrip.setFinish(location);
                activeTrip.addDistance();
                System.out.println(activeTrip.getDistance());
                TextView activeTripReport = (TextView) findViewById(R.id.activeTripReport);
                activeTripReport.setText(Double.toString(activeTrip.getDistance()) + " miles, $" + Double.toString(activeTrip.getCost()));
            }
            else{
                activeTrip.setStart(location);
                activeTrip.setFinish(location);
                activeTrip.begin();
            }
        }
    }

    public void displayCar(View view) {
        Intent intent = new Intent(this, DisplayCarActivity.class);
        startActivity(intent);
    }

    public void startTrip(View view) throws InterruptedException{
        System.out.println("Trip started");
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                    this);

// Setting Dialog Title
            alertDialog2.setTitle("Location Needs to be Enabled");

// Setting Dialog Message
            alertDialog2.setMessage("Please enable this app to use your phone's location");
            alertDialog2.show();
            return;
        }
        if(myCar != null) {
            isOnTrip = true;
            activeTrip = new Trip(myCar);
            activeTrip.addDistance();
            System.out.println(activeTrip.getDistance());
            TextView activeTripReport = (TextView) findViewById(R.id.activeTripReport);
            activeTripReport.setText(Double.toString(activeTrip.getDistance()) + " miles, $" + Double.toString(activeTrip.getCost()));
            Button startStop = (Button) findViewById(R.id.startStopButton);
            startStop.setText("Stop Trip");
            startStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stopTrip(view);
                }
            });
            System.out.println("location manager running");
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            try {
                System.out.println("Request Location Updates");
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
                //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, this);
            } catch (Exception e) {
                System.err.println("Location manager unable to request location updates");
            }
        }
        else{
            AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                    this);

// Setting Dialog Title
            alertDialog2.setTitle("Need a vehicle");

// Setting Dialog Message
            alertDialog2.setMessage("Please select your vehicle");
            alertDialog2.show();
        }
    }
    public void stopTrip(View view){
        //stops trip
        isOnTrip = false;
        Button startStop = (Button) findViewById(R.id.startStopButton);
        startStop.setText("Start Trip");
        startStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{startTrip(view);}
                catch(InterruptedException e){

                }
            }
        });
        activeTrip.end();

    }

    public void displayReportOverview(View view){
        Intent intent = new Intent(this, DisplayReportOverviewActivity.class);
        startActivity(intent);
    }

}
