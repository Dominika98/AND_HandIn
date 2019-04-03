package com.example.loveforhealth;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class ViewDrinkingWater extends AppCompatActivity {

    Toolbar myToolbar;
    RecyclerView drinkList;
    RecyclerView.Adapter mDrinkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drinking_water);

        myToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        drinkList = findViewById(R.id.rv_water);
        drinkList.hasFixedSize();
        drinkList.setLayoutManager(new LinearLayoutManager(this));

        mDrinkAdapter = new DrinkAdapter(this, getAllDrinks());
        drinkList.setAdapter(mDrinkAdapter);
    }

    private Cursor getAllDrinks() {
        WaterDbHelper waterDbHelper = new WaterDbHelper(this);
        SQLiteDatabase db = waterDbHelper.getReadableDatabase();

        String[] projection = {
                WaterContract.WaterEntry._ID,
                WaterContract.WaterEntry.COLUMN_WATER_AMOUNT,
                WaterContract.WaterEntry.COLUMN_WATER_DATE,
                WaterContract.WaterEntry.COLUMN_WATER_CUP};

        return db.query(
                WaterContract.WaterEntry.TABLE_NAME,
                projection,
                null, null, null, null,
                WaterContract.WaterEntry.COLUMN_WATER_TIMESTAMP + " DESC");
    }
}
