package com.example.loveforhealth;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewWorkouts extends AppCompatActivity {

    RecyclerView workoutList;
    WorkoutAdapter workoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workouts);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        workoutList = findViewById(R.id.rv);
        workoutList.hasFixedSize();
        workoutList.setLayoutManager(new LinearLayoutManager(this));
        workoutAdapter = new WorkoutAdapter(this, getAllWorkouts());
        workoutList.setAdapter(workoutAdapter);
        trySwap();
    }

    private void trySwap() {
        boolean swap = false;
        try {
            Bundle bundle = getIntent().getExtras();
            swap = bundle.getBoolean("swap");
        } catch (Exception e) {

        }
        if(swap) {
            workoutAdapter.swapCursor(getAllWorkouts());
        }
    }

    private Cursor getAllWorkouts() {
        WorkoutDbHelper workoutDbHelper = new WorkoutDbHelper(this);
        SQLiteDatabase db = workoutDbHelper.getReadableDatabase();

        String[] projection = {
                WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_DATE,
                WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_TYPE,
                WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_DURATION};

        return db.query(
                WorkoutContract.WorkoutEntry.TABLE_NAME,
                projection,
                null, null, null, null,
                WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_TIMESTAMP + " DESC");
    }
}
