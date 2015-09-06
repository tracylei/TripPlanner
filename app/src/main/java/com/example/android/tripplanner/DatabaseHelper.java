package com.example.android.tripplanner;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tracylei on 2015-09-06.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tripplannerdatabase";
    private static final String TABLE_NAME = "TRIPS";
    private static final int DATABASE_VERSION = 1;
    private static final String UID = "_id";
    private static final String TRIP_NAME = "Trip Name";
    private static final String ADDRESS = "Address";
    private Context context;
    private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + " ("+ UID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, "+ TRIP_NAME + " VARCHAR(255), "+ ADDRESS +
            " VARCHAR(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREATE_TABLE);
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
