package com.example.loveforhealth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, myToolbar, R.string.open, R.string.close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void drink(View view)
    {
        Toast.makeText(this, "Water added.", Toast.LENGTH_SHORT).show();
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
                break;
            case R.id.step_counter:
                break;
            case R.id.workout_data:
                intent = new Intent(this, ViewWorkouts.class);
                startActivity(intent);
                break;
            case R.id.drinking_data:
                break;
            case R.id.general_setting:
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
