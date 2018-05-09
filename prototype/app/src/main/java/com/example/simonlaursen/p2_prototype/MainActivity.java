package com.example.simonlaursen.p2_prototype;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    /*
    Okay, so this is the main activity, which will run the whole thing!
    The program itself, such as the home screen, calendar screen, etc. will all be fragments loaded up inside this.
    The reason why we do this is because it'll be much easier to do hotfixes along the way, as well as accessing the data we need.
     */


    //GLOBAL VARIABLES HERE!
    private Fragment currentFragment = null; //Used to check which fragment is currently running
    private Database database = new Database();
    public boolean timerActive = false;
    public static int diffinDays;
    public boolean abe = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPref.init(getApplicationContext());
        NavBarSetup(); //Used to setup the size of the navigation bar
        loadFragment(new HomeFragment());
        //SharedPref.wipe(getApplicationContext()); // removes all save data
        database.loadData();
        checkDate();
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
                    loadDialog(v, "Home");
                }

                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(10);
                }
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timerActive) {
                    loadFragment(new ProfileFragment()); //Load the profile screen

                    homeButton.setImageResource(R.drawable.homepage_button_unclicked);
                    profileButton.setImageResource(R.drawable.profile_button_clicked);
                    calendarButton.setImageResource(R.drawable.calendar_button_unclicked);
                } else {
                    loadDialog(v, "Profile");
                }

                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(10);
                }
            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timerActive) {
                    loadFragment(new CalendarFragment()); //Load the calendar screen

                    homeButton.setImageResource(R.drawable.homepage_button_unclicked);
                    profileButton.setImageResource(R.drawable.profile_button_unclicked);
                    calendarButton.setImageResource(R.drawable.calendar_button_clicked);
                } else {
                    loadDialog(v, "Calendar");
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

    private void loadDialog(View v, String name) {
        final ImageButton homeButton = findViewById(R.id.HomeButton);
        final ImageButton profileButton = findViewById(R.id.ProfileButton);
        final ImageButton calendarButton = findViewById(R.id.CalendarButton);

        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.AppTheme_DialogTheme);
        View newView = getLayoutInflater().inflate(R.layout.dialog_alert_stop_timer, null);
        final String _name = name;

        if (vibrator.hasVibrator()) {
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
                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(10);
                }
                dialog.cancel();
            }
        });

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(10);
                }

                if (_name == "Home") {
                    loadFragment(new HomeFragment());//Load the home screen

                    homeButton.setImageResource(R.drawable.homepage_button_clicked);
                    profileButton.setImageResource(R.drawable.profile_button_unclicked);
                    calendarButton.setImageResource(R.drawable.calendar_button_unclicked);
                } else if (_name == "Profile") {
                    loadFragment(new ProfileFragment()); //Load the profile screen

                    homeButton.setImageResource(R.drawable.homepage_button_unclicked);
                    profileButton.setImageResource(R.drawable.profile_button_clicked);
                    calendarButton.setImageResource(R.drawable.calendar_button_unclicked);
                } else if (_name == "Calendar") {
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
    public void onDestroy() {
        super.onDestroy();
        database.SaveData();
    }

    public  void  checkDate() {

        boolean newWeek = SharedPref.readBoolean(SharedPref.newWeek, true);
        if (newWeek) {
            Date startDate = new Date(System.currentTimeMillis());
            SharedPref.writeLong("time", startDate.getTime());
            database.setLong(startDate.getTime(), "StartDate");
            SharedPref.writeBoolean(SharedPref.newWeek, false);

        } else {
            Date CurrentDate = new Date(System.currentTimeMillis());
            Date startdate = new Date(SharedPref.readLong("time", 0));

            int diffinDays = (int) ((CurrentDate.getTime()) - (startdate.getTime()));
            if (diffinDays > 0.00034722222) {
                int day1 = database.getInt("day1");
                int day2 = database.getInt("day2");
                int day3 = database.getInt("day3");
                int day4 = database.getInt("day4");
                int day5 = database.getInt("day5");
                int day6 = database.getInt("day6");
                int day7 = database.getInt("day7");
                if (day1 == 0) {
                    day1 = database.getInt("currentProgress");
                    database.setInt(day1, "day1");
                }
            else if (day2==0) {
                    day2 = database.getInt("currentProgress");
                    database.setInt(day2, "day2");
                }
                else if (day3==0) {
                    day3 = database.getInt("currentProgress");
                    database.setInt( day3, "day3");
                }
                else if (day4==0) {
                    day4 = database.getInt("currentProgress");
                    database.setInt(day4, "day4");
                }
                else if (day5==0) {
                    day5 = database.getInt("currentProgress");
                    database.setInt(day5, "day5");
                }
                else if (day6==0) {
                    day6 = database.getInt("currentProgress");
                    database.setInt(day6, "day6");
                }
                else if (day7==0) {
                    day7 = database.getInt("currentProgress");
                    database.setInt(day7, "day7");
                }



              /*  if (diffinDays > 7) { //change this to 7, i think it works

                    int curr = database.getInt("currentProgress");
                    int max = database.getInt("highestExerciseNum");
                    int all = database.getInt("exerciseAllNum");
                    database.setInt((all + curr), "exerciseAllNum"); // add the amount of progress to the total amount of all progress
                    if (curr >= 150) {
                        int k = (database.getInt("hitGoalNum") + 1);
                        database.setInt(k, "numberOfWeeksNum"); // if the current amount of min at the end of a week is 150 or over, the number of weeks the goal has been met goes up by 1
                    }
                    // if the current amount of progress at the end of a week is higher than the week performed best, the current weak becomes best
                    if (curr > max) {
                        database.setInt(curr, "highestExerciseNum");
                    }
                    database.setInt(0, "currentProgress"); // reset the current progress
                    int i = (database.getInt("numberOfWeeksNum") + 1);
                    database.setInt(i, "numberOfWeeksNum"); //1 is added to the amount of weeks the app has been used
                    database.setInt(0, "medicineWeek");
                    SharedPref.writeLong("time", CurrentDate.getTime());
                    SharedPref.writeBoolean(SharedPref.newWeek, true);

                }
*/
            }

            }

    }
}
