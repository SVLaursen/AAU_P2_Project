package com.example.simonlaursen.p2_prototype;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
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
    int count = 0;
    boolean cancel = true;
    String timeInputValue;
    int s;
    int current1;

    public HomeFragment() {
        database = new Database();

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

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.AppTheme_DialogTheme);

        View newView = null; //Null until changed when a specific dialog is chosen

        final Fragment fragment = this;
        final Vibrator vibrator = (Vibrator) fragment.getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        final ProgressBar progressBar =(ProgressBar) fragment.getView().findViewById(R.id.progressBar);
        final TextView showProgress = fragment.getView().findViewById(R.id.showProgress);

        if (type == "insulin") {
            //THIS IS WHERE YOU PUT INTERACTION FOR THE INSULIN INPUT DIALOG!!!!
            count = 0;
            newView = getLayoutInflater().inflate(R.layout.dialog_insulin_input, null);
            ImageButton cancelButton = newView.findViewById(R.id.cancelButton);
            ImageButton okButton = newView.findViewById(R.id.okButton);
            final EditText timeInput = newView.findViewById(R.id.timeInput);
            //final TextView amount1 = newView.findViewById(R.id.amount1);
            //final TextView amount2 = newView.findViewById(R.id.amount2);
            //final TextView amount3 = newView.findViewById(R.id.amount3);
            final TextView[] amount = {newView.findViewById(R.id.amount1), newView.findViewById(R.id.amount2), newView.findViewById(R.id.amount3)};
            final TextView[] date = {newView.findViewById(R.id.date1), newView.findViewById(R.id.date2), newView.findViewById(R.id.date3)};
            final TextView[] time = {newView.findViewById(R.id.time1), newView.findViewById(R.id.time2), newView.findViewById(R.id.time3)};


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
                            amount[0].setText(timeInput.getText().toString());
                            date[0].setText(formattedDate);
                            time[0].setText(f);
                            System.out.println(count);
                            count++;
                            break;
                        case 1:
                            amount[1].setText(timeInput.getText().toString());
                            date[1].setText(formattedDate);
                            time[1].setText(f);
                            System.out.println(count);
                            count++;
                            break;
                        case 2:
                            amount[2].setText(timeInput.getText().toString());
                            date[2].setText(formattedDate);
                            time[2].setText(f);
                            System.out.println(count);
                            count = 0;
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
                    /*
                    if (cancel) {
                        count = 0;
                    }
                    */

                }
            });


        } else if (type == "exercise") {
            //THIS IS WHERE YOU PUT INTERACTION FOR THE exercise DIALOG!!!!
            count = 0;
            newView = getLayoutInflater().inflate(R.layout.dialog_insulin_input, null);
            ImageButton cancelButton = newView.findViewById(R.id.cancelButton);
            ImageButton okButton = newView.findViewById(R.id.okButton);
            final EditText timeInput = newView.findViewById(R.id.timeInput);
            //final TextView amount1 = newView.findViewById(R.id.amount1);
            //final TextView amount2 = newView.findViewById(R.id.amount2);
            //final TextView amount3 = newView.findViewById(R.id.amount3);

            final TextView[] amount = {newView.findViewById(R.id.amount1), newView.findViewById(R.id.amount2), newView.findViewById(R.id.amount3)};
            final TextView[] date = {newView.findViewById(R.id.date1), newView.findViewById(R.id.date2), newView.findViewById(R.id.date3)};
            final TextView[] time = {newView.findViewById(R.id.time1), newView.findViewById(R.id.time2), newView.findViewById(R.id.time3)};


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

                        /*
                        if(count < 3) {
                            amount[count].setText(timeInput.getText().toString());
                            date[count].setText(formattedDate);
                            time[count].setText(f);
                            count++;
                            if(count == 3){
                                count = 0;
                            }
                        }
                        */


                    switch (count) {
                        case 0:
                            amount[0].setText(timeInput.getText().toString());
                            s = Integer.parseInt(timeInput.getText().toString());
                            date[0].setText(formattedDate);
                            time[0].setText(f);
                            count++;
                            current1 = database.getInt("currentProgress");
                            if (s > 1) {

                                current1 = database.getInt("currentProgress");

                                long input47 =s+current1;

                                database.setInt((int) input47, "currentProgress");


                                // setting the current progress on the progress bar and animating the change
                                showProgress.setText(database.getProgressText());
                                progressBar.setProgress(database.getInt("currentProgress"),true);

                                dialog.cancel();
                            }
                            break;
                        case 1:
                            amount[1].setText(timeInput.getText().toString());
                            date[1].setText(formattedDate);
                            time[1].setText(f);
                            System.out.println(count);
                            count++;

                            break;
                        case 2:
                            amount[2].setText(timeInput.getText().toString());
                            date[2].setText(formattedDate);
                            time[2].setText(f);
                            System.out.println(count);
                            count = 0;
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

                    /*
                    if (cancel) {
                        count = 0;
                    }
                    */
                }
            });

        }
    }





}
