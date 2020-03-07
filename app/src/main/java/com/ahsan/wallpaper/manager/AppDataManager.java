package com.ahsan.wallpaper.manager;

import com.ahsan.wallpaper.model.HitsItem;

public class AppDataManager {
    private static final AppDataManager ourInstance = new AppDataManager();

    public static AppDataManager getInstance() {
        return ourInstance;
    }

    private AppDataManager() {
    }

    private HitsItem selectedItem;

    public HitsItem getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(HitsItem selectedItem) {
        this.selectedItem = selectedItem;
    }
}
