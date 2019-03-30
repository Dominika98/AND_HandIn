package com.example.loveforhealth;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WorkoutEditor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Toolbar myToolbar;
    public String mType = "";
    EditText durationET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_editor);

        myToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        Spinner spinner = (Spinner) findViewById(R.id.spinner_workouts);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.workouts,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        durationET = (EditText) findViewById(R.id.edit_duration);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selection = (String) parent.getItemAtPosition(position);
        if (!TextUtils.isEmpty(selection)) {
            if (selection.equals(getString(R.string.type_swimming))) {
                mType = WorkoutContract.WorkoutEntry.TYPE_SWIMMING;
            } else if (selection.equals(getString(R.string.type_cardio))) {
                mType = WorkoutContract.WorkoutEntry.TYPE_CARDIO;
            }else if (selection.equals(getString(R.string.type_running))) {
                mType = WorkoutContract.WorkoutEntry.TYPE_RUNNING;
            }else if (selection.equals(getString(R.string.type_cycling))) {
                mType = WorkoutContract.WorkoutEntry.TYPE_CYCLING;
            }else if (selection.equals(getString(R.string.type_skating))) {
                mType = WorkoutContract.WorkoutEntry.TYPE_SKATING;
            }else if (selection.equals(getString(R.string.type_arms))) {
                mType = WorkoutContract.WorkoutEntry.TYPE_S_ARMS;
            }else if (selection.equals(getString(R.string.type_chest))) {
                mType = WorkoutContract.WorkoutEntry.TYPE_S_CHEST;
            }else if (selection.equals(getString(R.string.type_legs))) {
                mType = WorkoutContract.WorkoutEntry.TYPE_S_LEGS;
            }else if (selection.equals(getString(R.string.type_stomach))) {
                mType = WorkoutContract.WorkoutEntry.TYPE_S_STOMACH;
            }else {
                mType = WorkoutContract.WorkoutEntry.TYPE_S_BACK;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mType = WorkoutContract.WorkoutEntry.TYPE_RUNNING;
    }

    public void save(View view) {
        if(durationET.getText().toString().equals(""))
        {
            Toast.makeText(this, "Please insert duration!", Toast.LENGTH_SHORT).show();
            return;
        }

        WorkoutDbHelper petDbHelper = new WorkoutDbHelper(this);
        SQLiteDatabase db = petDbHelper.getWritableDatabase();

        String duration = durationET.getText().toString().trim();
        int durationNum = Integer.parseInt(duration);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = format.format(calendar.getTime());

        ContentValues values = new ContentValues();
        values.put(WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_DATE, strDate);
        values.put(WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_TYPE, mType);
        values.put(WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_DURATION, durationNum);

        db.insert(WorkoutContract.WorkoutEntry.TABLE_NAME, null, values);

        Intent intent = new Intent(this, ViewWorkouts.class);
        intent.putExtra("swap", true);
        startActivity(intent);
    }
}
