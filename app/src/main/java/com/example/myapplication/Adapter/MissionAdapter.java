package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Object.MissionClass;
import com.example.myapplication.R;

import java.util.List;

public class MissionAdapter extends ArrayAdapter<MissionClass> {
    private Context context;
    private int layout;
    private List<MissionClass> data;
    public MissionAdapter(@NonNull Context context, int resource, @NonNull List<MissionClass> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MissionClass m = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.misson_row,parent,false);
            viewHolder.rbtnMission = convertView.findViewById(R.id.rbtnMission);
            viewHolder.tvMission = convertView.findViewById(R.id.tvMission);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.rbtnMission.isChecked();
        viewHolder.tvMission.setText(m.getName());
        return convertView;
    }
    private static class ViewHolder{
        RadioButton rbtnMission;
        TextView tvMission;
    }
}
