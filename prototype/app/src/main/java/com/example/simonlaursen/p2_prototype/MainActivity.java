package com.example.simonlaursen.p2_prototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INCOMING BUTTON SETUP
        Button home = findViewById(R.id.HomeButton);
        Button calendar = findViewById(R.id.CalendarButton);
        Button profile = findViewById(R.id.ProfileButton);
        Button inputExercise = findViewById(R.id.ExerciseInput);
        Button inputInsulin = findViewById(R.id.InsulinInput);
        //END BUTTON SETUP

        //TODO: Add in a check if the current activity is running and if it is then don't go to it
        //TODO: Kill off other activities when they are left
        //TODO: OOP the button process.


        //INCOMING BUTTON ACTIONS
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You are on home.", Toast.LENGTH_SHORT).show();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Calendar button pressed", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(), CalendarActivity.class);
                intent.getStringExtra("key");
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Profile button pressed",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(),ProfileActivity.class); //Go to profile activity
                intent.getStringExtra("key");
                startActivity(intent);
                //finish();
            }
        });

        inputExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Input Exercise pressed",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(), InputExerciseActivity.class);
                intent.getStringExtra("key");
                startActivityForResult(intent,0);
            }
        });

        inputInsulin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Input Insulin pressed",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
