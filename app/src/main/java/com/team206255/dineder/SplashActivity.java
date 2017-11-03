package com.team206255.dineder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//code from https://www.bignerdranch.com/blog/splash-screens-the-right-way/

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Thread startThread = new Thread(){
            @Override
            public void run(){
                try{
                    super.run();
                    sleep(1500);
                } catch(Exception e){

                } finally {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        startThread.start();
    }
}
