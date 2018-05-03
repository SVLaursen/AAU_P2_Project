package com.example.simonlaursen.p2_prototype;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
//<<<<<<< HEAD
import android.util.Log;
import android.view.ActionMode;
//=======
//>>>>>>> bc94204667c5f4af7d39997827975c3df7899c15
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import org.w3c.dom.Text;

public class HomeFragment extends Fragment {

    private Database database;
    Chronometer cmTimer;
    Boolean timerStopped = true;
    int count = 0;
    boolean cancel = true;
    String timeInputValue;

    public HomeFragment() {
        database = new Database();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, null);
        InputButtons(v);
        cmTimer = v.findViewById(R.id.cmTimer);
        cmTimer.setText(" ");
        RunProgressBar(v);
<<<<<<< HEAD
        cmTimer = (Chronometer) v.findViewById(R.id.cmTimer);
=======
>>>>>>> bc94204667c5f4af7d39997827975c3df7899c15


        // Inflate the layout for this fragment
        return v;
    }
<<<<<<< HEAD

    private void RunProgressBar(View v) {
=======
    // method for updating progress bar
    private void RunProgressBar(View v){
>>>>>>> bc94204667c5f4af7d39997827975c3df7899c15
        ProgressBar progressBar = v.findViewById(R.id.progressBar);
        // setting the max progress the progress bar can have
        progressBar.setMax(database.getInt("maxProgress"));
<<<<<<< HEAD
        progressBar.setProgress(database.getInt("currentProgress"), true);

=======
        // setting the current progress on the progress bar and animating the change
        progressBar.setProgress(database.getInt("currentProgress"),true);
        // calling the textview for showing the progress
>>>>>>> bc94204667c5f4af7d39997827975c3df7899c15
        TextView showProgress = v.findViewById(R.id.showProgress);
        //calling the get progress method from database and changing the number to fit the currentprogress
        showProgress.setText(database.getProgressText());
    }
    // method for the input buttons
  private void InputButtons(View v) {
        final Fragment fragment = this;
        // calling the objects which is imported in the top of the document.
        // calling the cmTimer and the related textviews.
        cmTimer  =  v.findViewById(R.id.cmTimer);
        // These objects are final to ensure that the reference to the object can not be changed.
        final ProgressBar progressBar = v.findViewById(R.id.progressBar);
        final TextView showProgress = v.findViewById(R.id.showProgress);
        final TextView progressBarSubtitle = v.findViewById(R.id.progressBarSubtitle);
        //Here we define the 3 buttons which will be used input data.
        ImageButton inputExercise = v.findViewById(R.id.InputExercise);
        ImageButton inputInsulin = v.findViewById(R.id.InputInsulin);
        ImageButton instantButton = v.findViewById(R.id.instantButton);
        // vibrator do stuff if it is there
        final Vibrator vibrator = (Vibrator) fragment.getActivity().getSystemService(Context.VIBRATOR_SERVICE);

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
        //The text viev called start text is defined.
        final TextView startText = v.findViewById(R.id.startText);
        // A listener is setup for the instant button
        instantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // In this if statement everything that happens when clicking the instant button happens
            // The timer starts, and progress is counted up and added to the current progress once the timer is stopped again.
            public void onClick(View v) {
                //int used to keep track of current progress
                int current;
                // long used to kep track of how long the timer has been going for
                long elapsedMillis = SystemClock.elapsedRealtime() - cmTimer.getBase();
                cmTimer.setText(" ");
                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(100);
                }
                // if the timer if stopped, we need it to start, and do the preperations by removing and changing text
                if (timerStopped) {
                    cmTimer.setBase(SystemClock.elapsedRealtime());
                    startText.setText("STOP");
                    showProgress.setText(database.getBlankText());
                    progressBarSubtitle.setText(database.getBlankText());
                    cmTimer.start();
                    // timer is set to false, to make the else part of the statement happens when the button is pressed again
                    timerStopped = false;
                } else {
                    //the timer is stopped, and if it counted more than 60.000 milliseconds( 1 minute) the timer is added to the current value and the progress is updated
                    cmTimer.stop();
                    startText.setText("START");
                    if (elapsedMillis > 60000) {
                        // asking the database for the current amount of progress on the progressbar
                        current = database.getInt("currentProgress");
<<<<<<< HEAD
                        database.setValue(current + 100, "currentProgress");
=======
                        // dividing by 60.000 to make it from milliseconds into minutes
                        long input =elapsedMillis/60000+current;
                        // updating the progress in the database
                        // typecased the long to be an int 
                        database.setInt((int) input, "currentProgress");
                        //updating the progress bar
                        long abe =elapsedMillis/60000+current;

                       // System.out.print(abe);
                        database.setInt((int) abe, "currentProgress");
                        progressBar.setProgress(database.getInt("currentProgress"),true);
>>>>>>> bc94204667c5f4af7d39997827975c3df7899c15
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

        }
    }





}