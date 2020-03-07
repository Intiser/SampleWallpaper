package com.ahsan.wallpaper.home.viewmodel;

import com.ahsan.wallpaper.model.ImageInfoResponse;
import com.ahsan.wallpaper.repository.Repository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ImageInfoResponse> imageInfoResponseMutableLiveData = new MutableLiveData<>();

    public void getImagesFromApi(){
        Repository.getInstance().getPicturesFromApi(imageInfoResponseMutableLiveData);
    }

    public MutableLiveData<ImageInfoResponse> getImageInfoResponseMutableLiveData() {
        return imageInfoResponseMutableLiveData;
    }
}
