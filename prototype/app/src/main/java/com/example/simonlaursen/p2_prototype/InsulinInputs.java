package com.example.simonlaursen.p2_prototype;

import java.text.SimpleDateFormat;

public class InsulinInputs {

    public SimpleDateFormat date;
    public SimpleDateFormat time;
    public int value;

    public InsulinInputs(){

    }

    public InsulinInputs(SimpleDateFormat date, SimpleDateFormat time, int value){
        this.date = date;
        this.time = time;
        this.value = value;
    }
}
