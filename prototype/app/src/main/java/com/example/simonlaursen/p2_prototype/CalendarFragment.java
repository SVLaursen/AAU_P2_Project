package com.example.simonlaursen.p2_prototype;


import android.app.AlertDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Calendar;

public class CalendarFragment extends Fragment {

    public CalendarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarSetup(v);

        return v;
    }

    public void calendarSetup(final View v){
        final MaterialCalendarView materialCalendarView = v.findViewById(R.id.calendarView);
        final View view = v;


        /* Instantiates the calendar and sets first day of week to monday,
        *  Sets the calendar to go from january 1st 1900 to december 31st 2100.
        *  Calendar mode is used to set the display mode to months. */
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(1900, 1, 1))
                .setMaximumDate(CalendarDay.from(2100, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.setTopbarVisible(true); //Used to enable/disable the top bar, which contains the name of the months and arrows.
        materialCalendarView.setWeekDayLabels(new String[] {"SØN", "MAN", "TIR","ONS","TOR","FRE","LØR"}); //Sets the labels for the weekdays.



        //sets the selected day to the current day, when entering the calendar
        final Calendar calendar = Calendar.getInstance();
        materialCalendarView.setDateSelected(calendar.getTime(), true);



        //Used to find the current day and decorate it
        materialCalendarView.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                Calendar cal1 = day.getCalendar();
                Calendar cal2 = Calendar.getInstance();

                return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
                        && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                        && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));

            }

            @Override
            public void decorate(DayViewFacade view) {
                    view.addSpan(new ForegroundColorSpan(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorDarkBlue))); //sets the color
                    view.addSpan(new StyleSpan(Typeface.BOLD)); //sets the current day to a BOLD text style
                    //view.addSpan(new RelativeSizeSpan(1.25f)); //sets the font size to be a little bigger than the other days

                    /* Adds a dot on the current day.
                     * For now, only used to test it out.
                     * But a decorator with a DotSpan is probably needed if we figure out the events thing. */
                    view.addSpan(new DotSpan(8, R.color.colorDarkBlue));
            }
        });


        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                /* Creates a toast that shows what date was clicked on in DD/MM/YYYY
                *  Was used for testing. */
                //Toast.makeText(getActivity().getApplicationContext(), ""+ date.getDay() + " / " + date.getMonth() + " / " + date.getYear(),Toast.LENGTH_SHORT).show();

                //Creates and alertDialog pop-up for that day
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("" + date.getDay() + " / " + date.getMonth() + " / " + date.getYear()); //Sets the title of the dialog to the clicked days date
                builder.setMessage("Message content");
                builder.setCancelable(true);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });


    }

}
