package com.example.simonlaursen.p2_prototype;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;
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

    public void calendarSetup(View v){
        MaterialCalendarView materialCalendarView = (MaterialCalendarView) v.findViewById(R.id.calendarView);
        //WeekDayFormatter weekDayFormatter = (WeekDayFormatter) v.findViewById(R.id.calendarView);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(1900, 1, 1))
                .setMaximumDate(CalendarDay.from(2100, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.setTopbarVisible(true);
        materialCalendarView.getSelectedDate();

        Calendar calendar = Calendar.getInstance();
        materialCalendarView.setDateSelected(calendar.getTime(), true);

    }

}
