package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class CategoryClass {
    private String id;
    private String name;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    private int icon;
    public CategoryClass(){

    }

    public CategoryClass(String id, String name,int icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
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
            CategoryClass ca = new CategoryClass(id[i], names[i],icons[i]);
            category.add(ca);
        }
        return category;
    }
}
