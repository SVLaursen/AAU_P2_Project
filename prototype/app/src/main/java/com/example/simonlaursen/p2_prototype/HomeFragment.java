package com.example.simonlaursen.p2_prototype;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.EditText;
import android.view.ActionMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.w3c.dom.Text;
import java.util.concurrent.TimeUnit;

public class HomeFragment extends Fragment {

    private Database database;
    private Chronometer cmTimer;

    private boolean timerStopped = true;
    private boolean ifInput = false;
    private boolean ifInsulin = false;

    private static int s;

    private int count = 0;
    private int insulinCount = 0;
    private int current1;

    // Textview Variables for exercise input
    private String a1 = null;
    private String a2 = null;
    private String a3 = null;

    private String date1 = null;
    private String date2 = null;
    private String date3 = null;

    private String time1 = null;
    private String time2 = null;
    private String time3 = null;

    // Textview Variables for insulin input
    private String u1 = null;
    private String u2 = null;
    private String u3 = null;

    private String insulinDate1 = null;
    private String insulinDate2 = null;
    private String insulinDate3 = null;

    private String insulinTime1 = null;
    private String insulinTime2 = null;
    private String insulinTime3 = null;


    public HomeFragment() {
        database = new Database();
        database.loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,null);
        InputButtons(v);
        cmTimer = v.findViewById(R.id.cmTimer);
        cmTimer.setText(" ");
        RunProgressBar(v);


        // Inflate the layout for this fragment
        return v;
    }

    // method for updating progress bar
    private void RunProgressBar(View v){
        ProgressBar progressBar = v.findViewById(R.id.progressBar);
        progressBar.setMax(database.getInt("maxProgress")); // setting the max progress the progress bar can have
        progressBar.setProgress(database.getInt("currentProgress"),true); // setting the current progress on the progress bar and animating the change
        TextView showProgress = v.findViewById(R.id.showProgress); // calling the textview for showing the progress
        showProgress.setText(database.getProgressText()); //calling the get progress method from database and changing the number to fit the currentprogress
    }

    // Method for the input buttons
    private void InputButtons(View v) {
        final Fragment fragment = this;
        final MainActivity mainActivity = (MainActivity)getActivity();

        // These objects are final to ensure that the reference to the object can not be changed.
        final ProgressBar progressBar = v.findViewById(R.id.progressBar);
        final TextView showProgress = v.findViewById(R.id.showProgress);
        final TextView progressBarSubtitle = v.findViewById(R.id.progressBarSubtitle);

        //Here we define the 3 buttons which will be used input data.
        ImageButton inputExercise = v.findViewById(R.id.InputExercise);
        ImageButton inputInsulin = v.findViewById(R.id.InputInsulin);
        ImageButton instantButton = v.findViewById(R.id.instantButton);

        // Vibrator setup
        final Vibrator vibrator = (Vibrator) fragment.getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        //The text view called start text is defined.
        final TextView startText = v.findViewById(R.id.startText);

        // calling the cmTimer and the related textviews.
        cmTimer  =  v.findViewById(R.id.cmTimer);
        cmTimer.setText(" ");

        inputExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(10);
                }
                DisplayDialog(v, "exercise");
            }
        });

        inputInsulin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(10);
                }
                DisplayDialog(v, "insulin");
            }
        });

        // A listener is setup for the instant button
        instantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                //In this if statement everything that happens when clicking the instant button happens
                //The timer starts, and progress is counted up and added to the current progress once the timer is stopped again.
                */

                int current; //int used to keep track of current progress
                long elapsedMillis = SystemClock.elapsedRealtime() - cmTimer.getBase(); // long used to kep track of how long the timer has been going for

                //Check if the phone has permission to use the vibrator, if true then give strong vibration
                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(100);
                }

                // if the timer if stopped, we need it to start, and do the preperations by removing and changing text
                if (timerStopped) {
                    cmTimer.setBase(SystemClock.elapsedRealtime());
                    startText.setText("STOP");
                    mainActivity.timerActive = true;
                    showProgress.setText(database.getBlankText());
                    progressBarSubtitle.setText(database.getBlankText());
                    cmTimer.start();
                    timerStopped = false; // timer is set to false, to make the else part of the statement happens when the button is pressed again
                }
                else {
                    cmTimer.stop(); //the timer is stopped, and if it counted more than 60.000 milliseconds( 1 minute) the timer is added to the current value and the progress is updated
                    mainActivity.timerActive = false;
                    startText.setText("START");

                    if (elapsedMillis > 60000) {
                        current = database.getInt("currentProgress"); // asking the database for the current amount of progress on the progressbar
                        long input =elapsedMillis/60000+current; // dividing by 60.000 to make it from milliseconds into minutes

                        database.setInt((int) input, "currentProgress"); // updating the progress in the database, while typecasting the long to an int
                        progressBar.setProgress(database.getInt("currentProgress"),true); //updating the progress bar
                    }

                    //the text is shown again, and button is made ready for another press by making timerStopped true again
                    cmTimer.setText(" ");
                    showProgress.setText(database.getProgressText());
                    progressBarSubtitle.setText(database.getMinPerweek());
                    timerStopped = true;
                }
            }
        });

    }

    private void DisplayDialog(View v, String type) {
        /*
        This one is used to create dialog pop-ups containing information.
        It takes in the view that we're current in and then a string to determine which pop-up we want.
         */

        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.AppTheme_DialogTheme);

        View newView = null; //Null until changed when a specific dialog is chosen

        final Fragment fragment = this;
        final Vibrator vibrator = (Vibrator) fragment.getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        final ProgressBar progressBar =(ProgressBar) fragment.getView().findViewById(R.id.progressBar);
        final TextView showProgress = fragment.getView().findViewById(R.id.showProgress);

        if (type == "insulin") {
            //THIS IS WHERE YOU PUT INTERACTION FOR THE INSULIN INPUT DIALOG!!!!


            newView = getLayoutInflater().inflate(R.layout.dialog_insulin_input, null);
            ImageButton cancelButton = newView.findViewById(R.id.cancelButton);
            ImageButton okButton = newView.findViewById(R.id.okButton);

            final EditText unitInput = newView.findViewById(R.id.timeInput);
            TextView[] amount = {newView.findViewById(R.id.amount1), newView.findViewById(R.id.amount2), newView.findViewById(R.id.amount3)};
            TextView[] date = {newView.findViewById(R.id.date1), newView.findViewById(R.id.date2), newView.findViewById(R.id.date3)};
            TextView[] time = {newView.findViewById(R.id.time1), newView.findViewById(R.id.time2), newView.findViewById(R.id.time3)};

            System.out.println(insulinCount);


            if(ifInsulin) {
                switch (insulinCount) {
                    case 0:
                        if(u1 != null) {
                            amount[2].setText(SharedPref.readString("Unit1",null));
                            date[2].setText(SharedPref.readString("insulinDate1",null));
                            time[2].setText(SharedPref.readString("insulinTime1",null));
                        }
                        if(u2 != null) {
                            amount[1].setText(SharedPref.readString("Unit2",null));
                            date[1].setText(SharedPref.readString("insulinDate2",null));
                            time[1].setText(SharedPref.readString("insulinTime2",null));
                        }

                        if(u3 != null){
                            amount[0].setText(SharedPref.readString("Unit3",null));
                            date[0].setText(SharedPref.readString("insulinDate3",null));
                            time[0].setText(SharedPref.readString("insulinTime3",null));
                        }
                        break;
                    case 1:

                        if(u1 != null) {
                            amount[0].setText(SharedPref.readString("Unit1",null));
                            date[0].setText(SharedPref.readString("insulinDate1",null));
                            time[0].setText(SharedPref.readString("insulinTime1",null));
                        }
                        if(u2 != null) {
                            amount[2].setText(SharedPref.readString("Unit2",null));
                            date[2].setText(SharedPref.readString("insulinDate2",null));
                            time[2].setText(SharedPref.readString("insulinTime2",null));
                        }

                        if(u3 != null){
                            amount[1].setText(SharedPref.readString("Unit3",null));
                            date[1].setText(SharedPref.readString("insulinDate3",null));
                            time[1].setText(SharedPref.readString("insulinTime3",null));
                        }
                        break;

                    case 2:

                        if(u1 != null) {
                            amount[1].setText(SharedPref.readString("Unit1",null));
                            date[1].setText(SharedPref.readString("insulinDate1",null));
                            time[1].setText(SharedPref.readString("insulinTime1",null));
                        }

                        if(u2 != null) {
                            amount[0].setText(SharedPref.readString("Unit2",null));
                            date[0].setText(SharedPref.readString("insulinDate2",null));
                            time[0].setText(SharedPref.readString("insulinTime2",null));
                        }

                        if(u3 != null){
                            amount[2].setText(SharedPref.readString("Unit3",null));
                            date[2].setText(SharedPref.readString("insulinDate3",null));
                            time[2].setText(SharedPref.readString("insulinTime3",null));
                        }

                        break;
                }

            }

            builder.setView(newView);
            final AlertDialog dialog = builder.create();
            dialog.show();

            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (vibrator.hasVibrator()) {
                        vibrator.vibrate(10);
                    }

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat currentDate = new SimpleDateFormat("MM-dd");
                    SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
                    String formattedDate = currentDate.format(c.getTime());
                    String f = currentTime.format(c.getTime());


                    switch (insulinCount) {
                        case 0:

                            if(TextUtils.isEmpty(unitInput.getText().toString())){
                                dialog.cancel();
                                break;
                            }
                            else if(Integer.parseInt(unitInput.getText().toString()) <= 0){
                                //int t =database.getInt("medicineWeek");
                                //database.setInt((Integer.parseInt(unitInput.getText().toString()))+t,"medicineWeek");
                                dialog.cancel();
                                break;
                            }

                            //database.setInsulinInputs(formattedDate,f,unitInput.getText().toString());
                            u1 = unitInput.getText().toString();
                            insulinDate1 = formattedDate;
                            insulinTime1 = f;

                            SharedPref.writeString("Unit1",u1);
                            SharedPref.writeString("insulinDate1",insulinDate1);
                            SharedPref.writeString("insulinTime1",insulinTime1);

                            ifInsulin = true;
                            insulinCount++;
                            break;
                        case 1:
                            if(TextUtils.isEmpty(unitInput.getText().toString())){
                                dialog.cancel();
                                break;
                            }
                            else if(Integer.parseInt(unitInput.getText().toString()) <= 0){
                                dialog.cancel();
                                break;
                            }

                            database.setInsulinInputs(formattedDate,f,unitInput.getText().toString());
                            System.out.println(count);

                            u2 = unitInput.getText().toString();
                            insulinDate2 = formattedDate;
                            insulinTime2 = f;

                            SharedPref.writeString("Unit2",u2);
                            SharedPref.writeString("insulinDate2",insulinDate2);
                            SharedPref.writeString("insulinTime2",insulinTime2);

                            insulinCount++;
                            break;
                        case 2:
                            if(TextUtils.isEmpty(unitInput.getText().toString())){
                                dialog.cancel();
                                break;
                            }
                            else if(Integer.parseInt(unitInput.getText().toString()) <= 0){
                                dialog.cancel();
                                break;
                            }

                            database.setInsulinInputs(formattedDate,f,unitInput.getText().toString());
                            System.out.println(count);

                            u3 = unitInput.getText().toString();
                            insulinDate3 = formattedDate;
                            insulinTime3 = f;

                            SharedPref.writeString("Unit3",u3);
                            SharedPref.writeString("insulinDate3",insulinDate3);
                            SharedPref.writeString("insulinTime3",insulinTime3);

                            insulinCount = 0;
                            break;
                    }

                    dialog.cancel();

                }
            });


            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (vibrator.hasVibrator()) {
                        vibrator.vibrate(10);
                    }
                    dialog.cancel();


                }
            });


        } else if (type == "exercise") {
            //THIS IS WHERE YOU PUT INTERACTION FOR THE exercise DIALOG!!!!

            newView = getLayoutInflater().inflate(R.layout.dialog_exercise_input, null);
            ImageButton cancelButton = newView.findViewById(R.id.cancelButton);
            ImageButton okButton = newView.findViewById(R.id.okButton);

            final EditText timeInput = newView.findViewById(R.id.timeInput);
            TextView[] amount = {newView.findViewById(R.id.amount1), newView.findViewById(R.id.amount2), newView.findViewById(R.id.amount3)};
            TextView[] date = {newView.findViewById(R.id.date1), newView.findViewById(R.id.date2), newView.findViewById(R.id.date3)};
            TextView[] time = {newView.findViewById(R.id.time1), newView.findViewById(R.id.time2), newView.findViewById(R.id.time3)};

            if(ifInput) {
                switch (count) {
                    case 0:

                    if(a1 != null) {
                        amount[2].setText(SharedPref.readString("Amount1",null));
                        date[2].setText(SharedPref.readString("exerciseDate1",null));
                        time[2].setText(SharedPref.readString("exerciseTime1",null));
                    }
                    if(a2 != null) {
                        amount[1].setText(SharedPref.readString("Amount2",null));
                        date[1].setText(SharedPref.readString("exerciseDate2",null));
                        time[1].setText(SharedPref.readString("exerciseTime2",null));
                    }

                    if(a3 != null){
                        amount[0].setText(SharedPref.readString("Amount3",null));
                        date[0].setText(SharedPref.readString("exerciseDate3",null));
                        time[0].setText(SharedPref.readString("exerciseTime3",null));
                    }
                        break;
                    case 1:

                    if(a1 != null) {
                        amount[0].setText(SharedPref.readString("Amount1",null));
                        date[0].setText(SharedPref.readString("exerciseDate1",null));
                        time[0].setText(SharedPref.readString("exerciseTime1",null));
                    }
                    if(a2 != null) {
                        amount[2].setText(SharedPref.readString("Amount2",null));
                        date[2].setText(SharedPref.readString("exerciseDate2",null));
                        time[2].setText(SharedPref.readString("exerciseTime2",null));
                    }

                    if(a3!=null){
                        amount[1].setText(SharedPref.readString("Amount3",null));
                        date[1].setText(SharedPref.readString("exerciseDate3",null));
                        time[1].setText(SharedPref.readString("exerciseTime3",null));
                    }
                        break;

                    case 2:

                    if(a1 != null) {
                        amount[1].setText(SharedPref.readString("Amount1",null));
                        date[1].setText(SharedPref.readString("exerciseDate1",null));
                        time[1].setText(SharedPref.readString("exerciseTime1",null));
                    }

                    if(a2 != null) {
                        amount[0].setText(SharedPref.readString("Amount2",null));
                        date[0].setText(SharedPref.readString("exerciseDate2",null));
                        time[0].setText(SharedPref.readString("exerciseTime2",null));
                    }

                    if(a3!=null){
                        amount[2].setText(SharedPref.readString("Amount3",null));
                        date[2].setText(SharedPref.readString("exerciseDate3",null));
                        time[2].setText(SharedPref.readString("exerciseTime3",null));
                    }

                        break;
                }

            }

            builder.setView(newView);
            final AlertDialog dialog = builder.create();
            dialog.show();

            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (vibrator.hasVibrator()) {
                        vibrator.vibrate(10);
                    }

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat currentDate = new SimpleDateFormat("MM-dd");
                    SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
                    String formattedDate = currentDate.format(c.getTime());
                    String f = currentTime.format(c.getTime());

                    switch (count) {
                        case 0:
                            if(TextUtils.isEmpty(timeInput.getText().toString())){
                                dialog.cancel();
                                break;
                            }
                            else if(Integer.parseInt(timeInput.getText().toString()) <= 0){
                                dialog.cancel();
                                break;
                            }

                            s = Integer.parseInt(timeInput.getText().toString());

                            database.setExerciseInputs(formattedDate,f,timeInput.getText().toString());

                            a1 = timeInput.getText().toString();
                            date1 = formattedDate;
                            time1 = f;
                            SharedPref.writeString("Amount1",a1);
                            SharedPref.writeString("exerciseDate1",date1);
                            SharedPref.writeString("exerciseTime1",time1);

                            ifInput = true;
                            count++;
                            if (s > 0) {
                                current1 = database.getInt("currentProgress");

                                long input47 = s + current1;

                                database.setInt((int) input47, "currentProgress");

                                // setting the current progress on the progress bar and animating the change

                                progressBar.setProgress(database.getInt("currentProgress"), true);
                                if (timerStopped && database.getProgressText() != null) {
                                    showProgress.setText(database.getProgressText());
                                }
                                dialog.cancel();
                            }
                            break;
                        case 1:
                            if(TextUtils.isEmpty(timeInput.getText().toString())){
                                dialog.cancel();
                                break;
                            }
                            else if(Integer.parseInt(timeInput.getText().toString()) <= 0){
                                dialog.cancel();
                                break;
                            }

                            s = Integer.parseInt(timeInput.getText().toString());
                            database.setExerciseInputs(formattedDate,f,timeInput.getText().toString());

                            //System.out.println(count);

                            a2 = timeInput.getText().toString();
                            date2 = formattedDate;
                            time2 = f;

                            SharedPref.writeString("Amount2",a2);
                            SharedPref.writeString("exerciseDate2",date2);
                            SharedPref.writeString("exerciseTime2",time2);

                            current1 = database.getInt("currentProgress");
                            count++;

                                current1 = database.getInt("currentProgress");

                                long input48 =s+current1;

                                database.setInt((int) input48, "currentProgress");



                                // setting the current progress on the progress bar and animating the change

                                progressBar.setProgress(database.getInt("currentProgress"),true);
                                if(timerStopped){
                                    showProgress.setText(database.getProgressText());
                                }
                               dialog.cancel();


                            break;
                        case 2:
                            if(TextUtils.isEmpty(timeInput.getText().toString())){
                                dialog.cancel();
                                break;
                            }
                            else if(Integer.parseInt(timeInput.getText().toString()) <= 0){
                                dialog.cancel();
                                break;
                            }

                            s = Integer.parseInt(timeInput.getText().toString());
                            database.setExerciseInputs(formattedDate,f,timeInput.getText().toString());

                            System.out.println(count);
                            count = 0;
                            a3 = timeInput.getText().toString();
                            date3 = formattedDate;
                            time3 = f;

                            SharedPref.writeString("Amount3",a3);
                            SharedPref.writeString("exerciseDate3",date3);
                            SharedPref.writeString("exerciseTime3",time3);

                            if (s > 0) {
                                current1 = database.getInt("currentProgress");

                                long input47 = s + current1;

                                database.setInt((int) input47, "currentProgress");


                                // setting the current progress on the progress bar and animating the change

                                progressBar.setProgress(database.getInt("currentProgress"), true);
                                if (timerStopped && database.getProgressText() != null) {
                                    showProgress.setText(database.getProgressText());
                                }
                                dialog.cancel();
                            }

                            break;
                    }

                }
            });


            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (vibrator.hasVibrator()) {
                        vibrator.vibrate(10);
                    }
                    dialog.cancel();


                }
            });


        }

    }

    //DELETE THIS IF IT ISN'T BEING USED
    public static int setInt(int value, String name){
        if (name == "s")
        {
            s = value;
        }

        return s;
    }

    public static int getInt(String name){
        if (name == "s") {
            return s;
        }

        return s;
    }
}

