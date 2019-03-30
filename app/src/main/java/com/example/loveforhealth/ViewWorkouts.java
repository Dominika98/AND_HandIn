package com.example.loveforhealth;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class ViewWorkouts extends AppCompatActivity {

    RecyclerView workoutList;
    RecyclerView.Adapter workoutAdapter;

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

        ArrayList<Workout> workouts = new ArrayList<>();
        workouts.add(new Workout("23/04/1998", "Running", 15));
        workouts.add(new Workout("25/03/2009", "Cycling", 45));
        workouts.add(new Workout("02/02/2016", "Swimming", 30));

        workoutAdapter = new WorkoutAdapter(workouts);
        workoutList.setAdapter(workoutAdapter);
    }
}
