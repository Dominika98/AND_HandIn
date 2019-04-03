package com.example.loveforhealth;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CupSettings extends AppCompatActivity {

    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cup_settings);

        myToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        createRadioButtons();
    }

    private void createRadioButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group);

        int[] numSizes = getResources().getIntArray(R.array.cup_sizes);

        for(int i = 0; i < numSizes.length; i++) {
            final int numSize = numSizes[i];

            RadioButton button = new RadioButton(this);
            button.setText(numSize + "ml");

            group.addView(button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    String strDate = format.format(calendar.getTime());

                    WaterDbHelper waterDbHelper = new WaterDbHelper(CupSettings.this);
                    SQLiteDatabase db = waterDbHelper.getWritableDatabase();

                    String selection = WaterContract.WaterEntry.COLUMN_WATER_DATE + " = ?";
                    String[] arguments = {strDate};

                    int newSize = numSize;

                    ContentValues values = new ContentValues();
                    values.put(WaterContract.WaterEntry.COLUMN_WATER_CUP, newSize);

                    int count = db.update(WaterContract.WaterEntry.TABLE_NAME, values, selection, arguments);

                    Toast.makeText(CupSettings.this, "New size set to " + newSize + "ml", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
