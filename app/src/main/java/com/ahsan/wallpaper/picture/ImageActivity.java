package com.ahsan.wallpaper.picture;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.ahsan.wallpaper.R;
import com.ahsan.wallpaper.databinding.ActivityImageBinding;
import com.ahsan.wallpaper.manager.AppDataManager;
import com.ahsan.wallpaper.manager.AppWallpaperManager;
import com.ahsan.wallpaper.model.HitsItem;
import com.ahsan.wallpaper.picture.model.FileStateModel;
import com.ahsan.wallpaper.picture.viewmodel.ImageViewModel;
import com.bumptech.glide.Glide;

public class ImageActivity extends AppCompatActivity {

    ActivityImageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image);
        initData();
        initView();
        initViewModel();
        handleClicks();
    }

    private ImageViewModel viewModel;

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ImageViewModel.class);
        binding.setViewmodel(viewModel);
        viewModel.getFileStateModelMutableLiveData().observe(this, new Observer<FileStateModel>() {
            @Override
            public void onChanged(FileStateModel fileStateModel) {
                if (fileStateModel.getCode() == FileStateModel.SUCCESSFULL) {
                    if (fileStateModel.getFile() != null) {
                        AppWallpaperManager.getInstance().setWallPaper(fileStateModel
                                        .getFile(), ImageActivity.this,
                                new AppWallpaperManager.Callbacks() {
                                    @Override
                                    public void onSuccesful() {
                                        Handler mainHandler = new Handler(ImageActivity.
                                                this.getMainLooper());

                                        Runnable myRunnable = new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(ImageActivity.this,
                                                        "Your wallpaper is set successfully",
                                                        Toast.LENGTH_LONG).show();
                                            }
                                        };
                                        mainHandler.post(myRunnable);

                                    }
                                });
                    }
                }
            }
        });
    }

    private void initData() {
        hitsItem = AppDataManager.getInstance().getSelectedItem();
    }

    private void handleClicks() {
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.downloadFile(hitsItem);
            }
        });
    }

    private HitsItem hitsItem;

    private void initView() {
        Glide.with(this).load(hitsItem.getLargeImageURL()).into(binding.imageview);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


}
