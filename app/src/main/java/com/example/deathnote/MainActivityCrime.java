package com.example.deathnote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deathnote.API.CrimesAPI;
import com.example.deathnote.DataClass.CrimesData;
import com.example.deathnote.RecyclerViewClasses.RecyclerViewAdapterCrime;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityCrime extends Activity {

    private static final String TAG = "MainActivityCrime";
    double lat, lng;
    String date;
    TextView LatView, LonView, DateView;
    List<CrimesData> storageCopy = new ArrayList<>();
    RecyclerViewAdapterCrime adapter;
    RecyclerView recyclerView;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_crime);
        LatView = findViewById(R.id.latitude);
        LonView = findViewById(R.id.longitude);
        DateView = findViewById(R.id.date);
        bar = findViewById(R.id.bar);

        Intent in = getIntent();
        lat = in.getDoubleExtra("lat", 52.629729);
        lng = in.getDoubleExtra("lng", -1.131592);
        date = in.getStringExtra("date");

        LatView.setText("Latitude - " + lat);
        LonView.setText("Longitude - " + lng);
        DateView.setText("Date - " + date);
        Toast.makeText(getApplicationContext(), "Fetching Data", Toast.LENGTH_SHORT).show();
        bar.setVisibility(View.VISIBLE);
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CrimesAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CrimesAPI crimesAPI = retrofit.create(CrimesAPI.class);
        Call<List<CrimesData>> callForces = crimesAPI.getCrimeDetails(date, lat, lng);
        callForces.enqueue(new Callback<List<CrimesData>>() {
            @Override
            public void onResponse(Call<List<CrimesData>> call, Response<List<CrimesData>> response) {
                storageCopy = response.body();
                for (CrimesData h : storageCopy) {
                    Log.d("name", h.getId());
                }
                bar.setVisibility(View.GONE);
                if(storageCopy.size()!=0) initRecyclerView();
                else Toast.makeText(getApplicationContext(),"No matches found",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<CrimesData>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, t.getMessage());
            }
        });
    }

    private void initRecyclerView() {
        Log.d(TAG, "initialising Recycler View");
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapterCrime(this, storageCopy);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
