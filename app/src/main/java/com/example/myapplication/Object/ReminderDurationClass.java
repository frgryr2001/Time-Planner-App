package com.example.myapplication.Object;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ReminderDurationClass {
    private String name;
    private int icon;

    public ReminderDurationClass() {
    }

    public ReminderDurationClass(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public static List<ReminderDurationClass> initList() {
        List<ReminderDurationClass> durations = new ArrayList<ReminderDurationClass>();
        String[] names = {
                "1 phút",
                "3 phút",
                "5 phút"};
        int[] icons = {R.drawable.timer, R.drawable.timer, R.drawable.timer};
        for (int i = 0; i < names.length; i++) {
            ReminderDurationClass type = new ReminderDurationClass(names[i], icons[i]);
            durations.add(type);
        }
        return durations;
    }
}
