package com.example.android.tripplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tracylei on 2015-09-06.
 */
public class DatabaseAdapter{
        DatabaseHelper helper;
        Context ct;
        public DatabaseAdapter(Context context){
            ToastMessage.message(context, "adapter ctor called");
            helper = new DatabaseHelper(context);
            helper.getWritableDatabase();
            ct = context;
        }

        public long insertData (String tripName, int numDays, int budget){
            ToastMessage.message(ct, "insert data");
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(DatabaseHelper.TRIP_NAME, tripName);
            cv.put(DatabaseHelper.NUM_DAYS, numDays);
            cv.put(DatabaseHelper.BUDGET, budget);
            //Returns id corresponding to the newly inserted row
            return db.insert(DatabaseHelper.TABLE_NAME, null, cv);
        }



        static class DatabaseHelper extends SQLiteOpenHelper {

            private static final String DATABASE_NAME = "tripplannerdatabase";
            private static final String TABLE_NAME = "TRIPS";
            private static final int DATABASE_VERSION = 8;
            private static final String UID = "_id";
            private static final String TRIP_NAME = "trip_name";
            private static final String NUM_DAYS = "number_of_days";
            private static final String BUDGET = "budget";
            private Context context;
            private static final String CREATE_TABLE = "CREATE TABLE Trips (_id INTEGER PRIMARY KEY AUTOINCREMENT, trip_name TEXT, number_of_days INTEGER, budget INTEGER);";
//                    "CREATE TABLE "
//                            + TABLE_NAME +
//                            " ( "+ UID +
//                    " INTEGER PRIMARY KEY AUTOINCREMENT, "+ TRIP_NAME + " TEXT, "+ NUM_DAYS +
//                    " INTEGER, " + BUDGET + " INTEGER);";
            private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;

            public DatabaseHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
                ToastMessage.message(context, "database helper ctor called");
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                try{
                    db.execSQL(CREATE_TABLE);
                    ToastMessage.message(context, "Trip created");
                } catch (SQLException e){
                    ToastMessage.message(context,""+e);
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                try{
                    db.execSQL(DROP_TABLE);
                } catch (SQLException e){
                    ToastMessage.message(context, ""+e);
                }
            }
        }
}