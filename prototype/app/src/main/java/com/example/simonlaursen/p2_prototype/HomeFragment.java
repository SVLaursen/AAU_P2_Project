package com.example.simonlaursen.p2_prototype;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class HomeFragment extends Fragment {

    private Database database;
    Chronometer cmTimer;
    Boolean timerStopped = true;


    public HomeFragment() {
        database = new Database();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home,null);
        InputButtons(v);
        RunProgressBar(v);
        cmTimer = (Chronometer) v.findViewById(R.id.cmTimer);





        // Inflate the layout for this fragment
        return v;
    }

    private void RunProgressBar(View v){
        ProgressBar progressBar = v.findViewById(R.id.progressBar);

        progressBar.setMax(database.getInt("maxProgress"));
        progressBar.setProgress(database.getInt("currentProgress"),true);

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
                        database.setValue(current+100, "currentProgress");
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
    private void DisplayDialog(View v, String type){
        /*
        This one is used to create dialog pop-ups containing information.
        It takes in the view that we're current in and then a string to determine which pop-up we want.
         */

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        View newView = null; //Null until changed when a specific dialog is chosen
        final Fragment fragment = this;
        final Vibrator vibrator = (Vibrator) fragment.getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        if(type == "insulin"){
            //THIS IS WHERE YOU PUT INTERACTION FOR THE INSULIN INPUT DIALOG!!!!

            newView = getLayoutInflater().inflate(R.layout.dialog_insulin_input,null);
            ImageButton cancelButton = newView.findViewById(R.id.cancelButton);

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

        }
        else if(type == "exercise"){
            //THIS IS WHERE YOU PUT INTERACTION FOR THE INSULIN INPUT DIALOG!!!!

            newView = getLayoutInflater().inflate(R.layout.dialog_exercise_input,null);
            ImageButton cancelButton = newView.findViewById(R.id.cancelButton);

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
        }
    }
}
