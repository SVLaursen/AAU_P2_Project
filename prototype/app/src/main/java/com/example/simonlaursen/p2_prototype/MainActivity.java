package com.example.simonlaursen.p2_prototype;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
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
    private Fragment currentFragment = null; //Used to check which fragment is currently running

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavBarSetup(); //Used to setup the size of the navigation bar
        loadFragment(new HomeFragment());
    }

    //NAV BAR
    private void NavBarSetup(){
        final ImageButton homeButton = findViewById(R.id.HomeButton);
        final ImageButton profileButton = findViewById(R.id.ProfileButton);
        final ImageButton calendarButton = findViewById(R.id.CalendarButton);

        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        homeButton.setImageResource(R.drawable.homepage_button_clicked);

        //CLICK LISTENERS
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"HOME",Toast.LENGTH_SHORT).show();
                loadFragment(new HomeFragment());//Load the home screen

                homeButton.setImageResource(R.drawable.homepage_button_clicked);
                profileButton.setImageResource(R.drawable.profile_button_unclicked);
                calendarButton.setImageResource(R.drawable.calendar_button_unclicked);

                if(vibrator.hasVibrator()){
                    vibrator.vibrate(10);
                }
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadFragment(new ProfileFragment()); //Load the profile screen

                homeButton.setImageResource(R.drawable.homepage_button_unclicked);
                profileButton.setImageResource(R.drawable.profile_button_clicked);
                calendarButton.setImageResource(R.drawable.calendar_button_unclicked);

                if(vibrator.hasVibrator()){
                    vibrator.vibrate(10);
                }
            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadFragment(new CalendarFragment()); //Load the calendar screen

                homeButton.setImageResource(R.drawable.homepage_button_unclicked);
                profileButton.setImageResource(R.drawable.profile_button_unclicked);
                calendarButton.setImageResource(R.drawable.calendar_button_clicked);

                if(vibrator.hasVibrator()){
                    vibrator.vibrate(10);
                }
            }
        });
    }

    //Load in fragment function
    private void loadFragment(Fragment fragment){
        if(fragment != null && currentFragment != fragment){
            getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, fragment).commit();
            currentFragment = fragment;
        }
    }

}
