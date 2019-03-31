package com.example.loveforhealth;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.WaterViewHolder> {


    @NonNull
    @Override
    public WaterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull WaterViewHolder waterViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class WaterViewHolder extends RecyclerView.ViewHolder {

        TextView amount;
        TextView date;
        ImageView icon;

        WaterViewHolder(View viewItem) {
            super(viewItem);
            amount = itemView.findViewById(R.id.tv_water_amount);
            date = itemView.findViewById(R.id.tv_water_date);
            icon = itemView.findViewById(R.id.iv_icon_water);
        }

    }
}
