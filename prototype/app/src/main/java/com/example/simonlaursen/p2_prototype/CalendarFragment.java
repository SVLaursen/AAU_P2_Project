package com.example.simonlaursen.p2_prototype;


import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Calendar;
import java.util.HashSet;

public class CalendarFragment extends Fragment {

    public CalendarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        final MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.timerActive = false;

        calendarSetup(v);

        return v;
    }

    public void calendarSetup(final View v){
        final MaterialCalendarView materialCalendarView = v.findViewById(R.id.calendarView);
        final Fragment current = this;

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
        materialCalendarView.setDateSelected(Calendar.getInstance().getTime(), true);

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
                view.addSpan(new ForegroundColorSpan(ContextCompat.getColor(current.getContext(), R.color.colorDarkBlue))); //sets the color
                view.addSpan(new StyleSpan(Typeface.BOLD)); //sets the current day to a BOLD text style
                //view.addSpan(new RelativeSizeSpan(1.25f)); //sets the font size to be a little bigger than the other days
            }
        });

        //COLLECTION OF DATES
        final HashSet<CalendarDay> dates = new HashSet<>();
        dates.add(CalendarDay.from(2018,4,20));
        dates.add(CalendarDay.from(2018,4,23));

        //ADD DOT ON EVENT DAYS
        materialCalendarView.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                return (dates.contains(day));
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new DotSpan(8,R.color.colorYellow));
            }
        });

        //ON CLICK LISTENER
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                /* Creates a toast that shows what date was clicked on in DD/MM/YYYY
                 *  Was used for testing. */
                //Toast.makeText(getActivity().getApplicationContext(), ""+ date.getDay() + " / " + date.getMonth() + " / " + date.getYear(), Toast.LENGTH_SHORT).show();

                int monthNum = date.getMonth() + 1;

                //Creates and alertDialog pop-up for that day
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.AppTheme_DialogTheme);
                View newView = getLayoutInflater().inflate(R.layout.dialog_event_popup, null);

                //Setup for the objects within the dialog popup
                TextView dateText = newView.findViewById(R.id.dateText);
                TextView eventName = newView.findViewById(R.id.EventName);
                TextView hostName = newView.findViewById(R.id.HostName);

                ImageButton joinButton = newView.findViewById(R.id.JoinButton);
                ImageButton cancelButton = newView.findViewById(R.id.CancelButton);

                /*
                 * Here we are doing the wizard of oz technique to setup fake events.
                 * These events could be setup with a online database, but for now we're gonna have
                 * to settle with the fake contraption.
                 */

                if(date.getDay() == 20 && date.getMonth() == 4)
                {
                    dateText.setText("" + date.getDay() + " / " + monthNum + " / " + date.getYear());
                    eventName.setText("Søndags Walk");
                    hostName.setText("Diabetes Foreningen");

                    builder.setView(newView);
                    final AlertDialog dialog = builder.create();
                    ButtonSetup(joinButton,cancelButton,dialog);
                    dialog.show();
                }
                else if(date.getDay() == 23 && date.getMonth() == 4)
                {
                    dateText.setText("" + date.getDay() + " / " + monthNum + " / " + date.getYear());
                    eventName.setText("Fællestræning");
                    hostName.setText("Hjerte Foreningen");

                    builder.setView(newView);
                    final AlertDialog dialog = builder.create();
                    ButtonSetup(joinButton,cancelButton,dialog);
                    dialog.show();
                }
            }
        });


    }

    private void ButtonSetup(ImageButton joinButton, ImageButton cancelButton, AlertDialog dialog){

        final AlertDialog _dialog = dialog;

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //JOIN THE EVENT
                Toast.makeText(getActivity().getApplicationContext(),"Du er nu tilmeldt",Toast.LENGTH_SHORT).show();
                _dialog.cancel();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LEAVE EVENT
                Toast.makeText(getActivity().getApplicationContext(),"Du er nu meldt fra",Toast.LENGTH_SHORT).show();
                _dialog.cancel();
            }
        });
    }

}
