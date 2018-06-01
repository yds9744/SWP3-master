package edu.skku.swp3.metroapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button map;
    private Button btnList; //출발시간 선택 버튼
    private Button departList; //출발지 선택 버튼
    private Button arrivalList; //도착지 선택 버튼
    private TextView departText;    //출발 시간 텍스트뷰 선택시 내용 바뀜
    private String departure; //출발지
    private String arrival;     //도착지
    private String departTime;  //출발 시간
    private int select_depart=0; //출발지 선택 햇는지 유무
    private int select_arrive=0; //도착지 선택 했는지 유무
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<String> selectedItems = new ArrayList<String>();

        departText=(TextView)findViewById(R.id.depart_text);
        departList=(Button)findViewById(R.id.depart_btn);
        arrivalList=(Button)findViewById(R.id.arrival_btn);
        btnList = (Button) findViewById(R.id.btnList);

        map=(Button)findViewById(R.id.map);

        FetchSeat fs = new FetchSeat();
        fs.execute();
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MetroMap.class);
                startActivity(intent);

                Intent intent2=getIntent();
                departure=intent2.getStringExtra("departure");
                arrival=intent2.getStringExtra("arrival");
                departList.setText(departure);
                arrivalList.setText(arrival);

            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[]{"오전 11시", "오후 12시", "오후 1시"};
                final int[] selectedIndex = {0};
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog .setTitle("출발 시간")
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { selectedIndex[0] = which; } })
                                .setPositiveButton("선택", new DialogInterface.OnClickListener() {
                            @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    departTime=items[selectedIndex[0]];
                                    departText.setText(items[selectedIndex[0]]);

                                }
                            }).create().show();
            }
        });

        departList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[]{"성균관대역", "수원역"};
                final int[] selectedIndex = {0};
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog .setTitle("출발역")
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { selectedIndex[0] = which; } })
                        .setPositiveButton("선택", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(select_arrive!=0) {
                                    if (arrival.equals(items[selectedIndex[0]])) {
                                        select_depart=1;
                                        Toast.makeText(MainActivity.this, "출발지와 도착지가 같습니다. \n다시 선택해주세요!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        departList.setText(items[selectedIndex[0]]);
                                        departure = items[selectedIndex[0]];
                                    }
                                }
                                else{
                                    departList.setText(items[selectedIndex[0]]);
                                    departure = items[selectedIndex[0]];
                                    select_depart=1;
                                }
                            }

                        }).create().show();
            }
        });
        arrivalList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[]{"성균관대역", "수원역"};
                final int[] selectedIndex = {0};
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog .setTitle("도착역")
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { selectedIndex[0] = which; } })
                        .setPositiveButton("선택", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(select_depart!=0) {
                                    if (departure.equals(items[selectedIndex[0]])) {
                                        select_arrive=1;
                                        Toast.makeText(MainActivity.this, "출발지와 도착지가 같습니다. \n다시 선택해주세요!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        arrivalList.setText(items[selectedIndex[0]]);
                                        arrival = items[selectedIndex[0]];
                                    }
                                }
                                else{
                                    arrivalList.setText(items[selectedIndex[0]]);
                                    arrival = items[selectedIndex[0]];
                                    select_arrive=1;
                                }
                            }
                        }).create().show();
            }
        });
    }
}

