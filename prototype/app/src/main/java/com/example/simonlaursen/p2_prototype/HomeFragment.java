package com.example.simonlaursen.p2_prototype;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

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
        RunProgressBar(v);
        cmTimer = (Chronometer) v.findViewById(R.id.cmTimer);


        // Inflate the layout for this fragment
        return v;
    }

    private void RunProgressBar(View v) {
        ProgressBar progressBar = v.findViewById(R.id.progressBar);

        progressBar.setMax(database.getInt("maxProgress"));
        progressBar.setProgress(database.getInt("currentProgress"), true);

        TextView showProgress = v.findViewById(R.id.showProgress);
        showProgress.setText(database.getProgressText());


    }

    private void InputButtons(View v) {
        final Fragment fragment = this;

        ImageButton inputExercise = v.findViewById(R.id.InputExercise);
        ImageButton inputInsulin = v.findViewById(R.id.InputInsulin);
        ImageButton instantButton = v.findViewById(R.id.instantButton);

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
        final TextView startText = v.findViewById(R.id.startText);
        instantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = 0;
                long elapsedMillis = SystemClock.elapsedRealtime() - cmTimer.getBase();
                // TextView startText = v.findViewById(R.id.startText);
                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(100);
                }
                if (timerStopped) {
                    cmTimer.setBase(SystemClock.elapsedRealtime());
                    startText.setText("STOP");
                    cmTimer.start();
                    timerStopped = false;
                } else {
                    cmTimer.stop();

                    startText.setText("START");
                    //database.setValue(100, "currentProgress");
                    if (elapsedMillis > 6) {
                        current = database.getInt("currentProgress");
                        database.setValue(current + 100, "currentProgress");
                    }

                    //currentProgress+=elapsedTime;
                    //progressBar.setProgress(database.getInt("currentProgress"),true);
                    cmTimer.setText("00:00");

                    // startText.setText("STOP");
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