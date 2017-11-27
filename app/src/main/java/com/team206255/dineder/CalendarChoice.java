package com.team206255.dineder;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.CalendarContract;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CalendarChoice extends AppCompatActivity {

    Recipe currentRecipe;
    Date currentDate = new Date();
    int index;

    RadioButton breakfastButton;
    RadioButton lunchButton;
    RadioButton afternoonButton;
    RadioButton dinnerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_choice);

        if (getIntent().hasExtra("RECIPE"))
            currentRecipe = (Recipe)getIntent().getSerializableExtra("RECIPE");
        else
            currentRecipe = new Recipe();

        //set up the image in the very top
        final ImageView foodView = (ImageView) findViewById(R.id.recipeImage);
        ImageProcessor.setURLImage(currentRecipe.pictureView, 0.75f,
                new CallbackHelper() {
                    @Override
                    public void onSuccess(JSONObject result) {

                    }

                    @Override
                    public void onSuccess(Bitmap result) {
                        foodView.setImageBitmap(result);
                    }
                });

        //setting up the on click listener for button
        //exiting
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //saving
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                //add the recipe to google calendar if this function is enabled
                if (UserInformation.getInstance().getEnableCalendar())
                    addEventToCalendar(index, currentDate);
                UserInformation.getInstance().getCalendarStorage().addRecipe(currentDate, currentRecipe, index);
                UserInformation.getInstance().updateSharedPreference();
                finish();
            }
        });

        //setting up the radio button
        breakfastButton = (RadioButton) findViewById(R.id.breakfast);
        breakfastButton.setChecked(true);
        lunchButton = (RadioButton) findViewById(R.id.lunch);
        afternoonButton = (RadioButton) findViewById(R.id.afternoon);
        dinnerButton = (RadioButton) findViewById(R.id.dinner);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (breakfastButton.isChecked()) index = 0;
                if (lunchButton.isChecked()) index = 1;
                if (afternoonButton.isChecked()) index = 2;
                if (dinnerButton.isChecked()) index = 3;
            }
        });

        //setting up the calendarView
        CalendarView calendar = (CalendarView) findViewById(R.id.recipeCalendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int y, int m, int d) {
                //deprecated but i still want to use
                currentDate = new Date(y-1900, m, d);
            }
        });

        //set that so the screen won't turn off
        if (UserInformation.getInstance().getEnableScreenOn())
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        else
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    private void addEventToCalendar(int index, Date currentDate)
    {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_CALENDAR))
            {}
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR}, 1);
            }
        }

        ContentResolver cr = getContentResolver();

        Calendar[] time = getTimeFromRecipe(index, currentDate);

        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, time[0].getTimeInMillis());
        values.put(CalendarContract.Events.DTEND, time[1].getTimeInMillis());
        values.put(CalendarContract.Events.TITLE, "Dinder reminder!");
        values.put(CalendarContract.Events.DESCRIPTION, "You saved '" + currentRecipe.name + "' before. \nIt's time to cook your saved recipe!");
        values.put(CalendarContract.Events.CALENDAR_ID, 1);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getDisplayName());
        values.put(CalendarContract.Events.CUSTOM_APP_PACKAGE, getPackageName());
        values.put(CalendarContract.Events.CUSTOM_APP_URI, "CALENDAR");

        cr.insert(CalendarContract.Events.CONTENT_URI, values);

        Toast.makeText(this, "added to Google Calendar!", Toast.LENGTH_SHORT).show();

    }

    //0 -> breakfast
    //1 -> lunch
    //2 -> afternoon
    //3 -> dinner
    private Calendar[] getTimeFromRecipe(int index, Date currentDate)
    {
        Calendar[] returnCalendar = new Calendar[2];

        int startHour = 0;
        switch (index)
        {
            case 0:
                startHour = 7;
                break;
            case 1:
                startHour = 12;
                break;
            case 2:
                startHour = 14;
                break;
            case 3:
                startHour = 18;
                break;
        }

        Calendar beginTime = Calendar.getInstance();
        beginTime.set(currentDate.getYear() + 1900, currentDate.getMonth(), currentDate.getDay(),
                      startHour, 0);
        Calendar endTime = Calendar.getInstance();
        endTime.set(currentDate.getYear() + 1900, currentDate.getMonth(), currentDate.getDay(),
                      startHour, 30);

        returnCalendar[0] = beginTime;
        returnCalendar[1] = endTime;
        return returnCalendar;
    }
}
