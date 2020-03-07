package com.ahsan.wallpaper.picture.model;

import java.io.File;

public class FileStateModel {

    private static final int FILE_SAVED = 101;
    private static final int FILE_NOT_SAVED = 440;
    private static final int FILE_NOT_DOWNLOADED = 444;

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
