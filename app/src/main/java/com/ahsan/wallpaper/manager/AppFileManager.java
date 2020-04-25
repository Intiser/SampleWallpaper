package com.ahsan.wallpaper.manager;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

public class AppFileManager {
    private static  AppFileManager ourInstance;

    public static AppFileManager getInstance() {
        return ourInstance;
    }

    private AppFileManager(Context context) {
        wallpaperFile = new File(context.getFilesDir() + File.separator + "wallpaper.png");
    }

    public static void register(Context context){
        if(ourInstance == null){
            ourInstance = new AppFileManager(context);
        }
    }

    public File getWallpaperFile() {
        return wallpaperFile;
    }

    private File wallpaperFile;

    public boolean writeResponseBodyToDisk(ResponseBody body) {
        try {


            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(wallpaperFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d("TAG", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}
