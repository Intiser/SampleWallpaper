package com.ahsan.wallpaper.repository;

import com.ahsan.wallpaper.model.ImageInfoResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {
    public static final String BASE_URL = "https://pixabay.com/api/?key=7465948-a3a3b94191491194b5f1d08b8";

    @GET
    Call<ImageInfoResponse> getImagesFromServer(
            @Url String url,
            @Query("image_type") String image_type,
            @Query("editors_choice") Boolean editorsChoice,
            @Query("orientation") String orientation,
            @Query("per_page") int size,
            @Query("page") int page);

    @GET
    Call<ImageInfoResponse> getImagesFromServer(
            @Query("image_type") String image_type,
            @Query("editors_choice") Boolean editorsChoice,
            @Query("orientation") String orientation,
            @Query("per_page") int size,
            @Query("page") int page);


    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);
}
