package edu.skku.swp3.metroapp;

/**
 * Created by lufov on 2018-05-22.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static android.content.ContentValues.TAG;

public class MetroMap extends Activity{
    DrawView drawView;
    //private float namX, namY, saX, saY, chX, chY, sungX, sungY, naeX, naeY, nakX, nakY, bangX, bangY;
    //private float x, y;
    private Integer[] numBtnIDs = { R.id.nam, R.id.sadang, R.id.chongsin, R.id.namsung, R.id.naebang, R.id.naksung, R.id.bangbae, };
    private String [] name ={"남태령", "사당", "총신대입구(이수)", "내방", "낙성", "방배"};
    private Button btn[] = new Button[10];
    private Button back;
    private String departure;
    private String arrival;
    private int index;
    private int i;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metro_map);


        AlertDialog.Builder ad = new AlertDialog.Builder(MetroMap.this);

/*
        ////4호선
        nam = (Button)findViewById(R.id.nam);
        sadang = (Button)findViewById(R.id.sadang);
        chong = (Button)findViewById(R.id.chongsin);
        ///7호선
        namsung = (Button)findViewById(R.id.namsung);
        naebang = (Button)findViewById(R.id.naebang);
        //2호선
        naksung = (Button)findViewById(R.id.naksung);
        bangbae = (Button)findViewById(R.id.bangbae);
*/
        for(i=0;i<7;i++){
            btn[i]=(Button)findViewById(numBtnIDs[i]);
        }
        for(i=0;i<7;i++){
            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popUp(v, i);
                }
            });
        }
        back=(Button)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MetroMap.this, MainActivity.class);
                intent.putExtra("departure", departure);
                intent.putExtra("arrival", arrival);
                startActivity(intent);
            }
        });
        Intent intent=new Intent(this.getIntent());
    }
    //남태령, 사당, 총신, 남성, 내방, 낙성, 방배
    public void popUp(View v, int index){
        this.index=index;
        AlertDialog.Builder ad = new AlertDialog.Builder(MetroMap.this);

        ad.setTitle("Title");       // 제목 설정
        switch(index) {
            case 0:
                ad.setMessage("남태령");   // 내용 설정
                break;
            case 1:
                ad.setMessage("사당");   // 내용 설정
                break;
            case 2:
                ad.setMessage("총신대입구(이수)");   // 내용 설정
                break;
            case 3:
                ad.setMessage("남성");   // 내용 설정
                break;
            case 4:
                ad.setMessage("내방");   // 내용 설정
                break;
            case 5:
                ad.setMessage("낙성");   // 내용 설정
                break;
            case 6:
                ad.setMessage("방배");   // 내용 설정
                break;
            default:
                break;
        }

        ad.setMessage("");   // 내용 설정
        // 확인 버튼 설정
        ad.setPositiveButton("출발역", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG,"Departure Btn Click");
                setDeparture();
                dialog.dismiss();     //닫기
                // Event
            }
        });
        ad.setNegativeButton("도착역", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG,"Arrival Btn Click");
                setArrival();
                dialog.dismiss();     //닫기
                // Event
            }
        });

        ad.setNeutralButton("닫기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG,"Close Btn Click");
                dialog.dismiss();     //닫기
                // Event
            }
        });

        // 창 띄우기
        ad.show();
    }


    public void setDeparture(){
        this.departure=name[this.index];
    }

    public void setArrival(){
        this.arrival=name[this.index];
    }

    /*
    public void toViewRawXY(View view){
        View parentview = view.getRootView();
        float sumX=0;
        float sumY=0;

        boolean chk = false;
        while(!chk){
            sumX=sumX+view.getLeft();
            sumY=sumY+view.getRight();

            view = (View)view.getParent();
            if(parentview==view){
                chk=true;
            }
        }
        position[count]=sumX;
        count++;
        position[count]=sumY;
        count++;
    }
    */
}
