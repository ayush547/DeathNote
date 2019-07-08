package com.example.deathnote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deathnote.API.ForcesAPI;
import com.example.deathnote.DataClass.ForceDataDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsForcesActivity extends Activity {
    ForceDataDetails dataDetails;
    TextView forceName, forceID, forceDescription;
    String id;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_forces);
        bar = findViewById(R.id.bar);
        forceName = findViewById(R.id.forceTitle);
        forceDescription = findViewById(R.id.forceDescription);
        forceID = findViewById(R.id.forceID);
        Intent in = getIntent();
        id = in.getStringExtra("id");
        bar.setVisibility(View.VISIBLE);
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ForcesAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ForcesAPI forcesAPI = retrofit.create(ForcesAPI.class);
        Call<ForceDataDetails> callForces = forcesAPI.getSpecificForces(id);
        callForces.enqueue(new Callback<ForceDataDetails>() {
            @Override
            public void onResponse(Call<ForceDataDetails> call, Response<ForceDataDetails> response) {
                dataDetails = response.body();
                bar.setVisibility(View.GONE);
                fitData();
            }

            @Override
            public void onFailure(Call<ForceDataDetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fitData() {
        forceName.setVisibility(View.VISIBLE);
        forceID.setVisibility(View.VISIBLE);
        forceDescription.setVisibility(View.VISIBLE);
        forceName.setText("Department of " + dataDetails.getName());
        forceID.setText("ID - " + dataDetails.getId());
        forceDescription.setText("Description - " + (dataDetails.getDescription() != null ? Html.fromHtml(dataDetails.getDescription()).toString() : "Not Available") + "\nURL - " + dataDetails.getUrl() + "\n\nContact - " + dataDetails.getTelephone());
    }
}
