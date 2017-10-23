package com.team206255.dineder;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Date;

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

        ImageView foodView = (ImageView) findViewById(R.id.recipeImage);
        currentRecipe.setImage(this, foodView, 0.75f);

        //setting up the on click listener for button
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                Singleton.getInstance().getCalendarStorage().addRecipe(currentDate, currentRecipe, index);
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
                currentDate = new Date(y, m, d);
            }
        });


    }
}
