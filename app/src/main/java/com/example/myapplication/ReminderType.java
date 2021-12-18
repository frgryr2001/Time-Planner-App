package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class ReminderType {
    String name;
    int icon;

    public ReminderType(String name, int icon) {
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

    public static List<ReminderType> initList() {
        List<ReminderType> types = new ArrayList<ReminderType>();
        String[] names = {
                "Một Lần",
                "Lặp Lại",
                "Lặp lại theo vòng",
                "Lặp theo chu kì trong khoảng thời gian",
                "Ngẫu Nhiên"};
        int[] icons = {R.drawable.only_once, R.drawable.loop, R.drawable.loop_inf, R.drawable.loop_time_period, R.drawable.random};
        for (int i = 0; i < names.length; i++) {
            ReminderType type = new ReminderType(names[i], icons[i]);
            types.add(type);
        }
        return types;
    }
}
