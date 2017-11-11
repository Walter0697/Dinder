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
        Bitmap calendarImage = ImageProcessor.scaleImage(R.drawable.calendar_icon, 0.1f);
        calendarIcon.setImageBitmap(calendarImage);

        //setting up the text view
        dateText = (TextView) view.findViewById(R.id.dateText);
        dateText.setText(UserInformation.getInstance().getCalendarStorage().dateToString());

        //setting up the left and right button
        ImageView rightButton = (ImageView) view.findViewById(R.id.rightButton);
        Bitmap rightImage = ImageProcessor.scaleImage(R.drawable.right, 0.1f);
        rightButton.setImageBitmap(rightImage);
        //setting up the on click listener
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInformation.getInstance().getCalendarStorage().nextDay();
                updateCalendarView();
            }
        });

        ImageView leftButton = (ImageView) view.findViewById(R.id.leftButton);
        Bitmap leftImage = ImageProcessor.scaleImage(R.drawable.left, 0.1f);
        leftButton.setImageBitmap(leftImage);
        //setting up the on click listener
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInformation.getInstance().getCalendarStorage().lastDay();
                updateCalendarView();
            }
        });

        calendarList = (ListView) view.findViewById(R.id.calendarList);
        adapter = new CalendarAdapter(getContext(), UserInformation.getInstance().getCalendarStorage());
        calendarList.setAdapter(adapter);
        calendarList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (UserInformation.getInstance().getCalendarStorage().getRecipe(i) != null) {

                    //load information from the api in case if there is no information
                    if (UserInformation.getInstance().getCalendarStorage().getRecipe(i).fullyLoaded == false)
                        RandomRecipeGenerator.setToRecipeAPI(UserInformation.getInstance().getCalendarStorage().getRecipe(i).id, 2, i);
                    Intent informationIntent = new Intent(getContext(), RecipeInformation.class);
                    informationIntent.putExtra("RECIPE", UserInformation.getInstance().getCalendarStorage().getRecipe(i));
                    startActivity(informationIntent);
                }
            }
        });

        //festival related widgets
        ImageView festiv = (ImageView) view.findViewById(R.id.festival3);
        if (UserInformation.getInstance().getFestival() == InfoDefine.HALLOWEEN) {
            Bitmap halloween = ImageProcessor.scaleImage(R.drawable.hallow1, 0.4f);
            festiv.setImageBitmap(halloween);
        }
        else if (UserInformation.getInstance().getFestival() == InfoDefine.CHRISTMAS)
        {
            Bitmap christmas = ImageProcessor.scaleImage(R.drawable.xmas1, 0.4f);
            festiv.setImageBitmap(christmas);
        }
        else
        {
            festiv.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    public void updateCalendarView()
    {
        dateText.setText(UserInformation.getInstance().getCalendarStorage().dateToString());
        calendarList.invalidate();
        adapter.notifyDataSetChanged();
    }
}
