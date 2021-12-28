package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.HashMap;
import java.util.List;

public class MissionFinishedAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<String> lstMissionFinish;
    private HashMap<String,List<String>> lstMissionFinishChild;

    public MissionFinishedAdapter(Context mContext, List<String> lstMissionFinish, HashMap<String, List<String>> lstMissionFinishChild) {
        this.mContext = mContext;
        this.lstMissionFinish = lstMissionFinish;
        this.lstMissionFinishChild = lstMissionFinishChild;
    }

    @Override
    public int getGroupCount() {
        return this.lstMissionFinish.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.lstMissionFinishChild.get(this.lstMissionFinish.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.lstMissionFinish.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.lstMissionFinishChild.get(this.lstMissionFinish.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String title =(String) getGroup(i);

        if (view == null){
            LayoutInflater inflater = (LayoutInflater)this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_mission_parent,null);
        }
        ImageButton ivGroupIndicatorMission = view.findViewById(R.id.ivGroupIndicatorMission);
        LinearLayout layoutMisChild = view.findViewById(R.id.layoutMisChild);
        layoutMisChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(b) ((ExpandableListView) viewGroup).collapseGroup(i);
                else ((ExpandableListView) viewGroup).expandGroup(i, true);
            }
        });
        ivGroupIndicatorMission.setSelected(b);
        TextView tvMissionParent = view.findViewById(R.id.tvMissionParent);
        tvMissionParent.setText(title);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String title =(String) getChild(i,i1);

        if (view == null){
            LayoutInflater inflater = (LayoutInflater)this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_mission_child,null);
        }
        RadioButton rbtnMissionChild = view.findViewById(R.id.rbtnMissionChild);
        TextView tvMissionChild = view.findViewById(R.id.tvMissionChild);
        tvMissionChild.setText(title);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
