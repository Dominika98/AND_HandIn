package com.example.loveforhealth;

import android.provider.BaseColumns;

public final class WaterContract {

    private WaterContract() {}

    public class WaterEntry implements BaseColumns {

        public  static final String TABLE_NAME = "drinks";

        public static final String ID = BaseColumns._ID;
        public static final String COLUMN_WATER_AMOUNT = "amount";
        public static final String COLUMN_WATER_DATE = "date";
        public static final String COLUMN_WATER_CUP = "cup";
        public static final String COLUMN_WATER_TIMESTAMP = "timestamp";

        public static final int CAPACITY_100 = 100;
        public static final int CAPACITY_150 = 150;
        public static final int CAPACITY_200 = 200;
        public static final int CAPACITY_250 = 250;
        public static final int CAPACITY_300 = 300;
        public static final int CAPACITY_400 = 400;
        public static final int CAPACITY_500 = 500;
    }
}
