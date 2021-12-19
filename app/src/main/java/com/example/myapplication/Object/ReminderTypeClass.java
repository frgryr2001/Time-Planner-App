package com.example.myapplication.Object;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ReminderTypeClass {
    String name;
    int icon;

    public ReminderTypeClass(String name, int icon) {
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

    public static List<ReminderTypeClass> initList() {
        List<ReminderTypeClass> types = new ArrayList<ReminderTypeClass>();
        String[] names = {
                "Một Lần",
                "Lặp Lại",
                "Lặp lại theo vòng",
                "Lặp theo chu kì trong khoảng thời gian",
                "Ngẫu Nhiên"};
        int[] icons = {R.drawable.only_once, R.drawable.loop, R.drawable.loop_inf, R.drawable.loop_time_period, R.drawable.random};
        for (int i = 0; i < names.length; i++) {
            ReminderTypeClass type = new ReminderTypeClass(names[i], icons[i]);
            types.add(type);
        }
        return types;
    }
}
