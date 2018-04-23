package com.example.simonlaursen.p2_prototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        ImageButton home = findViewById(R.id.HomeButton);
        ImageButton calendar = findViewById(R.id.CalendarButton);
        ImageButton profile = findViewById(R.id.ProfileButton);
        ImageButton calendarMenu = findViewById(R.id.bottomBG);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.getStringExtra("key");
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"You are here",Toast.LENGTH_SHORT).show();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ProfileActivity.class);
                intent.getStringExtra("key");
                startActivity(intent);
                finish();
            }
        });

        calendarMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),EventListActivity.class); //Go to profile activity
                intent.getStringExtra("key");
                startActivity(intent);
                finish();
            }
        });
    }
}
