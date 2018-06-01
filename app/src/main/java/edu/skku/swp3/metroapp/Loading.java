package edu.skku.swp3.metroapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Loading extends Activity{
    private ImageView imgAndroid;
    private Animation anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        initView();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    private void initView(){
        imgAndroid = (ImageView) findViewById(R.id.img_android);
        anim = AnimationUtils.loadAnimation(this, R.anim.loading);
                imgAndroid.setAnimation(anim);
    }
}