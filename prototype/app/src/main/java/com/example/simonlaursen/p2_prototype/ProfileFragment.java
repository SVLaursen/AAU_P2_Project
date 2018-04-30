package com.example.simonlaursen.p2_prototype;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        InputButtons(v); //activates the input buttons

        return v;
    }

    private void InputButtons(View v){

    }

    private void DisplayDialog(View v){

    }

    private void ExerciseGraph(){

    }

    private void InsulinGraph(){

    }

}
