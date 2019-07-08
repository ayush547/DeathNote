package com.example.deathnote.RecyclerViewClasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deathnote.DataClass.CrimesData;
import com.example.deathnote.DetailsCrimesActivity;
import com.example.deathnote.R;
import com.example.deathnote.SQL.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterCrime extends RecyclerView.Adapter<RecyclerViewAdapterCrime.ViewHolder> {

    private List<CrimesData> dataNames;
    private ArrayList<String> storageCopyID = new ArrayList<>();
    private Context mContext;
    SQLiteHelper helper;
    Cursor res;


    public RecyclerViewAdapterCrime(Context mContext , List<CrimesData> dataNames) {
        this.dataNames = dataNames;
        this.mContext = mContext;
        helper = new SQLiteHelper(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_layout, viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    private void refreshStorageCopy(){
        storageCopyID.clear();
        res = helper.getFavourites();
        while (res.moveToNext()){
            storageCopyID.add(res.getString(1));
        }
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Log.d("RecyclerViewAdapter","onBind Called.");
        viewHolder.initials.setText(""+dataNames.get(i).getCategory().charAt(0));
        viewHolder.names.setText("\nID - "+dataNames.get(i).getId()+"\nCategory - "+dataNames.get(i).getCategory());
        refreshStorageCopy();
        viewHolder.addToFav.setText(existsInFav(dataNames.get(i).getId())?"-":"+");
        viewHolder.addToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("RecyclerViewHolder","addToFav clicked for "+dataNames.get(i));
                boolean exists = existsInFav(dataNames.get(i).getId());
                if(exists){
                    Toast.makeText(mContext,dataNames.get(i).getId()+" removed",Toast.LENGTH_SHORT).show();
                    helper.DeleteByID(dataNames.get(i).getId());
                    viewHolder.addToFav.setText("+");
                    refreshStorageCopy();
                }
                else {
                    Toast.makeText(mContext, dataNames.get(i).getId() + " added", Toast.LENGTH_SHORT).show();
                    helper.InsertData(dataNames.get(i));
                    viewHolder.addToFav.setText("-");
                    refreshStorageCopy();
                }
                notifyDataSetChanged();

            }
        });
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outToForce = new Intent(mContext, DetailsCrimesActivity.class);
                outToForce.putExtra("persistent_id",dataNames.get(i).getPersistent_id());
                outToForce.putExtra("id",dataNames.get(i).getId());
                if(dataNames.get(i).getOutcome_status()!=null){
                    outToForce.putExtra("result_date",dataNames.get(i).getOutcome_date());
                    outToForce.putExtra("result",dataNames.get(i).getOutcome_category());
                }
                outToForce.putExtra("lat",dataNames.get(i).getLatitude());
                outToForce.putExtra("lng",dataNames.get(i).getLongitude());
                outToForce.putExtra("location_type",dataNames.get(i).getLocation_type());
                outToForce.putExtra("date",dataNames.get(i).getMonth());
                mContext.startActivity(outToForce);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataNames.size();
    }

    private boolean existsInFav(String id){
        for(String h: storageCopyID){
            if(h.equals(id)) return true;
        }
        return false;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView initials,names;
        Button addToFav;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initials = itemView.findViewById(R.id.initials);
            names = itemView.findViewById(R.id.names);
            addToFav = itemView.findViewById(R.id.addToFav);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
    public void updateList(List<CrimesData> dataNames){
        this.dataNames = dataNames;
        notifyDataSetChanged();
    }
}

