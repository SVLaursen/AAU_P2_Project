package com.example.simonlaursen.p2_prototype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    /*
    Okay, so this is the main activity, which will run the whole thing!
    The program itself, such as the home screen, calendar screen, etc. will all be fragments loaded up inside this.
    The reason why we do this is because it'll be much easier to do hotfixes along the way, as well as accessing the data we need.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

}
