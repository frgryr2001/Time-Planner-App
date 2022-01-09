package com.example.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.AddEventActivity;
import com.example.myapplication.Object.ScheduleClass;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ScheduleBubbleAdapter extends RecyclerView.Adapter<ScheduleBubbleAdapter.ViewHolder> {
    private ArrayList<ScheduleClass> listSchedules;
    private Context context;
    private ArrayList<CountDownTimer> listCountDownTimer = new ArrayList<CountDownTimer>();
    private ArrayList<Long> listStartTime = new ArrayList<Long>();
    private ArrayList<Long> listTimeLeft = new ArrayList<Long>();
    private ArrayList<Integer> listProgress = new ArrayList<Integer>();
    private ArrayList<Boolean> listIsRunning = new ArrayList<Boolean>();
    private int mPositionInit;
    private int mPositionEvent;

//    Constructor
    public ScheduleBubbleAdapter(Context context, ArrayList<ScheduleClass> schedules) {
        this.listSchedules = schedules;
        this.context = context;
        for(int i = 0; i < schedules.size(); i++) {
            mPositionInit = i;

            String timeStart = schedules.get(i).getTimeStart();
            String timeEnd = schedules.get(i).getTimeEnd();

            String[] timeStartArr = timeStart.split(":");
            String[] timeEndArr = timeEnd.split(":");

            long timeStartMinutes = Integer.parseInt(timeStartArr[0]) * 60 + Integer.parseInt(timeStartArr[1]);
            long timeEndMinutes = Integer.parseInt(timeEndArr[0]) * 60 + Integer.parseInt(timeEndArr[1]);

            listStartTime.add((timeEndMinutes - timeStartMinutes) * 1000);
            listTimeLeft.add((timeEndMinutes - timeStartMinutes) * 1000);
            listProgress.add(0);
            listIsRunning.add(false);

            CountDownTimer countDownTimer = new CountDownTimer(1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {

                }
            };

            listCountDownTimer.add(countDownTimer);
        }
    }

//    Override methods
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_bubble_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ScheduleClass schedule = listSchedules.get(position);
        if (schedule == null) {
            return;
        }
        holder.tvScheduleBallonItemName.setText(schedule.getName());
        holder.tvScheduleBallonItemTime.setText(String.valueOf(listStartTime.get(position)/1000));

        holder.scheduleItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Test Position Click: ", position + "");
                Log.i("Test Status: ", listIsRunning.get(position) + "");

                if (listIsRunning.get(position)) {
                    listCountDownTimer.get(position).cancel();
                    listIsRunning.set(position, false);
                    Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show();
                } else {
                    CountDownTimer countDownTimer = new CountDownTimer(listTimeLeft.get(position), 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            Log.i("Test Position Tick: ", position + "");
                            listTimeLeft.set(position, millisUntilFinished);

                            long sec = (listStartTime.get(mPositionInit) - listTimeLeft.get(position));
                            int p = (int) (sec * 100 / listStartTime.get(position));
                            listProgress.set(position, p);
                            Log.i("Test Time Left:" , listTimeLeft.get(position) + "");
                            Log.i("Test Progress:" , listProgress.get(position) + "");

                            holder.pbScheduleBallonItem.setProgress(listProgress.get(position));
                            holder.tvScheduleBallonItemTime.setText(String.valueOf(listTimeLeft.get(position)/1000));
                        }

                        @Override
                        public void onFinish() {
                            Toast.makeText(context, "Finished", Toast.LENGTH_SHORT).show();
                            listIsRunning.set(position, false);
                            holder.pbScheduleBallonItem.setProgress(100);
//                            listProgress.set(position, 0);
                        }
                    };

                    listCountDownTimer.set(position, countDownTimer);
                    listCountDownTimer.get(position).start();
                    listIsRunning.set(position, true);
                    Toast.makeText(context, "Started", Toast.LENGTH_SHORT).show();
//                    Log.i("Progress Click: ", listProgress.get(position) + "");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listSchedules != null) {
            return listSchedules.size();
        }
        return 0;
    }

    //    Class ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        ProgressBar pbScheduleBallonItem;
        TextView tvScheduleBallonItemName, tvScheduleBallonItemTime;
        RelativeLayout scheduleItemLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pbScheduleBallonItem = itemView.findViewById(R.id.pbScheduleBallonItem);
            tvScheduleBallonItemName = itemView.findViewById(R.id.tvScheduleBallonItemName);
            tvScheduleBallonItemTime = itemView.findViewById(R.id.tvScheduleBallonItemTime);
            scheduleItemLayout = itemView.findViewById(R.id.scheduleItemLayout);
        }
    }

}
