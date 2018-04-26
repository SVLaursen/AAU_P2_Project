package com.example.simonlaursen.p2_prototype;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class HomeFragment extends Fragment {


    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home,null);
        InputButtons(v);

        // Inflate the layout for this fragment
        return v;
    }

    private void InputButtons(View v){
        final Fragment fragment = this;

        ImageButton inputExercise = v.findViewById(R.id.InputExercise);
        ImageButton inputInsulin = v.findViewById(R.id.InputInsulin);
        ImageButton instantButton = v.findViewById(R.id.instantButton);

        final Vibrator vibrator = (Vibrator) fragment.getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        inputExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vibrator.hasVibrator()){
                    vibrator.vibrate(10);
                }
                DisplayDialog(v,"exercise");
            }
        });

        inputInsulin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vibrator.hasVibrator()){
                    vibrator.vibrate(10);
                }
                DisplayDialog(v,"insulin");
            }
        });

        instantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vibrator.hasVibrator()){
                    vibrator.vibrate(100);
                }
                //TODO: Change function of the grey button
            }
        });
    }

    private void DisplayDialog(View v, String type){
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
