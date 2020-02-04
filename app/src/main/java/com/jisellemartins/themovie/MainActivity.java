package com.jisellemartins.themovie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jisellemartins.themovie.api.MovieService;
import com.jisellemartins.themovie.model.Results;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    Button btnDados;
    TextView txtDados;
    Retrofit retrofit;
    ImageView imgDados;
    List<Results> resultsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnDados = findViewById(R.id.btnDados);
        txtDados = findViewById(R.id.txtDados);
        imgDados = findViewById(R.id.imgDados);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btnDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarDados();
            }
        });

    }

    private void recuperarDados() {
        MovieService movieService = retrofit.create(MovieService.class);
        Call<List<Results>> call = movieService.recuperaDados();

        call.enqueue(new Callback<List<Results>>() {
            @Override
            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                if (response.isSuccessful()) {
                    resultsList = response.body();

                    for (int i = 0; i < resultsList.size(); i++) {
                        Results results = resultsList.get(i);
                        results.toString();
                        Log.i("LISTA", "ID: " + results.getId() + "Titulo: " +
                                results.getTitle() + "Poster: " + results.getPoster_path() +
                                "Data de lançamento: " + results.getRelease_date());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Results>> call, Throwable t) {
                Log.i("ERRO", t.getMessage());
            }
        });
    }
}
