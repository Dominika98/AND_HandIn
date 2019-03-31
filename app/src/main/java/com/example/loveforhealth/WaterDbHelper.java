package com.example.loveforhealth;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WaterDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "water.db";
    private static final int DATABASE_VERSION = 1;

    public WaterDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_WATER_TABLE = "CREATE TABLE " + WaterContract.WaterEntry.TABLE_NAME +
                "(" + WaterContract.WaterEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WaterContract.WaterEntry.COLUMN_WATER_AMOUNT + " INTEGER, " +
                WaterContract.WaterEntry.COLUMN_WATER_DATE + " TEXT NOT NULL, " +
                WaterContract.WaterEntry.COLUMN_WATER_CUP + " INTEGER NOT NULL, " +
                WaterContract.WaterEntry.COLUMN_WATER_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
        db.execSQL(SQL_CREATE_WATER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
