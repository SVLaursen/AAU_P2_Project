package com.example.simonlaursen.p2_prototype;

import java.text.SimpleDateFormat;

public class Database {

    private static float fullTime;

    private static int shownTime;
    private static int inputTime;
    private static int currentInsulin;
    private static int maxProgress = 150; //Variable for the max progress on the progressbar
    private static int currentProgress=0; //Change this value to 0 before releasing app, the current value is for debugging only

    private static ExerciseInputs[] exerciseInputs = new ExerciseInputs[3];
    private static InsulinInputs[] insulinInputs = new InsulinInputs[3];

    //DEFAULT CONSTRUCTOR
    public Database(){
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

    public void setExerciseInputs(SimpleDateFormat date, SimpleDateFormat time, int value){
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

    public SimpleDateFormat getExerciseDate(int num){
        return exerciseInputs[num].date;
    }

    public SimpleDateFormat getExerciseTime(int num){
        return exerciseInputs[num].time;
    }

    public int getExerciseValue(int num){
        return exerciseInputs[num].value;
    }

    public void setInsulinInputs(SimpleDateFormat date, SimpleDateFormat time, int value){
        for(int i = 0; i < 3; i++){
            if(insulinInputs[i] != null){
                if(insulinInputs[i + 1] != null){
                    insulinInputs[i+2] = insulinInputs[i+1];
                    insulinInputs[i+1] = insulinInputs[i];
                    insulinInputs[i] = new InsulinInputs(date,time,value);
                }
                else{
                    insulinInputs[i+1] = insulinInputs[i];
                    insulinInputs[i] = new InsulinInputs(date,time,value);
                }
            }
            else{
                insulinInputs[i] = new InsulinInputs(date,time,value);
            }
        }
    }

    public SimpleDateFormat getInsulinDate(int num){
        return insulinInputs[num].date;
    }

    public SimpleDateFormat getInsulinTime(int num){
        return insulinInputs[num].time;
    }

    public int getInsulinValue(int num){
        return insulinInputs[num].value;
    }
}
