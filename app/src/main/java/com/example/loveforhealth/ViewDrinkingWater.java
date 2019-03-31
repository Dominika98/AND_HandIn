package com.example.loveforhealth;

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

        mDrinkAdapter = new DrinkAdapter();
        drinkList.setAdapter(mDrinkAdapter);
    }
}
