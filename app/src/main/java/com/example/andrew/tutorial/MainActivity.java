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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements LocationListener{
    public static Vehicle myCar;
    public static Trip activeTrip;
    public static boolean isOnTrip;
    public static Report overallReport; //overall has monthly reports, which have daily reports
    public static LocationListener locationListener;
    public static LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(this, new String[]{
        Manifest.permission.ACCESS_FINE_LOCATION }, 1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overallReport = new Report();
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
        System.out.println("Add fired");
        //if the month and day exist, add trip to Overall.report.report.report
        for(int i = 0; i < 12; i++){
            if(overallReport.getReport(i) != null){
                System.out.println("i: " + i + " report date: " + activeTrip.getMonth());
            }
        }

        if(overallReport.getReport(activeTrip.getMonth()) == null){
            System.out.println("Month null");
            Report rep = new Report(12);
            String date = "";
            switch(activeTrip.getMonth()){
                case 0 : date = "January "; break;
                case 1 : date = "February "; break;
                case 2: date = "March "; break;
                case 3: date = "April "; break;
                case 4: date = "May "; break;
                case 5: date = "June "; break;
                case 6: date = "July "; break;
                case 7: date = "August "; break;
                case 8: date = "September "; break;
                case 9: date = "October "; break;
                case 10: date = "November "; break;
                case 11: date = "December "; break;
            }
            rep.setDate(date);
            overallReport.addReport((activeTrip.getMonth()), rep);
        }
        if(overallReport.getReport(activeTrip.getMonth()).getReport(activeTrip.getDay()) == null){
            System.out.println("Day null");
            Report rep = new Report(31);
            rep.setDate(Integer.toString(activeTrip.getDay()));
            overallReport.getReport(activeTrip.getMonth()).addReport((activeTrip.getDay()), rep);
        }
        System.out.println("Adding");
        overallReport.getReport(activeTrip.getMonth()).getReport(activeTrip.getDay()).addTrip(activeTrip);
    }

    public void onLocationChanged(Location location){
        if(isOnTrip) {
            if (activeTrip.hasBegun()) {
                activeTrip.setStart(activeTrip.getFinish());
                activeTrip.setFinish(location);
                activeTrip.addDistance();
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

    public void toMonthlyReport(Report report){
        Intent intent = new Intent(this, DisplayMonthlyReportOverviewActivity.class);
        startActivity(intent);
    }

    public void toDailyReport(Report report) {
        Intent intent = new Intent(this, DisplayDailyReportOverviewActivity.class);
        startActivity(intent);
    }

    public void startTrip(View view) throws InterruptedException{
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
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            try {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
                //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, this);
            } catch (Exception e) {
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
        addTrip();

    }

    public void displayReportOverview(View view){
        Intent intent = new Intent(this, DisplayReportOverviewActivity.class);
        startActivity(intent);
    }

}
