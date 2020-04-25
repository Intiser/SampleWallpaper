package com.ahsan.wallpaper.picture.model;

import java.io.File;

public class FileStateModel {

    public static int SUCCESSFULL = 200;
    public static int UNAUTHORIZED = 401;

    private int code;

    private File file;

    public FileStateModel(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
