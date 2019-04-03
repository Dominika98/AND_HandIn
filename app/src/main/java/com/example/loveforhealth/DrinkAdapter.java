package com.example.loveforhealth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.WaterViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    DrinkAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    @NonNull
    @Override
    public WaterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.water_layout, parent, false);
        return  new DrinkAdapter.WaterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaterViewHolder waterViewHolder, int position) {
        if(!mCursor.moveToPosition(position)) {
            return;
        }
        int amount = mCursor.getInt(mCursor.getColumnIndex(WaterContract.WaterEntry.COLUMN_WATER_AMOUNT));
        String date = mCursor.getString(mCursor.getColumnIndex(WaterContract.WaterEntry.COLUMN_WATER_DATE)).trim();

        waterViewHolder.amount.setText(amount + "ml / 2000ml");
        waterViewHolder.date.setText(date);

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
