package com.ahsan.wallpaper.util;

import android.util.Log;

import java.lang.ref.Reference;

public class AppLogger {

    public static void log(String tag , String message){
        Log.d(tag, "Logger: " + message);
    }

}
