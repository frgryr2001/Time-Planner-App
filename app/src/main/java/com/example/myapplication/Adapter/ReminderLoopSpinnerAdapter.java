package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Object.ReminderLoopClass;
import com.example.myapplication.Object.ReminderTypeClass;
import com.example.myapplication.R;

import java.util.List;

public class ReminderLoopSpinnerAdapter extends ArrayAdapter<ReminderLoopClass> {
    LayoutInflater layoutInflater;

    public ReminderLoopSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<ReminderLoopClass> users)
    {
        super(context, resource, users);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View rowView = layoutInflater.inflate(R.layout.spinner_value, null,true);
        ReminderLoopClass type = getItem(position);
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

        ReminderLoopClass type = getItem(position);
        TextView textView = (TextView)convertView.findViewById(R.id.tvSpinner);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.ivSpinner);
        textView.setText(type.getName());
        imageView.setImageResource(type.getIcon());
        return convertView;
    }
}
