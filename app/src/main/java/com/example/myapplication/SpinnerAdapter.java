package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<ReminderType> {
    LayoutInflater layoutInflater;
    ImageView ivReminderType;

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<ReminderType> users)
    {
        super(context, resource, users);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View rowView = layoutInflater.inflate(R.layout.spinner_value, null,true);
        ReminderType type = getItem(position);
        TextView textView = (TextView)rowView.findViewById(R.id.tvSpinner);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.ivSpinner);
        textView.setText(type.getName());
        imageView.setImageResource(type.getIcon());
        return rowView;
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        if(convertView == null)
            convertView = layoutInflater.inflate(R.layout.spinner_value, parent,false);

        ReminderType type = getItem(position);
        TextView textView = (TextView)convertView.findViewById(R.id.tvSpinner);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.ivSpinner);
        textView.setText(type.getName());
        imageView.setImageResource(type.getIcon());
        return convertView;
    }
}
