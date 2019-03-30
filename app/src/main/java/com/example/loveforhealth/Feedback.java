package com.example.loveforhealth;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Feedback extends AppCompatActivity {

    Toolbar myToolbar;
    TextView display;
    EditText input;
    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        myToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        display = (TextView) findViewById(R.id.feedback_display);
        input = (EditText) findViewById(R.id.feedback_text);
        btn_send = (Button) findViewById(R.id.send_btn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference("feedback");

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.push().setValue(input.getText().toString());
                displayFeedback();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences text = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String feedback = text.getString("feedback", "");
        if(feedback.equals("")) {
            display.setText("You didn't post any feedback yet. Please post so we can improve your experience!");
        }
        else {
            display.setText(feedback);
        }
    }

    public void displayFeedback() {
        display.setText(input.getText().toString());

        SharedPreferences text = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = text.edit();
        editor.putString("feedback",input.getText().toString());
        editor.apply();

        input.setText("");
    }
}
