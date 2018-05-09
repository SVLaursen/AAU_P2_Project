package com.example.simonlaursen.p2_prototype;

import com.jjoe64.graphview.series.DataPoint;

import java.util.HashSet;

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
    private static int time;

    private static int day1;
    private static int day2;
    private static int day3;
    private static int day4;
    private static int day5;
    private static int day6;
    private static int day7;



    private static ExerciseInputs[] exerciseInputs = new ExerciseInputs[3]; //Storage for the data involving the exercise input
    private static InsulinInputs[] insulinInputs = new InsulinInputs[3]; //Storage for the data involving the insulin input


    private String userName="Brugernavn";


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
        userName=SharedPref.readString("name","Aida Guerra");
        MedicinTakenWeek=SharedPref.readInteger("MedicinTakenWeek",0);
        medtakenallNum=SharedPref.readInteger("MedTakenAll",0);
        day1=SharedPref.readInteger("day1",0);
        day2=SharedPref.readInteger("day2",0);
        day3=SharedPref.readInteger("day3",0);
        day4=SharedPref.readInteger("day4",0);
        day5=SharedPref.readInteger("day5",0);
        day6=SharedPref.readInteger("day6",0);
        day7=SharedPref.readInteger("day7",0);


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

            medtakenallNum+=value;
        SharedPref.writeInteger(SharedPref.MedicinTakenWeek,value);
        SharedPref.writeInteger(SharedPref.MedTakenAll,medtakenallNum);

    }
        else if (name == "day1") {
             day1=value;
            SharedPref.writeInteger(SharedPref.day1,value);
        }
        else if (name == "day2") {
             day2=value;
            SharedPref.writeInteger(SharedPref.day2,value);
        }
        else if (name == "day3") {
             day3=value;
            SharedPref.writeInteger(SharedPref.day3,value);
        }
        else if (name == "day4") {
             day4=value;
            SharedPref.writeInteger(SharedPref.day4,value);
        }
        else if (name == "day5") {
             day5=value;
            SharedPref.writeInteger(SharedPref.day5,value);
        }
        else if (name == "day6") {
             day6=value;
            SharedPref.writeInteger(SharedPref.day6,value);
        }
        else if (name == "day7") {
             day7=value;
            SharedPref.writeInteger(SharedPref.day7,value);

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
        else if (name == "time") {
            return time;
        }
        else if (name == "day1") {
            return day1;
        }
        else if (name == "day2") {
            return day2;
        }
        else if (name == "day3") {
            return day3;
        }
        else if (name == "day4") {
            return day4;
        }
        else if (name == "day5") {
            return day5;
        }
        else if (name == "day6") {
            return day6;
        }
        else if (name == "day7") {
            return day7;
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
        DataPoint[] dataPoints =  new DataPoint[8];

        dataPoints[0] = new DataPoint(0,0);
        dataPoints[1] = new DataPoint(1.0,day1);
        dataPoints[2] = new DataPoint(2.0,day2);
        dataPoints[3] = new DataPoint(3.0,day3);
        dataPoints[4] = new DataPoint(4.0,day4);
        dataPoints[5] = new DataPoint(5.0,day5);
        dataPoints[6] = new DataPoint(6.0,day6);
        dataPoints[7] = new DataPoint(7.0,day7);

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
