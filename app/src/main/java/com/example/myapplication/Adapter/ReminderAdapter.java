package com.example.myapplication.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Object.ReminderClass;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder> {
    private ArrayList<ReminderClass> list;
    private Context context;

    public ReminderAdapter(ArrayList<ReminderClass> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_row, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReminderClass r = list.get(position);
        if (r == null) {
            return;
        }

        holder.tvReminderRowTime.setText(r.getTime().toString());
        holder.tvReminderRowName.setText(r.getName().toString());
        holder.tvReminderRowDate.setText(r.getDate().toString());

        // set onclick item
        holder.reminderRowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvReminderRowTime, tvReminderRowName, tvReminderRowDate;
        LinearLayout reminderRowLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReminderRowTime = itemView.findViewById(R.id.tvReminderRowTime);
            tvReminderRowName = itemView.findViewById(R.id.tvReminderRowName);
            tvReminderRowDate = itemView.findViewById(R.id.tvReminderRowDate);
            reminderRowLayout = itemView.findViewById(R.id.reminderRowLayout);
        }
    }
}
