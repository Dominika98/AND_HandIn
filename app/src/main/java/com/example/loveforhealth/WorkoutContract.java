package com.example.loveforhealth;

import android.provider.BaseColumns;

public final class WorkoutContract {

    private WorkoutContract() {}

    public class WorkoutEntry implements BaseColumns {

        public  static final String TABLE_NAME = "workouts";

        public static final String ID = BaseColumns._ID;
        public static final String COLUMN_WORKOUT_DATE = "date";
        public static final String COLUMN_WORKOUT_TYPE = "type";
        public static final String COLUMN_WORKOUT_DURATION = "duration";
        public static final String COLUMN_WORKOUT_TIMESTAMP = "timestamp";

        public static final String TYPE_SWIMMING = "Swimming";
        public static final String TYPE_CARDIO = "Cardio";
        public static final String TYPE_RUNNING = "Running";
        public static final String TYPE_CYCLING = "Cycling";
        public static final String TYPE_SKATING = "Roler/Ice Skating";
        public static final String TYPE_S_ARMS = "Strenght - Arms";
        public static final String TYPE_S_CHEST = "Strenght - Chest";
        public static final String TYPE_S_LEGS = "Strenght - Legs";
        public static final String TYPE_S_STOMACH = "Strenght - Stomach";
        public static final String TYPE_S_BACK = "Strenght - Back";

    }
}
