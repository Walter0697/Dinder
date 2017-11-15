package com.team206255.dineder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);

        //////////////////////////////////////////////////////////
        //SETTING BUTTON
        //enable swiping
        Switch swiping = (Switch) findViewById(R.id.swiping);
        //set it to current user preference first
        swiping.setChecked(UserInformation.getInstance().getEnableSwiping());
        swiping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                UserInformation.getInstance().setEnableSwiping(b);
            }
        });

        //enable screen on
        Switch screenOn = (Switch) findViewById(R.id.KeepingOn);
        //set it to current user preference first
        screenOn.setChecked(UserInformation.getInstance().getEnableScreenOn());
        screenOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                UserInformation.getInstance().setEnableScreenOn(b);
            }
        });

        //back button to close this activity
        Button backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInformation.getInstance().updateSetting();
                finish();
            }
        });

        //set that so the screen won't turn off
        if (UserInformation.getInstance().getEnableScreenOn())
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        else
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}