package com.ahsan.wallpaper.manager;

import android.app.Activity;
import android.content.Intent;

import com.ahsan.wallpaper.home.MainActivity;
import com.ahsan.wallpaper.picture.ImageActivity;

public class AppActivityManager {

    public static void gotoMainActivity(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    public static void gotoImageActivity(Activity activity){
        Intent intent = new Intent(activity, ImageActivity.class);
        activity.startActivity(intent);
    }

}
