package com.watchingu.learning.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MovieEntity.class}, version = 1)
public abstract class MoviesDatabase extends RoomDatabase {
    public abstract MoviesDao getMoviesDao();
}
