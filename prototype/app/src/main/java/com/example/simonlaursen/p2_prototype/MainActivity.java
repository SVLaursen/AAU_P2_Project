package com.example.simonlaursen.p2_prototype;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
    Okay, so this is the main activity, which will run the whole thing!
    The program itself, such as the home screen, calendar screen, etc. will all be fragments loaded up inside this.
    The reason why we do this is because it'll be much easier to do hotfixes along the way, as well as accessing the data we need.
     */


    //GLOBAL VARIABLES HERE!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavBarSetup(); //Used to setup the size of the navigation bar
    }

    //NAV BAR
    private void NavBarSetup(){

        //TODO: Load up the different fragments!

        ImageButton homeButton = findViewById(R.id.HomeButton);
        ImageButton profileButton = findViewById(R.id.ProfileButton);
        ImageButton calendarButton = findViewById(R.id.CalendarButton);

        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"You pressed home", Toast.LENGTH_SHORT).show();

                if(vibrator.hasVibrator()){
                    vibrator.vibrate(10);
                }
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"You pressed profile", Toast.LENGTH_SHORT).show();

                if(vibrator.hasVibrator()){
                    vibrator.vibrate(10);
                }
            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"You pressed calendar",Toast.LENGTH_SHORT).show();

                if(vibrator.hasVibrator()){
                    vibrator.vibrate(10);
                }
            }
        });
    }

}
