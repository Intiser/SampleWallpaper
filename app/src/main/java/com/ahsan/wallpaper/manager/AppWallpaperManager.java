package com.ahsan.wallpaper.manager;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ahsan.wallpaper.adapter.ImageListAdapter;
import com.ahsan.wallpaper.executor.Executor;

import java.io.File;
import java.io.IOException;

public class AppWallpaperManager {

    private static AppWallpaperManager appWallpaperManager = new AppWallpaperManager();

    public static AppWallpaperManager getInstance() {
        return appWallpaperManager;
    }

    private AppWallpaperManager(){

    }

    public void setWallPaper(File file, Context context, Callbacks callback){
        Executor.execute(()->{
            String filePath = file.getPath();
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);

            try {
                WallpaperManager.getInstance(context).setBitmap(bitmap);
                callback.onSuccesful();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public interface Callbacks{
        void onSuccesful();
    }

}
