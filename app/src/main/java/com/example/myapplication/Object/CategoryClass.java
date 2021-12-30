package com.example.myapplication.Object;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryClass {
    private String id;
    private String name;
    private int icon;
    private String color;
    private ArrayList<CategoryClass> childCategories;
    private ArrayList<MissionClass> missions;

    public CategoryClass() {
    }

    public CategoryClass(String id, String name, int icon, String color, ArrayList<CategoryClass> childCategories, ArrayList<MissionClass> missions) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.color = color;
        this.childCategories = childCategories;
        this.missions = missions;
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

    public ArrayList<CategoryClass> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(ArrayList<CategoryClass> childCategories) {
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
        return "CategoryClass{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    public static List<CategoryClass> initList() {
        List<CategoryClass> category = new ArrayList<CategoryClass>();
        String[] id = {
                "C1",
                "C2"
        };
        String[] names = {
                "Học Vấn",
                "Công Việc"};
        int[] icons = {R.drawable.ic_baseline_school_24, R.drawable.ic_baseline_school_24};
        for (int i = 0; i < names.length; i++) {
            CategoryClass ca = new CategoryClass();
            ca.setId(id[i]);
            ca.setName(names[i]);
            ca.setIcon(icons[i]);
            category.add(ca);
        }
        return category;
    }
}
