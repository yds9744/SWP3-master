package edu.skku.swp3.metroapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by TS on 2018. 5. 31..
 */

class PathData{
    ArrayList<String> path;
    public int length;
    PathData(){
        path=new ArrayList<>();
    }
    void addstation(String station){
        path.add(station);
    }
}
public class StationHolder extends AsyncTask< Void, Void, Void> {
    HashMap<String,StationClass> stationmap;
    HashMap<Integer,ArrayList<String>> stationorder;
    HashMap<ArrayList<String>,PathData> pathtable;

    int numlines;
    Context ctxt;
    //ArrayList<String> stations;
    StationHolder(Context context){
        stationmap=new HashMap<>();
        stationorder=new HashMap<>();
        pathtable=new HashMap<>();
        ctxt=context;
    }

    public void addstation(String name,int lane){
        if(stationmap.containsKey(name)) {
            stationmap.get(name).isinterchange=true;
        }else{
            stationmap.put(name,new StationClass(name));
        }
        stationorder.get(lane).add(name);
        return;
    }

    public PathData findpath(String from,String to){
        ArrayList<String> target=new ArrayList<>();
        target.add(from);
        target.add(to);
        return pathtable.get(target);
    }
    public StationClass getstation(String name){
        return stationmap.get(name);
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.i("Reading db", "background process for file read starts");
        Scanner sc = new Scanner(ctxt.getResources().openRawResource(R.raw.db));
        StringTokenizer tokenizer;
        //1. get number of lane infos
        String line,stationname;
        line=sc.nextLine();
        numlines=Integer.parseInt(line);
        ArrayList<String> order;
        int linenum;
        for(int i=0;i<numlines;i++){
            line=sc.nextLine();
            Log.i("Reading First Part", line);
            tokenizer = new StringTokenizer(line, ",");
            order=new ArrayList<>();
            linenum=Integer.parseInt(tokenizer.nextToken());
            stationorder.put(linenum,new ArrayList<String>());
            while(tokenizer.hasMoreTokens()){//get stations
                stationname=tokenizer.nextToken();
                order.add(stationname);//add to order
                addstation(stationname,linenum);
            }
            stationorder.put(linenum,order);
        }//first part complete

        //get lane info
        String updown;
        int first,last,intv,numstation;
        MetroClass car;
        for(int i=0;i<numlines;i++){
            line=sc.nextLine();
            Log.i("Reading second part ", line);
            tokenizer = new StringTokenizer(line, ",");
            //up
            linenum=Integer.parseInt(tokenizer.nextToken());
            order=stationorder.get(linenum);
            updown=tokenizer.nextToken();
            first=Integer.parseInt(tokenizer.nextToken());
            last=Integer.parseInt(tokenizer.nextToken());
            intv=Integer.parseInt(tokenizer.nextToken());
            car=new MetroClass();
            for(int j=first;j<=last;j+=intv){
                numstation=order.size();
                for(int k=0;k<numstation;k++) {
                    stationmap.get(order.get(k)).addcar(linenum, updown, j, car);
                }
            }
            //down - reverse order
            line=sc.nextLine();
            Log.i("Reading second part ", line);
            tokenizer = new StringTokenizer(line, ",");
            //up
            linenum=Integer.parseInt(tokenizer.nextToken());
            order=stationorder.get(linenum);
            updown=tokenizer.nextToken();
            first=Integer.parseInt(tokenizer.nextToken());
            last=Integer.parseInt(tokenizer.nextToken());
            intv=Integer.parseInt(tokenizer.nextToken());
            for(int j=first;j<=last;j+=intv) {
                car = new MetroClass();
                numstation = order.size();
                for (int k = numstation - 1; k > 0; k--) {
                    stationmap.get(order.get(k)).addcar(linenum, updown, j, car);
                }
            }
        }//completed stations


        //get paths
        String from,to;
        int count;
        ArrayList<String> target;
        while(sc.hasNext()){
            line=sc.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            target=new ArrayList<>();
            from=tokenizer.nextToken();
            to=tokenizer.nextToken();
            target.add(from);
            target.add(to);
            PathData pth=new PathData();
            count=0;
            while(tokenizer.hasMoreTokens()){
                pth.addstation(tokenizer.nextToken());
                count++;
            }
            pth.length=count;
            pathtable.put(target,pth);
        }

        Log.i("Finished Reading db", "Read complete");
        sc.close();
        return null;
    }

    //유아이 처리하는 메인 쓰레드
    @Override
    public void onPostExecute(Void v) {

        //toast message - db load complete
        Toast.makeText(ctxt,"DB loading complete!",Toast.LENGTH_LONG).show();

    }


}

