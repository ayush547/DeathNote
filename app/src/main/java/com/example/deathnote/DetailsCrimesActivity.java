package com.example.deathnote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsCrimesActivity extends Activity {

    String persistentId,id,resultDate,resultCategory,locationType,date;
    Double lat,lng;
    TextView details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_crimes);
        Intent in = getIntent();
        persistentId = in.getStringExtra("persistent_id");
        id =in.getStringExtra("id");
        resultDate = in.getStringExtra("result_date");
        resultCategory = in.getStringExtra("result");
        lat = in.getDoubleExtra("lat",0);
        lng = in.getDoubleExtra("lng",0);
        locationType = in.getStringExtra("location_type");
        date = in.getStringExtra("date");
        details = findViewById(R.id.detailCrimeView);
        details.setText(
                "Date of Incident - "+fitter(date)+
                "\n\nPersistent Id - "+fitter(persistentId)+
                        "\nID - "+fitter(id)+
                        "\n\nLocation -"+
                        "\n\t Latitude - "+lat+
                        "\n\t Longitude - "+lng+
                        "\n\t Type - "+fitter(locationType)+
                        "\n\nResult - "+fitter(resultCategory)+
                        "\nDate of Verdict - "+fitter(resultDate));
    }

    private String fitter(String s){
        if(s==null||s.equals("")){
            return "Not Available";
        }
        return s;
    }
}
