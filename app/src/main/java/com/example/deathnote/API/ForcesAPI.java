package com.example.deathnote.API;

import com.example.deathnote.DataClass.ForceDataDetails;
import com.example.deathnote.DataClass.ForcesData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ForcesAPI {

    String BASE_URL ="https://data.police.uk/api/";

    @GET("forces")
    Call<List<ForcesData>> getForces();

    @GET("forces/{id}")
    Call<ForceDataDetails> getSpecificForces(@Path("id") String id);
}
