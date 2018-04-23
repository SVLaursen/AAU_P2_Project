package com.example.simonlaursen.p2_prototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class EventListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        ImageButton home = findViewById(R.id.HomeButton);
        ImageButton calendar = findViewById(R.id.CalendarButton);
        ImageButton profile = findViewById(R.id.ProfileButton);
        ImageButton morgen = findViewById(R.id.morgenButton);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class);
                intent.getStringExtra("key");
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CalendarActivity.class);
                intent.getStringExtra("key");
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ProfileActivity.class); //Go to profile activity
                intent.getStringExtra("key");
                startActivity(intent);
                finish();
            }
        });

        morgen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),JoinEventActivity.class); //Go to join event
                intent.getStringExtra("key");
                startActivity(intent);
                finish();
            }
        });
    }
}
