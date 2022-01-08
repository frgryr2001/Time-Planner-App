package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.Activity.AddEventActivity;
import com.example.myapplication.Activity.AddReminderActivity;
import com.example.myapplication.Activity.AddScheduleActivity;
import com.example.myapplication.Activity.MisstionNewActivity;
import com.example.myapplication.Adapter.ScheduleBubbleAdapter;
import com.example.myapplication.Object.ScheduleClass;
import com.example.myapplication.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageButton ibMoveToAddShedulePage,ibMoveToAddReminderPage,ibMoveToAddMissionPage, ibMoveToAddEventPage;
    private ProgressBar pbTest;
    private View view;
    private int progress = 0;
    private CountDownTimer myCountDownTimer;
    private boolean mTimerRunning;
    private static final long START_TIME_IN_MILLIS = 600000;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private RecyclerView rvListSchedule;
    private ScheduleBubbleAdapter scheduleBubbleAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ibMoveToAddReminderPage = view.findViewById(R.id.ibMoveToAddReminderPage);
        ibMoveToAddShedulePage = view.findViewById(R.id.ibMoveToAddShedulePage);
        ibMoveToAddMissionPage = view.findViewById(R.id.ibMoveToAddMissionPage);
        ibMoveToAddEventPage = view.findViewById(R.id.ibMoveToAddEventPage);
        pbTest = view.findViewById(R.id.pbScheduleBallonItem);

        ibMoveToAddReminderPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(), AddReminderActivity.class);
                startActivity(i);
            }
        });

        ibMoveToAddShedulePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(), AddScheduleActivity.class);
                startActivity(i);
            }
        });

        ibMoveToAddMissionPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(), MisstionNewActivity.class);
                startActivity(i);
            }
        });

        ibMoveToAddEventPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(), AddEventActivity.class);
                startActivity(i);
            }
        });

//        myCountDownTimer = new CountDownTimer(50000, 1000) {
//            public void onTick(long millisUntilFinished) {
//                // Used for formatting digit to be in 2 digits only
////                NumberFormat f = new DecimalFormat("00");
////                long hour = (millisUntilFinished / 3600000) % 24;
////                long min = (millisUntilFinished / 60000) % 60;
////                long sec = (millisUntilFinished / 1000) % 60;
////                pbTest.setProgress((50 - (int) sec) * 2);
////                        textView.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
//
//
//            }
//            // When the task is over it will print 00:00:00 there
//            public void onFinish() {
////                        textView.setText("00:00:00");
//                Toast.makeText(view.getContext(), "Finished", Toast.LENGTH_SHORT).show();
//            }
//        };

//        pbTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mTimerRunning) {
//                    Toast.makeText(view.getContext(), "Pause", Toast.LENGTH_SHORT).show();
//                    pauseTimer();
//                } else {
//                    Toast.makeText(view.getContext(), "Start", Toast.LENGTH_SHORT).show();
//                    startTimer();
//                }
//            }
//        });

        rvListSchedule = view.findViewById(R.id.rvListSchedule);
        ArrayList<ScheduleClass> list = ScheduleClass.init();

        rvListSchedule.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        rvListSchedule.setHasFixedSize(true);

        scheduleBubbleAdapter = new ScheduleBubbleAdapter(view.getContext(), list);
        rvListSchedule.setAdapter(scheduleBubbleAdapter);

        return view;
    }

    private void startTimer() {
        myCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
//                updateCountDownText();
                updateProgressBar();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
//                mButtonStartPause.setText("Start");
//                mButtonStartPause.setVisibility(View.INVISIBLE);
//                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
//        mButtonStartPause.setText("pause");
//        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void updateProgressBar() {
        long sec = (mTimeLeftInMillis / 1000) % 60;
        pbTest.setProgress((60 - (int) sec) * 100 / 60);

    }

    private void pauseTimer() {
        myCountDownTimer.cancel();
        mTimerRunning = false;
//        mButtonStartPause.setText("Start");
//        mButtonReset.setVisibility(View.VISIBLE);
    }

}