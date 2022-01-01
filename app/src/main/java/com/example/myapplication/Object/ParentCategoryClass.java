package com.example.myapplication.Object;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ParentCategoryClass {
    private String id;
    private String name;
    private int icon;
    private String color;
    private ArrayList<ChildCategoryClass> childCategories;
    private ArrayList<MissionClass> missions;
    private ArrayList<ActivityClass> activities;

    public ParentCategoryClass() {
    }

    public ParentCategoryClass(String id, String name, int icon, String color, ArrayList<ChildCategoryClass> childCategories, ArrayList<MissionClass> missions, ArrayList<ActivityClass> activities) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.color = color;
        this.childCategories = childCategories;
        this.missions = missions;
        this.activities = activities;
    }

    public ArrayList<ActivityClass> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<ActivityClass> activities) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
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