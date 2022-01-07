package com.example.myapplication.Object;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ParentCategoryClass {
    private String id;
    private String name;
    private int icon;
    private int color;
    private ArrayList<ChildCategoryClass> childCategories = new ArrayList<>();
    private ArrayList<MissionClass> missions = new ArrayList<>();
    private ArrayList<ScheduleClass> activities = new ArrayList<>();

    public ParentCategoryClass() {
    }
    public ParentCategoryClass(String id, String name, int icon, int color){
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.color = color;
    }
    public ParentCategoryClass(String id, String name, int icon, int color, ArrayList<ChildCategoryClass> childCategories, ArrayList<MissionClass> missions, ArrayList<ScheduleClass> activities) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.color = color;
        this.childCategories = childCategories;
        this.missions = missions;
        this.activities = activities;
    }

    public ArrayList<ScheduleClass> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<ScheduleClass> activities) {
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

    public ArrayList<ChildCategoryClass> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(ArrayList<ChildCategoryClass> childCategories) {
        this.childCategories = childCategories;
    }

    public ArrayList<MissionClass> getMissions() {
        return missions;
    }

    public void setMissions(ArrayList<MissionClass> missions) {
        this.missions = missions;
    }

    @Override
    public String toString() {
        return "ParentCategoryClass{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    public static List<ParentCategoryClass> initList() {
        List<ParentCategoryClass> category = new ArrayList<ParentCategoryClass>();
        String[] id = {
                "C1",
                "C2"
        };
        String[] names = {
                "Học Vấn",
                "Công Việc"};
        int[] icons = {R.drawable.ic_baseline_school_24, R.drawable.ic_baseline_school_24};
        for (int i = 0; i < names.length; i++) {
            ParentCategoryClass ca = new ParentCategoryClass();
            ca.setId(id[i]);
            ca.setName(names[i]);
            ca.setIcon(icons[i]);
            category.add(ca);
        }
        return category;
    }
}
