package com.team206255.dineder;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

/**
 * Created by walter on 2017-10-13.
 */

public class CalendarAdapter extends BaseAdapter {

    CalendarStorage calendarStorage;

    Context context;
    LayoutInflater inflater;

    public CalendarAdapter(Context c, CalendarStorage cs)
    {
        context = c;
        calendarStorage = cs;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
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

        final ImageView calendarImage = (ImageView) v.findViewById(R.id.calendarFoodView);
        TextView calendarName = (TextView) v.findViewById(R.id.calendarFoodName);
        TextView calendarCalorie = (TextView) v.findViewById(R.id.calendarCalorie);
        ImageView removeButton = (ImageView) v.findViewById(R.id.removeButton);

        Recipe currentRecipe = (Recipe)getItem(i);

        if (currentRecipe != null) {
            nullText.setVisibility(View.INVISIBLE);
            //setting all other widgets visible
            calendarImage.setVisibility(View.VISIBLE);
            calendarName.setVisibility(View.VISIBLE);
            calendarCalorie.setVisibility(View.VISIBLE);
            removeButton.setVisibility(View.VISIBLE);

            ImageProcessor.setURLImage(currentRecipe.pictureView, 0.2f,
                    new CallbackHelper() {
                        @Override
                        public void onSuccess(JSONObject result) {

                        }

                        @Override
                        public void onSuccess(Bitmap result) {
                            calendarImage.setImageBitmap(result);
                            notifyDataSetChanged();
                        }
                    });

            calendarName.setText(currentRecipe.name);
            calendarCalorie.setText("Calorie: " + Float.toString(currentRecipe.calorie));
            Bitmap removeBitmap = ImageProcessor.scaleImage(R.drawable.unchecked, 0.05f);
            removeButton.setImageBitmap(removeBitmap);
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    calendarStorage.removeRecipe(i);
                    notifyDataSetChanged();
                    //update shared preference
                    UserInformation.getInstance().updateSharedPreference();
                }
            });
        }
        else
        {
            nullText.setVisibility(View.VISIBLE);
            //setting all other widgets invisible
            calendarImage.setVisibility(View.INVISIBLE);
            calendarName.setVisibility(View.INVISIBLE);
            calendarCalorie.setVisibility(View.INVISIBLE);
            removeButton.setVisibility(View.INVISIBLE);
        }
        return v;
    }
}
