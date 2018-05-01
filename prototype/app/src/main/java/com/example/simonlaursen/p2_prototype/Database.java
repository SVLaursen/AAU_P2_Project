package com.example.simonlaursen.p2_prototype;

import android.view.ViewDebug;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Database {

    private static float fullTime = 0;
    private static int shownTime = 0;
    private static int inputTime = 0;
    private static int currentInsulin = 0;
    private static int maxProgress = 150; //Variable for the max progress on the progressbar
    private static int currentProgress = 0; //Change this value to 0 before releasing app, the current value is for debugging only
    private static LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{

    });

    public Database(){

    }

    public void setValue(int value, String name){
        //TODO:Implement set int value

        if(name == "shownTime"){
            shownTime = value;
        }
        else if(name =="inputTime"){
            inputTime = value;
        }
        else if(name == "currentInsulin"){
            currentInsulin = value;
        }
        else if(name == "maxProgress"){
            maxProgress = value;
        }
        else if(name == "currentProgress"){
            currentProgress = value;
        }
    }

    public void setValue(float value, String name){
        //TODO: Implement float set

        if(name == "fullTime"){
            fullTime += value;
        }
    }

    public int getInt(String name){
        //TODO: Implement a int return

        if(name == "shownTime"){
            return shownTime;
        }
        else if(name == "inputTime"){
            return inputTime;
        }
        else if(name == "currentInsulin"){
            return currentInsulin;
        }
        else if(name == "maxProgress"){
            return maxProgress;
        }
        else if(name == "currentProgress"){
            return currentProgress;
        }
        else{
            return 0;
        }
    }

    public float getFloat(String name){
        //TODO: Implement a float return
        return 0;
    }

    public String getProgressText(){
        return currentProgress + "/" + maxProgress;
    }

    public LineGraphSeries<DataPoint> getPoint(){
        //TODO: Setup a return for this function
        return null;
    }

    public void setPoint(int pointX, int pointY){
        //TODO: Figure out how to set the points from this function
    }

}
