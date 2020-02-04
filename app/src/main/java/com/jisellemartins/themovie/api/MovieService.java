package com.jisellemartins.themovie.api;

import com.jisellemartins.themovie.model.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {

    @GET("now_playing?api_key=c2e78b4a8c14e65dd6e27504e6df95ad&language=pt-BR&page=1")
    Call<List<Results>> recuperaDados();
}
