package com.example.myapplication.Object;

public class ReminderClass {
    private String id, name, time, date;
    private boolean type;   // kiểu nhắc nhở: 0 -> một lần, 1 -> lặp lại
    private int duration;   // thời lượng: 1, 3, 5 (phút)
    private int loopTime;   // số lần lặp: 1, 3, 5 (nếu 0 -> liên tục)
    private boolean capcha; // sử dụng capcha: true, false

    public ReminderClass() {
    }

    public ReminderClass(String id, String name, String time, String date, boolean type, int duration, int loopTime, boolean capcha) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.date = date;
        this.type = type;
        this.duration = duration;
        this.loopTime = loopTime;
        this.capcha = capcha;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getLoopTime() {
        return loopTime;
    }

    public void setLoopTime(int loopTime) {
        this.loopTime = loopTime;
    }

    public boolean isCapcha() {
        return capcha;
    }

    public void setCapcha(boolean capcha) {
        this.capcha = capcha;
    }
}
