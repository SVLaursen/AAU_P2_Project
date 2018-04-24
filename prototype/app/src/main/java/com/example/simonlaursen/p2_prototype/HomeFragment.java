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

        final Vibrator vibrator = (Vibrator) fragment.getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        inputExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vibrator.hasVibrator()){
                    vibrator.vibrate(10);
                }

                //TODO: Dialogue Pop-up
            }
        });

        inputInsulin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vibrator.hasVibrator()){
                    vibrator.vibrate(10);
                }

                DisplayDialog(v,"insulin");

                //TODO: Dialogue Pop-up
            }
        });
    }

    private void DisplayDialog(View v, String type){
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        View newView = null;

        if(type == "insulin"){
            newView = getLayoutInflater().inflate(R.layout.dialog_insulin_input,null);
        }
        else if(type == "exercise"){
            //TODO: Popup for exercise input
        }

        if(newView != null){
            builder.setView(newView);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

}
