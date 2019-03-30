package com.example.loveforhealth;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    WorkoutAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.workout_layout, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        if(!mCursor.moveToPosition(position)) {
            return;
        }
        String date = mCursor.getString(mCursor.getColumnIndex(WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_DATE)).trim();
        String type = mCursor.getString(mCursor.getColumnIndex(WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_TYPE)).trim();
        int duration = mCursor.getInt(mCursor.getColumnIndex(WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_DURATION));

        viewHolder.date.setText(date);
        viewHolder.type.setText(type);
        viewHolder.duration.setText(duration + " min");
        if(type.equals("Cycling"))
            viewHolder.icon.setImageResource(R.drawable.cycling);
        else if(type.equals("Swimming"))
            viewHolder.icon.setImageResource(R.drawable.swimming);
        else if(type.equals("Running"))
            viewHolder.icon.setImageResource(R.drawable.running);
        else if(type.equals("Cardio"))
            viewHolder.icon.setImageResource(R.drawable.cardio);
        else if(type.equals("Roler/Ice Skating"))
            viewHolder.icon.setImageResource(R.drawable.skating);
        else if(type.equals("Strength - Stomach"))
            viewHolder.icon.setImageResource(R.drawable.stomach);
        else
            viewHolder.icon.setImageResource(R.drawable.strength);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if(mCursor !=null) {
            mCursor.close();
        }
        mCursor = newCursor;

        if(newCursor !=null) {
            notifyDataSetChanged();
        }
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
