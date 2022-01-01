package com.example.myapplication.Object;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ReminderLoopClass {
    private String name;
    private int icon;

    public ReminderLoopClass() {
    }

    public ReminderLoopClass(String name, int icon) {
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

    public static List<ReminderLoopClass> initList() {
        List<ReminderLoopClass> loops = new ArrayList<ReminderLoopClass>();
        String[] names = {
                "1 lần",
                "3 lần",
                "5 lần",
                "Liên tục"};
        int[] icons = {R.drawable.loop_black_24dp, R.drawable.loop_black_24dp, R.drawable.loop_black_24dp, R.drawable.loop_black_24dp};
        for (int i = 0; i < names.length; i++) {
            ReminderLoopClass loop = new ReminderLoopClass(names[i], icons[i]);
            loops.add(loop);
        }
        return loops;
    }
}
