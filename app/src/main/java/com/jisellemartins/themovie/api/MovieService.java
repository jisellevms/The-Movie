package com.jisellemartins.themovie.api;

import com.jisellemartins.themovie.model.ResponseMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface MovieService {

    @GET("now_playing?api_key=c2e78b4a8c14e65dd6e27504e6df95ad")
    Call<ResponseMovie> recuperaDados(@Header("Content-Type") String contentType);
}
