package com.team206255.dineder;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CalendarScreen extends Fragment {

    TextView dateText;

    ListView calendarList;
    CalendarAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance)
    {
        View view = inflater.inflate(R.layout.activity_calendar_screen, container, false);
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();

        //setting up the calendar icon
        final ImageView calendarIcon = (ImageView) view.findViewById(R.id.CalendarIcon);
        Bitmap calendarImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.calendar_icon, 0.1f);
        calendarIcon.setImageBitmap(calendarImage);

        //setting up the text view
        dateText = (TextView) view.findViewById(R.id.dateText);
        dateText.setText(Singleton.getInstance().getCalendarStorage().dateToString());

        //setting up the left and right button
        ImageView rightButton = (ImageView) view.findViewById(R.id.rightButton);
        Bitmap rightImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.right, 0.1f);
        rightButton.setImageBitmap(rightImage);
        //setting up the on click listener
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.getInstance().getCalendarStorage().nextDay();
                updateCalendarView();
            }
        });

        ImageView leftButton = (ImageView) view.findViewById(R.id.leftButton);
        Bitmap leftImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.left, 0.1f);
        leftButton.setImageBitmap(leftImage);
        //setting up the on click listener
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.getInstance().getCalendarStorage().lastDay();
                updateCalendarView();
            }
        });

        calendarList = (ListView) view.findViewById(R.id.calendarList);
        adapter = new CalendarAdapter(getContext(), Singleton.getInstance().getCalendarStorage());
        calendarList.setAdapter(adapter);
        calendarList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (Singleton.getInstance().getCalendarStorage().getRecipe(i) != null) {
                    Intent informationIntent = new Intent(getContext(), RecipeInformation.class);
                    informationIntent.putExtra("RECIPE", Singleton.getInstance().getCalendarStorage().getRecipe(i));
                    startActivity(informationIntent);
                }
            }
        });

        return view;
    }

    public void updateCalendarView()
    {
        dateText.setText(Singleton.getInstance().getCalendarStorage().dateToString());
        calendarList.invalidate();
        adapter.notifyDataSetChanged();
    }
}
