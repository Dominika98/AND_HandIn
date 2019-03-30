package com.example.loveforhealth;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WorkoutDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "workouts.db";
    private static final int DATABASE_VERSION = 1;

    public WorkoutDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_WORKOUTS_TABLE = "CREATE TABLE " + WorkoutContract.WorkoutEntry.TABLE_NAME +
                "(" + WorkoutContract.WorkoutEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_DATE + " TEXT NOT NULL, " +
                WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_TYPE + " TEXT NOT NULL, " +
                WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_DURATION + " INTEGER NOT NULL, " +
                WorkoutContract.WorkoutEntry.COLUMN_WORKOUT_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
        db.execSQL(SQL_CREATE_WORKOUTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
