package com.watchingu.learning;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApi {
    @GET("discover/movie")
    Call<MoviesResult> getMovies(@Query("api_key") String apiKey);
}
