package com.watchingu.learning.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MoviesDao {
    @Query("SELECT * FROM movies")
    List<MovieEntity> getAllMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovie(MovieEntity movieEntity);

    @Query("DELETE FROM movies WHERE id = :movieId")
    void deleteMovie(Long movieId);

}
