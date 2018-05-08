package com.example.simonlaursen.p2_prototype;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref
{
    private static SharedPreferences mSharedPref;
    public static final String currentProgress = "currentProgress";
    public static final String MaxProg = "MaxProg";
    public static final String newWeek= "newWeek";
    public static final String numberOfWeeksNum="numberOfWeeksNum";
    public static final String hitGoalNum="hitGoalNum";
    public static final String exerciseAllNum="exerciseAllNum";
    public static final String highestExerciseNum="highestExerciseNum";
    public static final String Name="name";
    public static final String MedicinTakenWeek="MedicinTakenWeek";
    public static final String MedTakenAll="MedTakenAll";
    public SharedPref()
    {

    }

    public static void init(Context context)
    {
        if(mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }
    public static void wipe() {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.clear();
        prefsEditor.commit();
    }
    public static String readString(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void writeString(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public static boolean readBoolean(String key, boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);
    }

    public static void writeBoolean(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public static Integer readInteger(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    public static void writeInteger(String key, Integer value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putInt(key, value).commit();
    }

   public static void writeLong(String key, long value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putLong(key, value);
        prefsEditor.commit();
    }

    public static Long readLong(String key, long defValue) {
        return mSharedPref.getLong(key, defValue);
    }
}