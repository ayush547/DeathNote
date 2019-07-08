package com.example.deathnote;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.deathnote.API.ForcesAPI;
import com.example.deathnote.DataClass.ForcesData;
import com.example.deathnote.RecyclerViewClasses.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity implements android.support.v7.widget.SearchView.OnQueryTextListener {

    private static final String TAG = "MainActivity";
    SearchView searchBox;
    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    ProgressBar bar;
    private List<ForcesData> dataNames = new ArrayList<>();
    private List<ForcesData> storageCopy = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchBox = findViewById(R.id.searchForces);
        bar = findViewById(R.id.bar);
        searchBox.setOnQueryTextListener(this);
        searchBox.setIconifiedByDefault(false);
        Log.d(TAG, "onCreate started");
        bar.setVisibility(View.VISIBLE);
        initData();

    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ForcesAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ForcesAPI forcesAPI = retrofit.create(ForcesAPI.class);
        Call<List<ForcesData>> callForces = forcesAPI.getForces();
        callForces.enqueue(new Callback<List<ForcesData>>() {
            @Override
            public void onResponse(Call<List<ForcesData>> call, Response<List<ForcesData>> response) {
                storageCopy = response.body();
                for (ForcesData h : storageCopy) {
                    Log.d("name", h.getID());
                }
                bar.setVisibility(View.GONE);
                fitData();
            }

            @Override
            public void onFailure(Call<List<ForcesData>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fitData() {
        dataNames.addAll(storageCopy);
        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initialising Recycler View");
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(this, dataNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String userInput = s.toLowerCase();
        dataNames.clear();
        for (ForcesData f : storageCopy) {
            if (f.getName().toLowerCase().contains(userInput))
                dataNames.add(f);
        }
        adapter.updateList(dataNames);
        return true;
    }
}
