package com.team206255.dineder;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Rating;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by walter on 2017-10-13.
 */

public class CalendarAdapter extends BaseAdapter {

    CalendarStorage calendarStorage;

    Context context;
    DisplayMetrics metrics;
    LayoutInflater inflater;

    public CalendarAdapter(Context c, CalendarStorage cs)
    {
        context = c;
        calendarStorage = cs;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        metrics = c.getResources().getDisplayMetrics();
    }

    public void setCalendarStorage(CalendarStorage cs)
    {
        calendarStorage = cs;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int i) {
        Recipe[] currentRecipes = calendarStorage.getRecipe();
        if (currentRecipes == null) return null;
        return currentRecipes[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = inflater.inflate(R.layout.calendar_list_detail, null);

        if (i >= 4) return null;
        //setting calendar Type
        TextView calType = (TextView) v.findViewById(R.id.calendarType);
        switch (i) {
            case 0:
                calType.setText("Breakfast");
                break;
            case 1:
                calType.setText("Lunch");
                break;
            case 2:
                calType.setText("Afternoon Tea");
                break;
            case 3:
                calType.setText("Dinner");
                break;
        }
        //setting all widgets first
        TextView nullText = (TextView) v.findViewById(R.id.nullText);

        ImageView calendarImage = (ImageView) v.findViewById(R.id.calendarFoodView);
        TextView calendarName = (TextView) v.findViewById(R.id.calendarFoodName);
        TextView calendarDuration = (TextView) v.findViewById(R.id.calendarDuration);
        TextView calendarCalorie = (TextView) v.findViewById(R.id.calendarCalorie);
        TextView calendarCuisine = (TextView) v.findViewById(R.id.calendarCuisine);
        RatingBar calendarDifficulty = (RatingBar) v.findViewById(R.id.calendarDifficulty);

        Recipe currentRecipe = (Recipe)getItem(i);

        if (currentRecipe != null) {
            nullText.setVisibility(View.INVISIBLE);
            //setting all other widgets visible
            calendarImage.setVisibility(View.VISIBLE);
            calendarName.setVisibility(View.VISIBLE);
            calendarDuration.setVisibility(View.VISIBLE);
            calendarCalorie.setVisibility(View.VISIBLE);
            calendarCuisine.setVisibility(View.VISIBLE);
            calendarDifficulty.setVisibility(View.VISIBLE);

            currentRecipe.setImage(context, calendarImage, 0.2f);
            calendarName.setText(currentRecipe.name);
            calendarDuration.setText("Duration: " + Float.toString(currentRecipe.duration) + "mins");
            calendarCalorie.setText("Calorie: " + Float.toString(currentRecipe.calorie));
            calendarCuisine.setText(currentRecipe.getCuisine(context));
            calendarDifficulty.setRating(currentRecipe.difficulty);
        }
        else
        {
            nullText.setVisibility(View.VISIBLE);
            //setting all other widgets invisible
            calendarImage.setVisibility(View.INVISIBLE);
            calendarName.setVisibility(View.INVISIBLE);
            calendarDuration.setVisibility(View.INVISIBLE);
            calendarCalorie.setVisibility(View.INVISIBLE);
            calendarCuisine.setVisibility(View.INVISIBLE);
            calendarDifficulty.setVisibility(View.INVISIBLE);
        }
        return v;
    }
}
