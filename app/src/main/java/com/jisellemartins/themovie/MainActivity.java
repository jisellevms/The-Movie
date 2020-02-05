package com.jisellemartins.themovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.jisellemartins.themovie.adapter.AdapterMovie;
import com.jisellemartins.themovie.api.MovieService;
import com.jisellemartins.themovie.model.ResponseMovie;
import com.jisellemartins.themovie.model.Results;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private ArrayList<Results> results;
    private AdapterMovie adapterMovie;
    private RecyclerView recyclerMoviesBr, recyclerMoviesUs;
    private LinearLayoutManager layoutManagerBr, layoutManagerUs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerMoviesBr = findViewById(R.id.recyclerMoviesBr);
        recyclerMoviesUs = findViewById(R.id.recyclerMoviesUs);
        results = new ArrayList<>();


        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        layoutManagerBr = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        layoutManagerUs = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerMoviesBr.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL));
        recyclerMoviesUs.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL));


        recuperarDadosBr();
        recuperarDadosUs();
    }

    private ArrayList<Results> recuperarDadosBr() {
        MovieService movieService = retrofit.create(MovieService.class);
        Call<ResponseMovie> call = movieService.recuperaDadosBr("aplication/json");


        call.enqueue(new Callback<ResponseMovie>() {
                         @Override
                         public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {
                             if (response.isSuccessful()) {
                                 results = (ArrayList<Results>) response.body().getResults();
                                 adapterMovie = new AdapterMovie(results);
                                 recyclerMoviesBr.setLayoutManager(layoutManagerBr);

                                 recyclerMoviesBr.setHasFixedSize(true);
                                 recyclerMoviesBr.setNestedScrollingEnabled(true);
                                 recyclerMoviesBr.setAdapter(adapterMovie);

                             } else {
                                 Toast.makeText(MainActivity.this, "Algo de errado aconteceu, verifique sua conexão", Toast.LENGTH_SHORT).show();
                             }
                         }

                         @Override
                         public void onFailure(Call<ResponseMovie> call, Throwable t) {
                             Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                         }
                     }
        );
        return results;
    }

    private ArrayList<Results> recuperarDadosUs() {
        MovieService movieService = retrofit.create(MovieService.class);
        Call<ResponseMovie> call = movieService.recuperaDadosUs("aplication/json");


        call.enqueue(new Callback<ResponseMovie>() {
                         @Override
                         public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {
                             if (response.isSuccessful()) {
                                 results = (ArrayList<Results>) response.body().getResults();
                                 adapterMovie = new AdapterMovie(results);
                                 recyclerMoviesUs.setLayoutManager(layoutManagerUs);

                                 recyclerMoviesUs.setHasFixedSize(true);
                                 recyclerMoviesUs.setNestedScrollingEnabled(true);
                                 recyclerMoviesUs.setAdapter(adapterMovie);

                             } else {
                                 Toast.makeText(MainActivity.this, "Algo de errado aconteceu, verifique sua conexão", Toast.LENGTH_SHORT).show();
                             }
                         }

                         @Override
                         public void onFailure(Call<ResponseMovie> call, Throwable t) {
                             Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                         }
                     }
        );
        return results;
    }

}
