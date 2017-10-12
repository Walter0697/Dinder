package com.team206255.dineder;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CalendarScreen extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance)
    {
        View view = inflater.inflate(R.layout.activity_calendar_screen, container, false);
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();

        ImageView calendarIcon = (ImageView) view.findViewById(R.id.CalendarIcon);
        Bitmap calendarImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.dinner, 0.1f);
        calendarIcon.setImageBitmap(calendarImage);

        ImageView rightButton = (ImageView) view.findViewById(R.id.rightButton);

        ImageView LeftButton = (ImageView) view.findViewById(R.id.leftButton);

        return view;
    }
}
