package com.example.simonlaursen.p2_prototype;

public class Database {

    private static float fullTime;

    private static int shownTime;
    private static int inputTime;
    private static int currentInsulin;
    private static int maxProgress = 150; //Variable for the max progress on the progressbar
    private static int currentProgress=0; //Change this value to 0 before releasing app, the current value is for debugging only

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
    public void setValue2(float value, String name){
            //TODO: Implement float set

            if (name == "fullTime") {

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


}

