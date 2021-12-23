package com.example.myapplication.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Object.CalendarUtils;
import com.example.myapplication.R;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder>
{
    private final ArrayList<LocalDate> days;
    private final OnItemListener onItemListener;

    public CalendarAdapter(ArrayList<LocalDate> days, OnItemListener onItemListener)
    {
        this.days = days;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.day_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if(days.size() > 15) //month view
            layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        else // week view
            layoutParams.height = (int) parent.getHeight();

        return new ViewHolder(view, onItemListener, days);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final LocalDate date = days.get(position);

        holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()));

        if(date.equals(CalendarUtils.selectedDate))
            holder.parentView.setBackgroundColor(Color.LTGRAY);

        if(date.getMonth().equals(CalendarUtils.selectedDate.getMonth()))
            holder.dayOfMonth.setTextColor(Color.BLACK);
        else
            holder.dayOfMonth.setTextColor(Color.LTGRAY);
    }

    @Override
    public int getItemCount()
    {
        return days.size();
    }

    public interface  OnItemListener
    {
        void onItemClick(int position, LocalDate date);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ArrayList<LocalDate> days;
        public final View parentView;
        public final TextView dayOfMonth;
        private final CalendarAdapter.OnItemListener onItemListener;

        public ViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener, ArrayList<LocalDate> days) {
            super(itemView);
            parentView = itemView.findViewById(R.id.parentView);
            dayOfMonth = itemView.findViewById(R.id.cellDayText);
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
            this.days = days;
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition(), days.get(getAdapterPosition()));
        }
    }
}
