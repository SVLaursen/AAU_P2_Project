package com.example.simonlaursen.p2_prototype;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    /*
    Okay, so this is the main activity, which will run the whole thing!
    The program itself, such as the home screen, calendar screen, etc. will all be fragments loaded up inside this.
    The reason why we do this is because it'll be much easier to do hotfixes along the way, as well as accessing the data we need.
     */


    //GLOBAL VARIABLES HERE!
    private Fragment currentFragment = null; //Used to check which fragment is currently running
    private Database database = new Database();
    private File saveFile;
    private String savedData = "savedData";
    public boolean timerActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPref.init(getApplicationContext());
        NavBarSetup(); //Used to setup the size of the navigation bar
        loadFragment(new HomeFragment());
        database.loadData();
    }

    //NAV BAR
    private void NavBarSetup() {
        final ImageButton homeButton = findViewById(R.id.HomeButton);
        final ImageButton profileButton = findViewById(R.id.ProfileButton);
        final ImageButton calendarButton = findViewById(R.id.CalendarButton);

        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        homeButton.setImageResource(R.drawable.homepage_button_clicked); //Initial screen to load

        //CLICK LISTENERS
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timerActive) {
                    loadFragment(new HomeFragment());//Load the home screen

                    homeButton.setImageResource(R.drawable.homepage_button_clicked);
                    profileButton.setImageResource(R.drawable.profile_button_unclicked);
                    calendarButton.setImageResource(R.drawable.calendar_button_unclicked);
                } else {
                    loadDialog(v,"Home");
                }

                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(10);
                }
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!timerActive){
                    loadFragment(new ProfileFragment()); //Load the profile screen

                    homeButton.setImageResource(R.drawable.homepage_button_unclicked);
                    profileButton.setImageResource(R.drawable.profile_button_clicked);
                    calendarButton.setImageResource(R.drawable.calendar_button_unclicked);
                }
                else{
                    loadDialog(v,"Profile");
                }

                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(10);
                }
            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!timerActive){
                    loadFragment(new CalendarFragment()); //Load the calendar screen

                    homeButton.setImageResource(R.drawable.homepage_button_unclicked);
                    profileButton.setImageResource(R.drawable.profile_button_unclicked);
                    calendarButton.setImageResource(R.drawable.calendar_button_clicked);
                }
                else{
                    loadDialog(v,"Calendar");
                }

                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(10);
                }
            }
        });
    }

    //Load in fragment function
    private void loadFragment(Fragment fragment) {
        if (fragment != null && currentFragment != fragment) {
            getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, fragment).commit();
            currentFragment = fragment;
        }
    }

    private void loadDialog(View v, String name){
        final ImageButton homeButton = findViewById(R.id.HomeButton);
        final ImageButton profileButton = findViewById(R.id.ProfileButton);
        final ImageButton calendarButton = findViewById(R.id.CalendarButton);

        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(),R.style.AppTheme_DialogTheme);
        View newView = getLayoutInflater().inflate(R.layout.dialog_alert_stop_timer,null);
        final String _name = name;

        if(vibrator.hasVibrator()){
            vibrator.vibrate(100); //Warning vibration
        }

        ImageButton cancelButton = newView.findViewById(R.id.cancelButton);
        ImageButton acceptButton = newView.findViewById(R.id.acceptButton);

        builder.setView(newView);
        final AlertDialog dialog = builder.create();
        dialog.show();

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vibrator.hasVibrator()){
                    vibrator.vibrate(10);
                }
                dialog.cancel();
            }
        });

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(vibrator.hasVibrator()){
                    vibrator.vibrate(10);
                }

                if(_name == "Home") {
                    loadFragment(new HomeFragment());//Load the home screen

                    homeButton.setImageResource(R.drawable.homepage_button_clicked);
                    profileButton.setImageResource(R.drawable.profile_button_unclicked);
                    calendarButton.setImageResource(R.drawable.calendar_button_unclicked);
                }
                else if(_name == "Profile"){
                    loadFragment(new ProfileFragment()); //Load the profile screen

                    homeButton.setImageResource(R.drawable.homepage_button_unclicked);
                    profileButton.setImageResource(R.drawable.profile_button_clicked);
                    calendarButton.setImageResource(R.drawable.calendar_button_unclicked);
                }
                else if(_name == "Calendar"){
                    loadFragment(new CalendarFragment()); //Load the calendar screen

                    homeButton.setImageResource(R.drawable.homepage_button_unclicked);
                    profileButton.setImageResource(R.drawable.profile_button_unclicked);
                    calendarButton.setImageResource(R.drawable.calendar_button_clicked);
                }

                dialog.cancel();
            }
        });
    }

    //When the app closes
    public void OnDestroy() {
        super.onDestroy();

        saveFile = new File(getFilesDir(), savedData);
    }

    private String readSaveData(Context context) {
        //TODO: Read save data
        return null; //Temp code
    }
}
