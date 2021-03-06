package com.example.myapplication.Object;

import java.time.LocalDate;
import java.util.ArrayList;

public class ScheduleClass {
    private String id, name, date, timeStart, timeEnd, note;

    public ScheduleClass() {
    }

    public ScheduleClass(String id, String name, String date, String timeStart, String timeEnd, String note) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public static ArrayList<ScheduleClass> init() {
        ArrayList<ScheduleClass> list = new ArrayList<ScheduleClass>();
        for (int i = 0; i < 5; i++) {
            ScheduleClass s = new ScheduleClass();
            s.setName("Test");
            s.setTimeStart("9:30");
            s.setTimeEnd("10:30");
            list.add(s);
        }
        return list;
    }
}
