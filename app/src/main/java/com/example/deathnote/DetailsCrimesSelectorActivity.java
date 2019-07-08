package com.example.deathnote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.weiwangcn.betterspinner.library.BetterSpinner;

public class DetailsCrimesSelectorActivity extends Activity {

    BetterSpinner monthSpinner, yearSpinner;
    EditText latView, lngView;
    TextView invalid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_crimes_selector);
        invalid = findViewById(R.id.invalid);
        latView = findViewById(R.id.latEditText);
        lngView = findViewById(R.id.lngEditText);
        monthSpinner = findViewById(R.id.monthSpinner);
        yearSpinner = findViewById(R.id.yearSpinner);
        ArrayAdapter<String> arrayAdapterMonth = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, this.getResources().getStringArray(R.array.monthList));
        ArrayAdapter<String> arrayAdapterYear = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, this.getResources().getStringArray(R.array.yearList));
        monthSpinner.setAdapter(arrayAdapterMonth);
        yearSpinner.setAdapter(arrayAdapterYear);
    }

    public void checker(View view) {
        double lat, lng;
        int month, year;
        lat = Double.parseDouble("0" + latView.getText().toString());
        lng = Double.parseDouble("0" + lngView.getText().toString());
        switch ("" + monthSpinner.getText().toString()) {
            case "Jan":
                month = 1;
                break;
            case "Feb":
                month = 2;
                break;
            case "Mar":
                month = 3;
                break;
            case "Apr":
                month = 4;
                break;
            case "May":
                month = 5;
                break;
            case "Jun":
                month = 6;
                break;
            case "Jul":
                month = 7;
                break;
            case "Aug":
                month = 8;
                break;
            case "Sep":
                month = 9;
                break;
            case "Oct":
                month = 10;
                break;
            case "Nov":
                month = 11;
                break;
            case "Dec":
                month = 12;
                break;
            default:
                month = 0;
        }
        year = Integer.parseInt("0" + yearSpinner.getText().toString());
        if (lat < 50.1 || lat > 60.1 || lng < -7.6 || lng > 1.7 || (month < 6 && year == 2016) || (month > 5 && year == 2019) || year == 0) {
            invalid.setVisibility(View.VISIBLE);
        } else {
            Intent outToCrimeList = new Intent(this, MainActivityCrime.class);
            outToCrimeList.putExtra("lat", lat);
            outToCrimeList.putExtra("lng", lng);
            outToCrimeList.putExtra("date", year + "-" + month);
            startActivity(outToCrimeList);
            finish();
        }
    }
}
