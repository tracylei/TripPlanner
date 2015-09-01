package com.example.android.tripplanner;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * Created by tracylei on 2015-08-22.
 */


public class PlanTrip extends AppCompatActivity {

    private Toolbar toolbar;
    private int destCount;
    private LinearLayout screen1;
    private RelativeLayout screen2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option2_plan_trip);

        PlaceAutocompleteFragment paFragment = new PlaceAutocompleteFragment();
        DestinationAutocompleteFragment daFragment = new DestinationAutocompleteFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.starting_place_holder, paFragment, "Starting place autocomplete box");
        transaction.add(R.id.dest1_holder,daFragment, "Destination 1 autocomplete box");
        transaction.commit();

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        destCount=1;
    }


    //Plus button click on fragment
    public void addDest(View v) {
        if (destCount < 6) {
            DestinationAutocompleteFragment daFragment = new DestinationAutocompleteFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.dest1_holder, daFragment, "Destination 1 autocomplete box");
            transaction.commit();
            destCount++;
        }
    }

    //Next button click on screen 1
    public void firstNext(View v){
        screen1 = (LinearLayout) findViewById(R.id.screen1);
        screen2 = (RelativeLayout) findViewById(R.id.screen2);
        screen1.setVisibility(View.GONE);
        screen2.setVisibility(View.VISIBLE);
    }
}
