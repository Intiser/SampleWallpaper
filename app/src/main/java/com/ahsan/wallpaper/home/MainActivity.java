package com.ahsan.wallpaper.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.ahsan.wallpaper.R;
import com.ahsan.wallpaper.databinding.ActivityMainBinding;
import com.ahsan.wallpaper.home.HomeFragment;
import com.ahsan.wallpaper.manager.AppFragmentManager;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initFragment();
    }

    private void initFragment() {
        AppFragmentManager.sharedInstance().setFragmentManager(getSupportFragmentManager());
        AppFragmentManager.sharedInstance().setFragmentContainerViewId(binding.layoutContainer.getId());
        AppFragmentManager.sharedInstance().addFragmentToBackStack(HomeFragment.newInstance(), null);
    }


}
