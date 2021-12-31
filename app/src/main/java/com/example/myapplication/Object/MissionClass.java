package com.example.myapplication.Object;

import java.util.ArrayList;
import java.util.List;

public class MissionClass {
    private String id;
    private String name;
    private boolean status = false; // Trạng thái
    private int priority;   // Độ ưu tiên: 1(xanh), 2(vàng), 3(đỏ)

    public MissionClass() {
    }

    public MissionClass(String id, String name, boolean status, int priority) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<MissionClass> init(){
        String[] id = {"1","2","3"};
        String[] name = {"Đi học","Đi chơi","Đi đá banh"};
        List<MissionClass> list = new ArrayList<>();
        for (int i = 0 ; i < id.length ; i++){
            MissionClass mission = new MissionClass();
            mission.setId(id[i]);
            mission.setName(name[i]);
            if(!mission.isStatus()){
                list.add(mission);
            }
        }
        return  list;
    }
}
