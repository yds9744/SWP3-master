package edu.skku.swp3.metroapp;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by TS on 2018. 5. 31..
 */


public class StationClass {
    HashMap<ArrayList<String>,MetroClass> stationtable;
    String stationname;
    public boolean isinterchange;
    StationClass(String name){

        stationtable= new HashMap<>();
        stationname=name;
        isinterchange=false;
    }

    public void addcar(int lane,String updown, int time,MetroClass car){
        ArrayList<String> key=new ArrayList<>();
        key.add(Integer.toString(lane));//lane info
        key.add(updown);//up,down info
        key.add(Integer.toString(time));//car time
        stationtable.put(key,car);
        return;
    }

    public MetroClass getcar(int lane,String updown, int time){
        ArrayList<String> key=new ArrayList<>();
        key.add(Integer.toString(lane));//lane info
        key.add(updown);//up,down info
        key.add(Integer.toString(time));//car time
        return stationtable.get(key);
    }
}
