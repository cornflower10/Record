package com.record.utils;

import android.util.Log;

import com.record.BuildConfig;


public class LogManager {
    private static final String TAG = "TEST";
    public static final boolean DEBUG = BuildConfig.DEBUG;
    public static void i(String msg){
        if(DEBUG){
            Log.i(TAG, msg);
        }
    }
    public static void d(String msg){
        if(DEBUG){
            Log.d(TAG, msg);
        }
    }
    public static void e(String msg){
        if(DEBUG){
            Log.e(TAG, msg);
        }
    }

    public static void i(String tag, String msg){
        if(DEBUG){
            Log.i(tag, msg);
        }
    }
    public static void d(String tag, String msg){
        if(DEBUG){
            Log.d(tag, msg);
        }
    }
    public static void e(String tag, String msg){
        if(DEBUG){
            Log.e(tag, msg);
        }
    }
}
