package com.example.loveforhealth;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

    private ArrayList<Workout> workouts;

    WorkoutAdapter(ArrayList<Workout> workouts) {
        this.workouts = workouts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.workout_layout, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.date.setText(workouts.get(position).getDate());
        viewHolder.type.setText(workouts.get(position).getType());
        viewHolder.duration.setText(workouts.get(position).getDuration() + " min");
        viewHolder.icon.setImageResource(R.drawable.run);
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView date;
        TextView type;
        TextView duration;
        ImageView icon;

        ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.tv_data_date);
            type = itemView.findViewById(R.id.tv_data_type);
            duration = itemView.findViewById(R.id.tv_data_duration);
            icon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
