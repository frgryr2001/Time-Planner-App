package com.example.myapplication.Object;

import java.util.ArrayList;
import java.util.List;

public class MissionClass {

    private String id;
    private String name;
    private boolean check = false;

    public MissionClass() {
    }

    public MissionClass(String id, String name, boolean check) {
        this.id = id;
        this.name = name;
        this.check = check;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
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
            if(!mission.isCheck()){
                list.add(mission);
            }
        }
        return  list;
    }
}
