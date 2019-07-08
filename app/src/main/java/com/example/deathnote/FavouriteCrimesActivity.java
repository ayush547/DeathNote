package com.example.deathnote;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.deathnote.DataClass.CrimesData;
import com.example.deathnote.RecyclerViewClasses.RecyclerViewAdapterCrime;
import com.example.deathnote.SQL.SQLContract;
import com.example.deathnote.SQL.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class FavouriteCrimesActivity extends Activity {
    private static final String TAG = "FavouriteCrimesActivity";

    SQLiteHelper helper;
    Cursor res;
    List<CrimesData> storageCopy = new ArrayList<>();
    RecyclerViewAdapterCrime adapter;
    RecyclerView recyclerView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_crimes);
        Toast.makeText(getApplicationContext(),"Fetching Data",Toast.LENGTH_SHORT).show();
        helper = new SQLiteHelper(this);
        res = helper.getFavourites();
        
        if(res.getCount()==0){
            Toast.makeText(getApplicationContext(),"Nothing in favourites", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Loading Favs",Toast.LENGTH_SHORT).show();
            while (res.moveToNext()){
                storageCopy.add(new CrimesData(res.getString(7),res.getString(3),res.getString(4),res.getString(1),res.getString(2),res.getDouble(8),res.getDouble(9),res.getString(5),res.getString(6)));
            }
            initRecyclerView();
        }
    }

    private void initRecyclerView(){
        Log.d(TAG,"initialising Recycler View");
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapterCrime(this,storageCopy);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
