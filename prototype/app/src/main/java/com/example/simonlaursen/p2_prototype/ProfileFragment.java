package com.example.simonlaursen.p2_prototype;


import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ProfileFragment extends Fragment {

    //TODO: The whole thing actually
    private Database database = new Database(); //database setup for this fragment

    public ProfileFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        final MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.timerActive = false;

        InputButtons(v); //activates the input buttons

        return v;
    }

    private void InputButtons(View v){
        final Fragment fragment = this; //Used to declare what fragment is currently working for the vibrator to work

        ImageButton resultsButton = v.findViewById(R.id.resultsButton);
        ImageButton graphsButton = v.findViewById(R.id.graphButton);
        ImageButton statisticButton = v.findViewById(R.id.statisticButton);

        final Vibrator vibrator = (Vibrator) fragment.getActivity().getSystemService(Context.VIBRATOR_SERVICE); //Sets up the vibrator

        resultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vibrator.hasVibrator()){
                    vibrator.vibrate(10);
                }
                DisplayDialog(v,"results");
            }
        });

        graphsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vibrator.hasVibrator()){
                    vibrator.vibrate(10);
                }
                DisplayDialog(v,"graphs");
            }
        });

        statisticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vibrator.hasVibrator()){
                    vibrator.vibrate(10);
                }
                DisplayDialog(v,"statistics");
            }
        });
    }

    private void DisplayDialog(View v, String type){
        /*
        This one is used to create dialog pop-ups containing information.
        It takes in the view that we're current in and then a string to determine which pop-up we want.
         */

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(),R.style.AppTheme_DialogTheme);
        View newView = null; //Null until changed when a specific dialog is chosen

        if(type == "results"){
            newView = getLayoutInflater().inflate(R.layout.dialog_results,null);

            builder.setView(newView);
            final AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if(type == "graphs"){
            newView = getLayoutInflater().inflate(R.layout.dialog_graph,null);

            builder.setView(newView);
            final AlertDialog dialog = builder.create();
            dialog.show();
            DisplayGraph(newView);
        }
        else if(type == "statistics"){
            newView = getLayoutInflater().inflate(R.layout.dialog_statistics,null);

            builder.setView(newView);
            final AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    private void Latest(View v){

    }

    private void DisplayGraph(View v){
        GraphView graphView = (GraphView) v.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graphView.addSeries(series);
    }


}
