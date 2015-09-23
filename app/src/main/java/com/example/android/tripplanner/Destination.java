package com.example.android.tripplanner;

/**
 * Created by tracylei on 2015-09-22.
 */
public class Destination {
    private long id;
    private String location;

    public Destination (){};
    public Destination (String location){
        this.location = location;
    }
    public void setID (int id){
        this.id = id;
    }

    public String getLocation() {
        return location;
    }
}
