package com.example.android.tripplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tracylei on 2015-09-06.
 */
public class DatabaseAdapter {
    // Logcat tag
private static final String LOG = "DatabaseAdapter";
    DatabaseHelper helper;
    SQLiteDatabase db;
    Context ct;

    public DatabaseAdapter(Context context){
        Log.i(LOG, "Adapter ctor");
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
        ct = context;
    }

    public long createTrip(Trip trip){
        Log.i(LOG, "Creating new trip");
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.KEY_TRIP_NAME, trip.getTripName());
        cv.put(DatabaseHelper.KEY_NUM_DAYS, trip.getNumDays());
        cv.put(DatabaseHelper.KEY_BUDGET, trip.getBudget());
        //Returns id corresponding to the newly inserted row
        long trip_id = db.insert(DatabaseHelper.TABLE_TRIPS, null, cv);
        trip.setID(trip_id);
        return trip_id;
    }

    public long createDestination (Destination dest, Long tripID){
        Log.i(LOG, "Creating new destination in trip: " + tripID);
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.KEY_PLACE, dest.getLocation());

        long dest_id = db.insert(DatabaseHelper.TABLE_DEST, null, cv);

        linkDestToTrip(tripID, dest_id);

        return dest_id;
    }

    public long linkDestToTrip(long trip_id, long dest_id){
        Log.i(LOG, "Linking dest_id " + dest_id + " to trip_id " + trip_id);
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.KEY_TRIP_ID, trip_id);
        cv.put(DatabaseHelper.KEY_DEST_ID, dest_id);
        return db.insert(DatabaseHelper.TABLE_TRIP_DEST, null, cv);
    }



        static class DatabaseHelper extends SQLiteOpenHelper {

            // Logcat tag
            private static final String LOG = "DatabaseHelper";

            private static final int DATABASE_VERSION = 8;

            private static final String DATABASE_NAME = "tripPlannerDatabase";

            // Table names
            private static final String TABLE_TRIPS = "trips";
            private static final String TABLE_DEST = "destinations";
            private static final String TABLE_TRIP_DEST = "trip_destinations";

            // Key
            private static final String UID = "_id";

            // TRIPS table
            private static final String KEY_TRIP_NAME = "trip_name";
            private static final String KEY_NUM_DAYS = "number_of_days";
            private static final String KEY_BUDGET = "budget";

            // DEST table
            private static final String KEY_PLACE = "place";

            // TRIP_DEST table
            private static final String KEY_TRIP_ID = "trip_id";
            private static final String KEY_DEST_ID = "dest_id";


            private Context context;
            private static final String CREATE_TABLE_TRIPS =  "CREATE TABLE " + TABLE_TRIPS + " ( "+ UID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_TRIP_NAME + " TEXT, "+ KEY_NUM_DAYS +
                    " INTEGER, " + KEY_BUDGET + " INTEGER);";
            private static final String CREATE_TABLE_DEST =  "CREATE TABLE " + TABLE_DEST + " ( "+ UID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_PLACE + " TEXT);";
            private static final String CREATE_TABLE_TRIP_DEST =  "CREATE TABLE " + TABLE_DEST + " ( "+ UID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_TRIP_ID + " INTEGER, "
                    + KEY_DEST_ID + " INTEGER);";

            private static final String DROP_TRIPS = "DROP TABLE IF EXISTS "+ TABLE_TRIPS;
            private static final String DROP_DEST = "DROP TABLE IF EXISTS "+ TABLE_DEST;
            private static final String DROP_TRIP_DEST = "DROP TABLE IF EXISTS " + TABLE_TRIP_DEST;

            public DatabaseHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
                Log.i(LOG, "Database helper ctor");
                this.context = context;
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                try{
                    db.execSQL(CREATE_TABLE_TRIPS);
                    db.execSQL(CREATE_TABLE_DEST);
                    db.execSQL(CREATE_TABLE_TRIP_DEST);
                    Log.i(LOG, "Trip table created");
                } catch (SQLException e){
                    ToastMessage.message(context,""+e);
                }
        }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                try{
                    db.execSQL(DROP_TRIPS);
                    db.execSQL(DROP_DEST);
                    db.execSQL(DROP_TRIP_DEST);
                    Log.i(LOG, "Trip table deleted");
                } catch (SQLException e){
                    ToastMessage.message(context,""+e);
                }
            }
        }
}