package com.ahsan.wallpaper.application;

import android.app.Application;

import com.ahsan.wallpaper.manager.AppFileManager;

public class WallpaperApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppFileManager.register(this);
    }
}
