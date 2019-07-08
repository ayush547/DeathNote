package com.example.deathnote.RecyclerViewClasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

import com.example.deathnote.DataClass.ForcesData;
import com.example.deathnote.DetailsForcesActivity;
import com.example.deathnote.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<ForcesData> dataNames;
    private Context mContext;

    public RecyclerViewAdapter(Context mContext , List<ForcesData> dataNames) {
        this.dataNames = dataNames;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_layout, viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d("RecyclerViewAdapter","onBind Called.");
        viewHolder.initials.setText(""+dataNames.get(i).getName().charAt(0));
        viewHolder.names.setText(dataNames.get(i).getName());
        viewHolder.addToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("RecyclerViewHolder","addToFav clicked for "+dataNames.get(i));
                Toast.makeText(mContext,dataNames.get(i).getID(),Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outToForce = new Intent(mContext, DetailsForcesActivity.class);
                outToForce.putExtra("id",dataNames.get(i).getID());
                mContext.startActivity(outToForce);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataNames.size();
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
            addToFav.setVisibility(View.GONE);
        }
    }

    public void updateList(List<ForcesData> dataNames){
        this.dataNames = dataNames;
        notifyDataSetChanged();
    }
}
