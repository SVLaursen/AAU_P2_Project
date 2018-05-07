package com.example.simonlaursen.p2_prototype;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Database {

    private static float fullTime;
    private static int shownTime;
    private static int inputTime;
    private static int currentInsulin;
    private static int numberOfWeeksNum;
    private static int hitGoalNum;
    private static int exerciseAllNum;
    private static int highestExerciseNum;
    private static int maxProgress; //Variable for the max progress on the progressbar
    private static int currentProgress; //Change this value to 0 before releasing app, the current value is for debugging only
    private static long StartweekDate;
    private static long currentDate;
    private static ExerciseInputs[] exerciseInputs = new ExerciseInputs[3]; //Storage for the data involving the exercise input
    private static InsulinInputs[] insulinInputs = new InsulinInputs[3]; //Storage for the data involving the insulin input

    //DEFAULT CONSTRUCTOR
    public Database() {

    }

    public void loadData() {

     currentProgress = SharedPref.readInteger(SharedPref.currentProgress, 0);
       maxProgress = SharedPref.readInteger(SharedPref.MaxProg, 150);
     numberOfWeeksNum = SharedPref.readInteger(SharedPref.numberOfWeeksNum,0);
        hitGoalNum=SharedPref.readInteger(SharedPref.hitGoalNum,0);
        exerciseAllNum=SharedPref.readInteger(SharedPref.exerciseAllNum,0);
       highestExerciseNum=SharedPref.readInteger(SharedPref.highestExerciseNum,0);
    }

    public void setLong(long value, String name) {
        if (name == "StartDate") {
            StartweekDate = value;
        } else if (name == "currentDate") {
            currentDate = value;
        }
    }
    public long getLong(String name){
        if (name=="StartDate"){
           return StartweekDate;
        } else if (name=="currentDate"){
            return currentDate;
        } else{
            return 0;
        }
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
            SharedPref.writeInteger(SharedPref.MaxProg, value);
        } else if (name == "currentProgress") {
            currentProgress = value;
            SharedPref.writeInteger(SharedPref.currentProgress, value);
        } else if(name=="numberOfWeeksNum") {
            numberOfWeeksNum=value;
            SharedPref.writeInteger(SharedPref.numberOfWeeksNum,value);
        } else if (name=="hitGoalNum") {
            hitGoalNum=value;
            SharedPref.writeInteger(SharedPref.hitGoalNum,value);
        } else if (name=="exerciseAllNum") {
            exerciseAllNum=value;
            SharedPref.writeInteger(SharedPref.exerciseAllNum,value);
        } else if (name=="highestExerciseNum") {
            highestExerciseNum=value;
            SharedPref.writeInteger(SharedPref.highestExerciseNum,value);
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
        }
        else if (name == "inputTime") {
            return inputTime;
        }
        else if (name == "currentInsulin") {
            return currentInsulin;
        }
        else if (name == "maxProgress") {
            return maxProgress;
        }
        else if (name == "currentProgress") {
            return currentProgress;
        }
        else if(name=="numberOfWeeksNum") {
            return numberOfWeeksNum;
        }
        else if (name=="hitGoalNum") {
            return hitGoalNum;
        }
        else if (name == "exerciseAllNum") {
            return exerciseAllNum;
        }
        else if (name=="highestExerciseNum") {
            return highestExerciseNum;
        }
        else {
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

    public String getMinPerweek() { return "min per uge"; }

    //EXERCISE INPUT DATA
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

    //INSULIN INPUT DATA
    public void setInsulinInputs(String date, String time, String value){
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
