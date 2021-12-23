package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.Adapter.HourEventAdapter;
import com.example.myapplication.Object.CalendarUtils;
import com.example.myapplication.Object.Event;
import com.example.myapplication.Object.HourEvent;
import com.example.myapplication.R;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DailyScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DailyScheduleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView monthDayText;
    private TextView dayOfWeekTV;
    private ListView hourListView;
    private View view;
    private Button btnDailyBack, btnDailyNext, btnAddNewDailyEvent;

    public DailyScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DailyScheduleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DailyScheduleFragment newInstance(String param1, String param2) {
        DailyScheduleFragment fragment = new DailyScheduleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_daily_schedule, container, false);
        initWidgets();
//        CalendarUtils.selectedDate = LocalDate.now();
        setDayView();

        // Nhấn mũi tên Back
        btnDailyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousDayAction(v);
            }
        });

        // Nhấn mũi tên Next
        btnDailyNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextDayAction(v);
            }
        });

        //Nhấn nút thêm lịch trình
        btnAddNewDailyEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    private void initWidgets()
    {
        monthDayText = view.findViewById(R.id.monthDayText);
        dayOfWeekTV = view.findViewById(R.id.dayOfWeekTV);
        hourListView = view.findViewById(R.id.hourListView);
        btnDailyBack = view.findViewById(R.id.btnDailyBack);
        btnDailyNext = view.findViewById(R.id.btnDailyNext);
        btnAddNewDailyEvent = view.findViewById(R.id.btnAddNewDailyEvent);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        setDayView();
    }

    private void setDayView()
    {
        monthDayText.setText(CalendarUtils.monthDayYearFromDate(CalendarUtils.selectedDate));
        String dayOfWeek = CalendarUtils.selectedDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
        dayOfWeekTV.setText(dayOfWeek);
        setHourAdapter();
    }

    private void setHourAdapter()
    {
        HourEventAdapter hourEventAdapter = new HourEventAdapter(view.getContext(), hourEventList());
        hourListView.setAdapter(hourEventAdapter);
    }

    private ArrayList<HourEvent> hourEventList()
    {
        ArrayList<HourEvent> list = new ArrayList<>();

        for(int hour = 0; hour < 24; hour++)
        {
            LocalTime time = LocalTime.of(hour, 0);
            ArrayList<Event> events = Event.eventsForDateAndTime(CalendarUtils.selectedDate, time);
            HourEvent hourEvent = new HourEvent(time, events);
            list.add(hourEvent);
        }

        return list;
    }

    public void previousDayAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusDays(1);
        setDayView();
    }

    public void nextDayAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusDays(1);
        setDayView();
    }
}