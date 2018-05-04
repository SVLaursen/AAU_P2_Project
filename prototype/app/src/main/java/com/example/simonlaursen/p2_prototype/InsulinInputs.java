package com.example.simonlaursen.p2_prototype;

import java.text.SimpleDateFormat;

public class InsulinInputs {

    public String date;
    public String time;
    public String value;

    public InsulinInputs(){

    }

    public InsulinInputs(String date, String time, String value){
        this.date = date;
        this.time = time;
        this.value = value;
    }
}
