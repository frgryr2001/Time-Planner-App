package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
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

//    Constructor
    public ScheduleBubbleAdapter(Context context, ArrayList<ScheduleClass> laptops) {
        this.listSchedules = laptops;
        this.context = context;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleClass schedule = listSchedules.get(position);
        if (schedule == null) {
            return;
        }
        holder.tvScheduleBallonItemName.setText(schedule.getName());
        String timeStart = schedule.getTimeStart();
        String timeEnd = schedule.getTimeEnd();

        String[] timeStartArr = timeStart.split(":");
        String[] timeEndArr = timeEnd.split(":");

        int timeStartMinutes = Integer.parseInt(timeStartArr[0]) * 60 + Integer.parseInt(timeStartArr[1]);
        int timeEndMinutes = Integer.parseInt(timeEndArr[0]) * 60 + Integer.parseInt(timeEndArr[1]);

        holder.tvScheduleBallonItemTime.setText(String.valueOf(timeEndMinutes - timeStartMinutes));

        holder.scheduleItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, AddEventActivity.class);
                context.startActivity(intent);
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
