package com.example.simonlaursen.p2_prototype;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.jjoe64.graphview.series.DataPoint;

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
    private static int MedicinTakenWeek;
    private static int medtakenallNum;

    private static long StartweekDate;
    private static long currentDate;

    private static ExerciseInputs[] exerciseInputs = new ExerciseInputs[3]; //Storage for the data involving the exercise input
    private static InsulinInputs[] insulinInputs = new InsulinInputs[3]; //Storage for the data involving the insulin input

    private String userName="Brugernavn";

    //DEFAULT CONSTRUCTOR
    public Database() {

    }

    public void loadData() {
        currentProgress = SharedPref.readInteger(SharedPref.currentProgress, currentProgress);
        maxProgress = SharedPref.readInteger(SharedPref.MaxProg, maxProgress);
        numberOfWeeksNum = SharedPref.readInteger(SharedPref.numberOfWeeksNum,numberOfWeeksNum);
        hitGoalNum=SharedPref.readInteger(SharedPref.hitGoalNum,hitGoalNum);
        exerciseAllNum=SharedPref.readInteger(SharedPref.exerciseAllNum,exerciseAllNum);
        highestExerciseNum=SharedPref.readInteger(SharedPref.highestExerciseNum,highestExerciseNum);
        userName=SharedPref.readString("name","Brugernavn");
        MedicinTakenWeek=SharedPref.readInteger("MedicinTakenWeek",MedicinTakenWeek);
        medtakenallNum=SharedPref.readInteger("MedTakenAll",medtakenallNum);
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
            SharedPref.writeInteger(SharedPref.MaxProg, maxProgress);
        } else if (name == "currentProgress") {
            currentProgress = value;
            SharedPref.writeInteger(SharedPref.currentProgress, currentProgress);
        } else if(name=="numberOfWeeksNum") {
            numberOfWeeksNum=value;
            SharedPref.writeInteger(SharedPref.numberOfWeeksNum,numberOfWeeksNum);
        } else if (name=="hitGoalNum") {
            hitGoalNum = value;
            SharedPref.writeInteger(SharedPref.hitGoalNum,hitGoalNum);
        } else if (name=="exerciseAllNum") {
            exerciseAllNum=value;
            SharedPref.writeInteger(SharedPref.exerciseAllNum,exerciseAllNum);
        } else if (name=="highestExerciseNum") {
            highestExerciseNum=value;
            SharedPref.writeInteger(SharedPref.highestExerciseNum,highestExerciseNum);
        } else if (name=="medicineWeek") {
            MedicinTakenWeek=value;
            medtakenallNum=value;
            SharedPref.writeInteger(SharedPref.MedicinTakenWeek,MedicinTakenWeek);
            SharedPref.writeInteger(SharedPref.MedTakenAll,medtakenallNum);
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
        else if(name=="medicineWeek") {
            return MedicinTakenWeek;
        }
        else if(name=="medtakenallNum") {
            return medtakenallNum;
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
    public void setName(String navn2){
        userName=navn2;
        SharedPref.writeString("name",navn2);

    }
    public String getName() {return userName;}
    public String getProgressText() {
        return currentProgress + "/" + maxProgress;
    }

    public String getBlankText() {
        return " ";
    }

    public String getMinPerweek() { return "min per uge"; }

    //EXERCISE INPUT DATA
    public void setExerciseInputs(String date, String time, String value){
            if(exerciseInputs[0] != null){
                if(exerciseInputs[1] != null){
                    exerciseInputs[0+2] = exerciseInputs[0+1];
                    exerciseInputs[0+1] = exerciseInputs[0];
                    exerciseInputs[0] = new ExerciseInputs(date,time,value);
                }
                else{
                    exerciseInputs[1] = exerciseInputs[0];
                    exerciseInputs[0] = new ExerciseInputs(date,time,value);
                }
            }
            else{
                exerciseInputs[0] = new ExerciseInputs(date,time,value);
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
            if(insulinInputs[0] != null){
                if(insulinInputs[1] != null){
                    insulinInputs[2] = insulinInputs[1];
                    insulinInputs[1] = insulinInputs[0];
                    insulinInputs[0] = new InsulinInputs(date,time,value);
                }
                else{
                    insulinInputs[1] = insulinInputs[0];
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

    public DataPoint[] loadDataPoints(){
        DataPoint[] dataPoints =  new DataPoint[4];

        //TODO: Change the values based on the prior inputs

        dataPoints[0] = new DataPoint(0,0);
        dataPoints[1] = new DataPoint(0,0);
        dataPoints[2] = new DataPoint(0,0);
        dataPoints[3] = new DataPoint(0,0);


        return dataPoints;
    }

    public void SaveData(){
        SharedPref.writeInteger(SharedPref.MaxProg, maxProgress);
        SharedPref.writeInteger(SharedPref.currentProgress, currentProgress);
        SharedPref.writeInteger(SharedPref.numberOfWeeksNum,numberOfWeeksNum);
        SharedPref.writeInteger(SharedPref.hitGoalNum,hitGoalNum);
        SharedPref.writeInteger(SharedPref.exerciseAllNum,exerciseAllNum);
        SharedPref.writeInteger(SharedPref.highestExerciseNum,highestExerciseNum);
        SharedPref.writeInteger(SharedPref.MedicinTakenWeek,MedicinTakenWeek);
        SharedPref.writeInteger(SharedPref.MedTakenAll,medtakenallNum);
    }
}
