package com.example.simonlaursen.p2_prototype;

import java.text.SimpleDateFormat;

public class Database {

    private static float fullTime;

    private static int shownTime;
    private static int inputTime;
    private static int currentInsulin;
    private static int maxProgress = 150; //Variable for the max progress on the progressbar
    private static int currentProgress=0; //Change this value to 0 before releasing app, the current value is for debugging only

    private static ExerciseInputs[] exerciseInputs;
    private static InsulinInputs[] insulinInputs;

    //DEFAULT CONSTRUCTOR
    public Database(){
        exerciseInputs = new ExerciseInputs[3];
        insulinInputs = new InsulinInputs[3];
    }

    public void setInt(int value, String name){
        if (name == "shownTime") {
            shownTime = value;
        } else if (name == "inputTime") {
            inputTime = value;
        } else if (name == "currentInsulin") {
            currentInsulin = value;
        } else if (name == "maxProgress") {
            maxProgress = value;
        } else if (name == "currentProgress") {
            currentProgress = value;
        }
    }

    public void setFloat(float value, String name){
        if(name == "fullTime"){
            fullTime += value;
        }
    }


    public int getInt(String name){

        if (name == "shownTime") {
            return shownTime;
        } else if (name == "inputTime") {
            return inputTime;
        } else if (name == "currentInsulin") {
            return currentInsulin;
        } else if (name == "maxProgress") {
            return maxProgress;
        } else if (name == "currentProgress") {
            return currentProgress;
        } else {
            return 0;
        }
    }

    public float getFloat(String name){
        if(name == "fullTime"){
            return fullTime;
        }
        else {
            return 0;
        }

    }

    public String getProgressText() {
        return currentProgress + "/" + maxProgress;
    }


    public String getBlankText() {
        return " ";
    }

    public String getMinPerweek() {
            return "min per uge";
        }

    public void setExerciseInputs(String date, String time, String value){
        for(int i = 0; i < 3; i++){
            if(exerciseInputs[i] != null){
                if(exerciseInputs[i + 1] != null){
                    exerciseInputs[i+2] = exerciseInputs[i+1];
                    exerciseInputs[i+1] = exerciseInputs[i];
                    exerciseInputs[i] = new ExerciseInputs(date,time,value);
                }
                else{
                    exerciseInputs[i+1] = exerciseInputs[i];
                    exerciseInputs[i] = new ExerciseInputs(date,time,value);
                }
            }
            else{
                exerciseInputs[i] = new ExerciseInputs(date,time,value);
            }
        }
    }

    public String getExerciseDate(int num){
        return exerciseInputs[num].date;
    }

    public String getExerciseTime(int num){
        return exerciseInputs[num].time;
    }

    public String getExerciseValue(int num){
        return exerciseInputs[num].value;
    }

    public void setInsulinInputs(String date, String time, String value){

        if(insulinInputs[0] != null){
            if(insulinInputs[0 + 1] != null){
                insulinInputs[0+2] = insulinInputs[0+1];
                insulinInputs[0+1] = insulinInputs[0];
                insulinInputs[0] = new InsulinInputs(date,time,value);
            }
            else{
                insulinInputs[0+1] = insulinInputs[0];
                insulinInputs[0] = new InsulinInputs(date,time,value);
            }
        }
        else{
            insulinInputs[0] = new InsulinInputs(date,time,value);
        }
    }

    public String getInsulinDate(int num){
        return insulinInputs[num].date;
    }

    public String getInsulinTime(int num){
        return insulinInputs[num].time;
    }

    public String getInsulinValue(int num){
        return insulinInputs[num].value;
    }
}
