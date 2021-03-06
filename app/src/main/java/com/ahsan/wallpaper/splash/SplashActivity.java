package com.ahsan.wallpaper.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.ahsan.wallpaper.R;
import com.ahsan.wallpaper.manager.AppActivityManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AppActivityManager.gotoMainActivity(SplashActivity.this);
            }
        }, 500);
    }
}
