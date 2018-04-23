package com.example.simonlaursen.p2_prototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageButton home = findViewById(R.id.HomeButton);
        ImageButton calendar = findViewById(R.id.CalendarButton);
        ImageButton profile = findViewById(R.id.ProfileButton);
        ImageButton editButton = findViewById(R.id.EditButton);
        ImageButton graferButton = findViewById(R.id.GraferButton);
        ImageButton resultatButton = findViewById(R.id.resultaterButton);
        ImageButton statistikButton = findViewById(R.id.statistikButton);

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
                Intent intent = new Intent(v.getContext(), CalendarActivity.class);
                intent.getStringExtra("key");
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"You are here",Toast.LENGTH_SHORT).show();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Edit profile",Toast.LENGTH_SHORT).show();
            }
        });

        graferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Show graphs",Toast.LENGTH_SHORT).show();

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ProfileActivity.this, R.style.AppTheme_DialogTheme);
                View mView = getLayoutInflater().inflate(R.layout.dialogue_graphs,null);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                dialog.setCanceledOnTouchOutside(true);
            }
        });

        resultatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Show results",Toast.LENGTH_SHORT).show();
            }
        });

        statistikButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Show statistik",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
