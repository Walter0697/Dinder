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

        //set up context inside imageprocessor
        ImageProcessor.setContext(this);

        //loading everything before getting inside the main screen
        //set up context for getrequestgenerator
        GetRequestURLGenerate.setContact(this);
        //set up things for the random recipe generator
        RandomRecipeGenerator.setupDummy();
        RandomRecipeGenerator.setUpQueue(this);

        //setting up everything from the shared preference
        UserInformation.getInstance().setSharedPreferences(this);
        //if for testing ,wanna reset everything inside the app
        UserInformation.getInstance().resetSharedPreference();


        //starting the splashscreen and then start the main screen
        Thread startThread = new Thread(){
            @Override
            public void run(){
                try{
                    super.run();
                    sleep(500);
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
