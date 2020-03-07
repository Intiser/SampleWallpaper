package com.ahsan.wallpaper.picture.viewmodel;

import com.ahsan.wallpaper.model.HitsItem;
import com.ahsan.wallpaper.picture.model.FileStateModel;
import com.ahsan.wallpaper.repository.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ImageViewModel extends ViewModel {

    private MutableLiveData<FileStateModel> fileStateModelMutableLiveData = new MutableLiveData<>();

    public LiveData<FileStateModel> getFileStateModelMutableLiveData() {
        return fileStateModelMutableLiveData;
    }

    public void downloadFile(HitsItem hitsItem){
        Repository.getInstance().downloadPicture(hitsItem, fileStateModelMutableLiveData);
    }


}
