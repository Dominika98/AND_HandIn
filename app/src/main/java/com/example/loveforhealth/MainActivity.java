package com.example.loveforhealth;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar myToolbar;
    TextView waterDetailsAmount;
    TextView waterDetailsDate;
    TextView workoutType;
    TextView workoutDuration;
    TextView workoutDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);
        
        waterDetailsAmount = (TextView) findViewById(R.id.tv_water_details_amount);
        waterDetailsDate = (TextView) findViewById(R.id.tv_water_details_date);

        workoutType = (TextView) findViewById(R.id.tv_wtype);
        workoutDuration = (TextView) findViewById(R.id.tv_wduration);
        workoutDate = (TextView) findViewById(R.id.tv_wdate);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, myToolbar, R.string.open, R.string.close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        updateWaterView();
        latestWorkout();
    }

    private void latestWorkout() {
        WorkoutDbHelper workoutDbHelper = new WorkoutDbHelper(this);
        SQLiteDatabase db = workoutDbHelper.getReadableDatabase();

        String[] projection = {
                WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_DATE,
                WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_TYPE,
                WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_DURATION};

        Cursor cursor = db.query(
                WorkoutContract.WorkoutEntry.TABLE_NAME,
                projection,
                null, null, null, null,
                WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_TIMESTAMP + " DESC");

        if(cursor.moveToFirst()) {
            String type = cursor.getString(cursor.getColumnIndex(WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_TYPE));
            int duration = cursor.getInt(cursor.getColumnIndex(WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_DURATION));
            String date = cursor.getString(cursor.getColumnIndex(WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_DATE));

            workoutDate.setText(date);
            workoutDuration.setText(duration + "min");
            workoutType.setText(type);
        }
    }

    public void drink(View view) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = format.format(calendar.getTime());

        WaterDbHelper waterDbHelper = new WaterDbHelper(this);
        SQLiteDatabase db = waterDbHelper.getReadableDatabase();

        String[] projection = {
                WaterContract.WaterEntry._ID,
                WaterContract.WaterEntry.COLUMN_WATER_AMOUNT,
                WaterContract.WaterEntry.COLUMN_WATER_DATE,
                WaterContract.WaterEntry.COLUMN_WATER_CUP};

        String selection = WaterContract.WaterEntry.COLUMN_WATER_DATE + " = ?";
        String[] arguments = {strDate};

        Cursor cursor = db.query(
                WaterContract.WaterEntry.TABLE_NAME,
                projection,
                selection,
                arguments,
                null,
                null, null);

        if (cursor.moveToFirst()) {

            int amount = cursor.getInt(cursor.getColumnIndex(WaterContract.WaterEntry.COLUMN_WATER_AMOUNT));
            int cupSize = cursor.getInt(cursor.getColumnIndex(WaterContract.WaterEntry.COLUMN_WATER_CUP));
            int newAmount = amount + cupSize;

            db = waterDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(WaterContract.WaterEntry.COLUMN_WATER_AMOUNT, newAmount);

            int count = db.update(WaterContract.WaterEntry.TABLE_NAME, values, selection, arguments);
            
            updateWaterView();
        } else {
            Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show();
        }

    }

    private void updateWaterView() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = format.format(calendar.getTime());

        WaterDbHelper waterDbHelper = new WaterDbHelper(this);
        SQLiteDatabase db = waterDbHelper.getReadableDatabase();

        String[] projection = {
                WaterContract.WaterEntry._ID,
                WaterContract.WaterEntry.COLUMN_WATER_AMOUNT,
                WaterContract.WaterEntry.COLUMN_WATER_DATE,
                WaterContract.WaterEntry.COLUMN_WATER_CUP};

        String selection = WaterContract.WaterEntry.COLUMN_WATER_DATE + " = ?";
        String[] arguments = {strDate};

        Cursor cursor = db.query(
                WaterContract.WaterEntry.TABLE_NAME,
                projection,
                selection,
                arguments,
                null,
                null, null);


        if (cursor.moveToFirst()) {
            int amount = cursor.getInt(cursor.getColumnIndex(WaterContract.WaterEntry.COLUMN_WATER_AMOUNT));
            waterDetailsDate.setText(cursor.getString(cursor.getColumnIndex(WaterContract.WaterEntry.COLUMN_WATER_DATE)));
            waterDetailsAmount.setText(amount + "ml / 2000ml");
        }
        else {
            db = waterDbHelper.getWritableDatabase();


            ContentValues values = new ContentValues();
            values.put(WaterContract.WaterEntry.COLUMN_WATER_AMOUNT, 0);
            values.put(WaterContract.WaterEntry.COLUMN_WATER_DATE, strDate);
            values.put(WaterContract.WaterEntry.COLUMN_WATER_CUP, 200);

            db.insert(WaterContract.WaterEntry.TABLE_NAME, null, values);

            waterDetailsDate.setText(strDate);
            waterDetailsAmount.setText("0");
        }
        db.close();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;
        switch (menuItem.getItemId()) {
            case R.id.display_data:
                break;
            case R.id.add_workout:
                intent = new Intent(this, WorkoutEditor.class);
                startActivity(intent);
                break;
            case R.id.cup_settings:
                intent = new Intent(this, CupSettings.class);
                startActivity(intent);
                break;
            case R.id.step_counter:
                intent = new Intent(this, StepCounter.class);
                startActivity(intent);
                break;
            case R.id.workout_data:
                intent = new Intent(this, ViewWorkouts.class);
                startActivity(intent);
                break;
            case R.id.drinking_data:
                intent = new Intent(this, ViewDrinkingWater.class);
                startActivity(intent);
                break;
            case R.id.feedback:
                intent = new Intent(this, Feedback.class);
                startActivity(intent);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
