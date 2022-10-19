package com.watchingu.learning;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        MoviesApi moviesApi = retrofit.create(MoviesApi.class);
        Callback moviesCallback = new Callback<MoviesResult>() {
            @Override
            public void onResponse(Call<MoviesResult> call, Response<MoviesResult> response) {
                MoviesResult body = response.body();
                List<Movie> movies = body.getMovies();
                for (int i = 0; i < movies.size(); i++) {
                    Log.d("TAG", "onResponse: " + movies.get(i).getTitle());
                }
            }

            @Override
            public void onFailure(Call<MoviesResult> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t);
            }
        };

        moviesApi.getMovies("9fa99c02931117a67a7649be011c49da").enqueue(moviesCallback);
    }
}
