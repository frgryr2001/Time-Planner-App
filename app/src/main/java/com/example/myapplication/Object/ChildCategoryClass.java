package com.example.myapplication.Object;

import java.util.ArrayList;

public class ChildCategoryClass {
    private String id;
    private String idParent;
    private String name;
    private int icon;
    private int color;
    private ArrayList<MissionClass> missions = new ArrayList<>();
    private ArrayList<ScheduleClass> activities;

    public ChildCategoryClass() {
    }

    public ChildCategoryClass(String id, String name, int icon, int color, ArrayList<MissionClass> missions, ArrayList<ScheduleClass> activities) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.color = color;
        this.missions = missions;
        this.activities = activities;
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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public ArrayList<MissionClass> getMissions() {
        return missions;
    }

    public void setMissions(ArrayList<MissionClass> missions) {
        this.missions = missions;
    }

    public ArrayList<ScheduleClass> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<ScheduleClass> activities) {
        this.activities = activities;
    }

    public String getIdParent() {
        return idParent;
    }

    public void setIdParent(String idParent) {
        this.idParent = idParent;
    }
}
