package com.example.android.tripplanner;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by tracylei on 2015-08-22.
 */
public class DisplayTrips extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips_display);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PlaceAutocompleteFragment paFragment = new PlaceAutocompleteFragment();
        DestinationAutocompleteFragment daFragment = new DestinationAutocompleteFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.starting_place_holder, paFragment, "Starting place autocomplete box");
        transaction.add(R.id.dest1_holder, daFragment, "Destination 1 autocomplete box");
        transaction.commit();

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
