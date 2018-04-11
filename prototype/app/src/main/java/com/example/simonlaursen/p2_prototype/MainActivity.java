package com.example.simonlaursen.p2_prototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private boolean isActive; //Used to change between workout activated and deactivated

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INCOMING BUTTON SETUP
        ImageButton home = findViewById(R.id.HomeButton);
        ImageButton calendar = findViewById(R.id.CalendarButton);
        ImageButton profile = findViewById(R.id.ProfileButton);
        ImageButton inputExercise = findViewById(R.id.ExerciseInput);
        ImageButton inputInsulin = findViewById(R.id.InsulinInput);
        final ImageButton startWorkout = findViewById(R.id.centerButton); //Test button
        //END BUTTON SETUP


        //TODO: OOP the button process.
        //TODO: Make a functioning calendar
        //TODO: Implement the final graphical elements
        //TODO: Finish up profile activity and design for drop down menus


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
                Intent intent = new Intent(v.getContext(), CalendarActivity.class);
                intent.getStringExtra("key");
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ProfileActivity.class); //Go to profile activity
                intent.getStringExtra("key");
                startActivity(intent);
            }
        });

        inputExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this, R.style.AppTheme_DialogTheme);
                View mView = getLayoutInflater().inflate(R.layout.dialogue_input_exercise,null);
                //final EditText mTime = mView.findViewById(R.id.yourTime);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                dialog.setCanceledOnTouchOutside(true);

                ImageButton backButton = mView.findViewById(R.id.backButton);

                backButton.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        dialog.cancel();
                    }
                });
            }
        });

        inputInsulin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Input Insulin pressed",Toast.LENGTH_SHORT).show();

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this, R.style.AppTheme_DialogTheme);
                View mView = getLayoutInflater().inflate(R.layout.dialogue_input_insulin,null);
                //final EditText mInsulin = mView.findViewById(R.id.inputInsulin);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                dialog.setCanceledOnTouchOutside(true);

                ImageButton backButton = mView.findViewById(R.id.backButton);

                backButton.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        dialog.cancel();
                    }
                });
            }
        });

        startWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isActive){
                    startWorkout.setImageResource(R.drawable.main_center_clicked);
                    Toast.makeText(getApplicationContext(),"You've started workout",Toast.LENGTH_SHORT).show();
                    isActive = true;
                }
                else{
                    startWorkout.setImageResource(R.drawable.main_center_unclicked);
                    Toast.makeText(getApplicationContext(),"Workout stopped",Toast.LENGTH_SHORT).show();
                    isActive = false;
                }
            }
        });

    }
}
