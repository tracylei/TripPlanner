package com.example.android.tripplanner;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

        PlaceAutocompleteFragment paFragment = new PlaceAutocompleteFragment();
        DestinationAutocompleteFragment daFragment = new DestinationAutocompleteFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.starting_place_holder, paFragment, "Starting place autocomplete box");
        transaction.add(R.id.dest1_holder, daFragment, "Destination 1 autocomplete box");
        transaction.commit();

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        tripName = (EditText)findViewById(R.id.trip_name);
        numDays = (EditText)findViewById(R.id.num_days);
        budget = (EditText)findViewById(R.id.budget);
        dbAdapter = new DatabaseAdapter(this);
    }

    public void createTrip (View view){
        long id = dbAdapter.insertData(tripName.getText().toString(),
                Integer.parseInt(numDays.getText().toString()),
                Integer.parseInt(budget.getText().toString()));
        if (id == -1){
            ToastMessage.message(this,"Trip was not added successfully");
        }
        else{
            ToastMessage.message(this,"Trip added successfully");
        }
    }
//    //Plus button click on fragment
//    public void addDest(View v) {
//        if (destCount < 6) {
//            DestinationAutocompleteFragment daFragment = new DestinationAutocompleteFragment();
//            FragmentManager manager = getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.add(R.id.dest1_holder, daFragment, "Destination 1 autocomplete box");
//            transaction.commit();
//            destCount++;
//        }
//    }
}
