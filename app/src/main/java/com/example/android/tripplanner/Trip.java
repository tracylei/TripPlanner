package com.example.android.tripplanner;

/**
 * Created by tracylei on 2015-09-22.
 */
public class Trip {
    private long id;
    private String tripName;
    private int numDays;
    private int budget;

    public Trip(){}
    public Trip(String tripName, int numDays, int budget){
        this.tripName = tripName;
        this.numDays = numDays;
        this.budget = budget;
    }
    public String getTripName(){
        return tripName;
    }

    public int getNumDays() {
        return numDays;
    }

    public int getBudget() {
        return budget;
    }

    public void setID(long id){
        this.id = id;
    }
}
