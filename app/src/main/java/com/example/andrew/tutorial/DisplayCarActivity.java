package com.example.andrew.tutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.andrew.tutorial.CarManager.AddCarActivity;

/*
This activity displays the car make/model form. The user types in the make/model of the car and the activity
gets the data for that make/model from the database. If the make/model isn't in the database, it sends the
user to the DisplayCarFormActivity.
 */
public class DisplayCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_car);

        loadOptions();
    }

    public void loadOptions(){
        //list makes and models of cars, list alphabetically by make{ model}
    }

    public void addCar(View view){
        Intent intent = new Intent(this, AddCarActivity.class);
        startActivity(intent);
    }
}
