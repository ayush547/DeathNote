package com.example.deathnote.API;

import com.example.deathnote.DataClass.CrimesData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CrimesAPI {
    String BASE_URL ="https://data.police.uk/api/";

    @GET("crimes-street/all-crime")
    Call<List<CrimesData>> getCrimeDetails(@Query("date") String date, @Query("lat") double lat, @Query("lng") double lng);
}
