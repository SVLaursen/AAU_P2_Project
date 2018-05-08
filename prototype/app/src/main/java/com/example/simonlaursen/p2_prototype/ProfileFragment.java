package com.example.simonlaursen.p2_prototype;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

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
        TextView name =(TextView) v.findViewById(R.id.nameArea);
        InputButtons(v); //activates the input buttons
        String t=(SharedPref.readString("name","Navn Navnesen"));
        name.setText(t);
        return v;
    }

    private void InputButtons(View v){
        final Fragment fragment = this; //Used to declare what fragment is currently working for the vibrator to work

        ImageButton settingsButton = v.findViewById(R.id.settingsButton);
        ImageButton graphsButton = v.findViewById(R.id.graphButton);
        ImageButton statisticButton = v.findViewById(R.id.statisticButton);

        final Vibrator vibrator = (Vibrator) fragment.getActivity().getSystemService(Context.VIBRATOR_SERVICE); //Sets up the vibrator

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vibrator.hasVibrator()){
                    vibrator.vibrate(10);
                }
                DisplayDialog(v,"settings");

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

    private void DisplayDialog(View v, String type) {
        /*
        This one is used to create dialog pop-ups containing information.
        It takes in the view that we're current in and then a string to determine which pop-up we want.
         */
        //final Fragment fragment = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.AppTheme_DialogTheme87);

        final Fragment fragment = this;
        View newView = null; //Null until changed when a specific dialog is chosen
        final TextView name =(TextView) fragment.getView().findViewById(R.id.nameArea);
            if (type == "settings") {
                newView = getLayoutInflater().inflate(R.layout.dialog_options, null);
                final TextView text= newView.findViewById(R.id.textView);
                final ImageButton mål = newView.findViewById(R.id.målButton);
                final ImageButton navn = newView.findViewById(R.id.nameButton);
                final ImageButton data = newView.findViewById(R.id.DataButton);
                builder.setView(newView);
                final AlertDialog dialog = builder.create();
                dialog.show();
                mål.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        DisplayDialog(v, "maal");
                    }
                });
                navn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DisplayDialog(v, "navn");
                        dialog.cancel();
                    }
                });
                data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DisplayDialog(v, "data");
                        dialog.cancel();
                    }
                });
            }

            if (type == "graphs")
            {
                newView = getLayoutInflater().inflate(R.layout.dialog_graph, null);

                builder.setView(newView);
                final AlertDialog dialog = builder.create();
                dialog.show();
                DisplayGraph(newView);
            }
            if (type == "statistics")
            {
                newView = getLayoutInflater().inflate(R.layout.dialog_statistics, null);

                final TextView numberOfWeeksNum = newView.findViewById(R.id.numberOfWeeksNum);
                final TextView hitGoalNum = newView.findViewById(R.id.hitGoalNum);
                final TextView exerciseAllNum = newView.findViewById(R.id.exerciseAllNum);
                final TextView highestExerciseNum = newView.findViewById(R.id.highestExerciseNum);
                final TextView medtakenweekNumber= newView.findViewById(R.id.medtakenweekNumber);
                final TextView medtakenallNum= newView.findViewById(R.id.medtakenallNum);

                numberOfWeeksNum.setText("" + (database.getInt("numberOfWeeksNum")));
                hitGoalNum.setText("" + (database.getInt("hitGoalNum")));
                exerciseAllNum.setText("" + (database.getInt("exerciseAllNum")));
                highestExerciseNum.setText("" + (database.getInt("highestExerciseNum")));
                medtakenweekNumber.setText(""+(database.getInt("medicineWeek")));
                medtakenallNum.setText(""+(database.getInt("medtakenallNum")));

                builder.setView(newView);
                final AlertDialog dialog = builder.create();
                dialog.show();
            }
            if (type == "maal")
            {
                newView = getLayoutInflater().inflate(R.layout.dialog_maal, null);

                final EditText mål = newView.findViewById(R.id.maal);
                final ImageButton ok = newView.findViewById(R.id.okButton);

                mål.setHint("Mindst 150 min per uge");
                builder.setView(newView);

                final AlertDialog dialog = builder.create();
                dialog.show();

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int s = Integer.parseInt(mål.getText().toString());
                        if (s<150){
                            s=database.getInt("maxProgress");
                            }
                        database.setInt(s,"maxProgress");
                        dialog.cancel();
                    }
                });
            }
        if (type == "navn")
        {
                newView = getLayoutInflater().inflate(R.layout.dialog_maal, null);
                final EditText mål = newView.findViewById(R.id.maal);
                final ImageButton ok = newView.findViewById(R.id.okButton);

                mål.setHint("Indtast nyt navn");
                mål.setInputType(InputType.TYPE_CLASS_TEXT);

                builder.setView(newView);
                final AlertDialog dialog = builder.create();
                dialog.show();
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s=(mål.getText().toString());
                        name.setText(s);
                        database.setName(s);

                        //Name(v);
                        dialog.cancel();

                    }
                });
        }
        if (type == "data")
        {
            newView = getLayoutInflater().inflate(R.layout.dialog_data, null);

            final TextView Sure = newView.findViewById(R.id.sure);
            final ImageButton yes = newView.findViewById(R.id.yes);
            final ImageButton no = newView.findViewById(R.id.no);

            builder.setView(newView);
            final AlertDialog dialog = builder.create();
            dialog.show();
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPref.wipe();
                    database.loadData();
                    database.setInt(150,"maxProgress");
                    database.setInt(0,"currentProgress");
                    database.setInt(0,"numberOfWeeksNum");
                    database.setInt(0,"hitGoalNum");
                    database.setInt(0,"exerciseAllNum");
                    database.setInt(0,"highestExerciseNum");
                    name.setText("Navn Navnesen");
                    dialog.cancel();

                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();

                }
            });
        }
        }

    public void Name(View v){

        TextView name =(TextView) v.findViewById(R.id.nameArea);
        name.setText(database.getName());
    }
    private void Latest(View v){

    }

    private void DisplayGraph(View v){
        GraphView graphView = (GraphView) v.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(database.loadDataPoints());
        graphView.addSeries(series);
    }
}
