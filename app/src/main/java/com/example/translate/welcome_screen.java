package com.example.translate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

public class welcome_screen extends AppCompatActivity {
private static int SPLASH_TIME_OUT =4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent=new Intent(welcome_screen.this,Translation_Page.class);
//                startActivity(intent);
//                finish();
//            }
//        },SPLASH_TIME_OUT);
    }
    public void onStart(){
        super.onStart();
        CountDownTimer timer= new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent=new Intent(welcome_screen.this,Translation_Page.class);
                startActivity(intent);
            }
        }.start();
    }
}
