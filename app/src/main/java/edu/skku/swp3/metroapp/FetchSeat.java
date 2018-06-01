package edu.skku.swp3.metroapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by TS on 2018. 5. 31..
 */

public class FetchSeat extends AsyncTask<Void,Void,Void> {

    public FetchSeat(){
      //  PrintWriter
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String rec;
        try {
            Socket soc = new Socket("192.168.0.6", 5000);

            InputStream in = soc.getInputStream();
            Scanner sc = new Scanner(in);

            //ObjectInputStream ois = new ObjectInputStream (soc.getInputStream());
            //rec=(String)ois.readObject(); //read here
            Log.i("From Server", sc.nextLine());
            //ois.close();
            sc.close();
            soc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
