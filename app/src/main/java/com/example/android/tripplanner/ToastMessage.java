package com.example.android.tripplanner;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by tracylei on 2015-09-06.
 */
public class ToastMessage {
    public static void message (Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
