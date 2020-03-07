package com.ahsan.wallpaper.repository;


import com.ahsan.wallpaper.executor.Executor;
import com.ahsan.wallpaper.manager.AppFileManager;
import com.ahsan.wallpaper.model.HitsItem;
import com.ahsan.wallpaper.model.ImageInfoResponse;
import com.ahsan.wallpaper.picture.model.FileStateModel;
import com.ahsan.wallpaper.util.AppLogger;

import androidx.lifecycle.MutableLiveData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private static final Repository ourInstance = new Repository();

    public static Repository getInstance() {
        return ourInstance;
    }

    private Repository() {
    }

    public void getPicturesFromApi(MutableLiveData<ImageInfoResponse> imageInfoResponseMutableLiveData) {

        String image_type = "photo";
        Boolean editorChoice = true;
        String orientation = "vertical";
        int size = 100;
        int page = 1;

        Executor.execute(() -> {
            Retrofit retrofit = getRetrofit();

            Api api = retrofit.create(Api.class);

            Call<ImageInfoResponse> call = api.getImagesFromServer(Api.BASE_URL, image_type,
                    editorChoice, orientation, size, page);


            call.enqueue(new Callback<ImageInfoResponse>() {
                @Override
                public void onResponse(Call<ImageInfoResponse> call, Response<ImageInfoResponse> response) {
                    AppLogger.log(this.toString(), response.code() + "  " + call.request().url());
                    //AppLogger.log(this.toString(), response.body().toString());
                    imageInfoResponseMutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<ImageInfoResponse> call, Throwable t) {
                    AppLogger.log(this.toString(), t + "");
                }
            });


        });
        ;
    }

    public void downloadPicture(HitsItem hitsItem, MutableLiveData<FileStateModel> fileStateModelMutableLiveData) {

        Executor.execute(()->{
            Retrofit retrofit = getRetrofit();

            Api api = retrofit.create(Api.class);

            Call<ResponseBody> call = api.downloadFile(hitsItem.getLargeImageURL());

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        boolean status =
                                AppFileManager.getInstance().writeResponseBodyToDisk(response.body());
                        if(status){

                        }
                        else{

                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

        });
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
