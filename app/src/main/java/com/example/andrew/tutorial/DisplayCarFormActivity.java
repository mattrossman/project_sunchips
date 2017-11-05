package com.example.andrew.tutorial;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/*
If the database doesn't have the user's make/model, this form displays, allowing the user
to add the make/model of their vehicle with mileage. This will save locally to their app.
 */
public class DisplayCarFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_car_form);
    }

    public void submitCar(View view){
        //takes data from text boxes, validates that mpg field contains number
        //sets mainActivity's current car to this data

        if(!((EditText) findViewById(R.id.make_input)).getText().toString().equals("") && !((EditText) findViewById(R.id.model_input)).getText().toString().equals("") && !((EditText) findViewById(R.id.mileage_input)).getText().toString().equals("") && !((EditText) findViewById(R.id.mileage_input)).getText().toString().equals(".") && !((EditText) findViewById(R.id.year_input)).getText().toString().equals("") && !((EditText) findViewById(R.id.year_input)).getText().toString().equals(".")) {
            Vehicle car = new Vehicle();
            car.setMake(((EditText) findViewById(R.id.make_input)).getText().toString());
            car.setModel(((EditText) findViewById(R.id.model_input)).getText().toString());
            car.setYear(Integer.parseInt(((EditText) findViewById(R.id.year_input)).getText().toString()));
            car.setMpg(Double.parseDouble(((EditText) findViewById(R.id.mileage_input)).getText().toString()));
            MainActivity.myCar = car;

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else {
            AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                    this);

// Setting Dialog Title
            alertDialog2.setTitle("Missing Information");

// Setting Dialog Message
            alertDialog2.setMessage("Please fill out all fields.");
            alertDialog2.show();
        }
    }

}
