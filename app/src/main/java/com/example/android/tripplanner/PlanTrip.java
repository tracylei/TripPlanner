package com.example.android.tripplanner;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;


/**
 * Created by tracylei on 2015-08-22.
 */


public class PlanTrip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_plan);

        PlaceAutocompleteFragment paFragment = new PlaceAutocompleteFragment();
        DestinationAutocompleteFragment daFragment = new DestinationAutocompleteFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.starting_place_holder, paFragment, "Starting place autocomplete box");
        transaction.add(R.id.dest1_holder,daFragment, "Destination 1 autocomplete box");
        transaction.commit();
    }


    //Button click on fragment
    public void addDest(View v) {
        DestinationAutocompleteFragment daFragment = new DestinationAutocompleteFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.dest1_holder, daFragment, "Destination 1 autocomplete box");
        transaction.commit();
    }
    public void clearText (View v){
        // Set up the 'clear text' button that clears the text in the autocomplete view
        ((AutoCompleteTextView) v).setText("");

    }
}
