package com.watchingu.learning;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.watchingu.learning.db.MovieEntity;
import com.watchingu.learning.db.MoviesDao;
import com.watchingu.learning.db.MoviesDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.crypto.Mac;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "zlo";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MoviesDatabase database = Room.databaseBuilder(getApplicationContext(), MoviesDatabase.class, "movies-database")
                .build();
        MoviesDao moviesDao = database.getMoviesDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<MovieEntity> movieEntityList = moviesDao.getAllMovies();
                if (movieEntityList.isEmpty()) {
                    Log.d(TAG, "movies list is empty");
                } else {
                    for (int i = 0; i < movieEntityList.size(); i++) {
                        Log.d(TAG, "movie" + movieEntityList.get(i));
                    }
                }

                moviesDao.saveMovie(new MovieEntity("Ruslan", "ruslan image"));
                moviesDao.saveMovie(new MovieEntity("Ruslan 1", "ruslan image 1"));
                moviesDao.saveMovie(new MovieEntity("Ruslan 2", "ruslan image 2"));
                moviesDao.saveMovie(new MovieEntity("Ruslan 3", "ruslan image 3"));
                movieEntityList = moviesDao.getAllMovies();
                if (movieEntityList.isEmpty()) {
                    Log.d(TAG, "movies list is empty");
                } else {
                    for (int i = 0; i < movieEntityList.size(); i++) {
                        Log.d(TAG, "movie" + movieEntityList.get(i));
                        moviesDao.deleteMovie(movieEntityList.get(i).getId());
                    }
                }
                movieEntityList = moviesDao.getAllMovies();
                Log.d(TAG, "movies list is empty " + movieEntityList.isEmpty());
            }
        }).start();
    }
}
