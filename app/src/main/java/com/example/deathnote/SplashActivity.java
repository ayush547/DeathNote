package com.example.deathnote;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SplashActivity extends Activity {

    TextView welcome;
    Button police,crime,fav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        welcome = findViewById(R.id.welcomeText);
        police = findViewById(R.id.policeForce);
        crime = findViewById(R.id.crimeDetails);
        fav = findViewById(R.id.favourites);

        ObjectAnimator animation = ObjectAnimator.ofFloat(welcome, "translationY", 200f);
        animation.setDuration(2000);
        animation.start();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                police.setVisibility(View.VISIBLE);
                crime.setVisibility(View.VISIBLE);
                fav.setVisibility(View.VISIBLE);
            }
        },2100);
    }

    public void outToPoliceForce(View view) {
        Intent outToPoliceForce = new Intent(this,MainActivity.class);
        startActivity(outToPoliceForce);
    }

    public void outToCrimeDetails(View view) {
        Intent outToCrimeSelector = new Intent(this,DetailsCrimesSelectorActivity.class);
        startActivity(outToCrimeSelector);
    }

    public void outToFavourites(View view) {
        Intent outToFav = new Intent(this,FavouriteCrimesActivity.class);
        startActivity(outToFav);
    }
}
