package com.example.android.tripplanner;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;


/**
 * Created by tracylei on 2015-08-22.
 */


public class PlanTrip extends AppCompatActivity {

    private Toolbar toolbar;
    private DatabaseAdapter dbAdapter;
    private EditText tripName;
    private EditText numDays;
    private EditText budget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option2_plan_trip);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        tripName = (EditText)findViewById(R.id.trip_name);
        numDays = (EditText)findViewById(R.id.num_days);
        budget = (EditText)findViewById(R.id.budget);
        dbAdapter = new DatabaseAdapter(this);

    }

    public void createTrip (View view){
        long id = dbAdapter.createTrip(tripName.getText().toString(),
                Integer.parseInt(numDays.getText().toString()),
                Integer.parseInt(budget.getText().toString()));
        if (id == -1){
            ToastMessage.message(this,"Trip was not added successfully");
        }
        else{
            ToastMessage.message(this,"Trip added successfully");
        }
        Intent intent = new Intent(this, DisplayTrips.class);
        startActivity(intent);
    }

}
