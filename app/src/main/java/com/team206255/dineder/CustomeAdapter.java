package com.team206255.dineder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.team206255.dineder.InfoDefine.*;

/**
 * Created by walter on 2017-10-03.
 */

//create a custome adapter that will have the information for all listview we have in this app
//including liked list, saved list, and searched list

public class CustomeAdapter extends BaseAdapter {

    Context context;
    Activity activity;
    DisplayMetrics metrics;
    LayoutInflater inflater;
    RecipeList items;

    //temp variable for button click
    int j;

    int layout;
    ListType type;

    public CustomeAdapter(Context c, RecipeList items, int layout, ListType type)
    {
        this.type = type;
        this.layout = layout;
        this.items = items;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = c;
        metrics = c.getResources().getDisplayMetrics();
    }

    public CustomeAdapter(Context c, RecipeList items, int layout, ListType type, Activity a)
    {
        this.type = type;
        this.layout = layout;
        this.items = items;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = c;
        metrics = c.getResources().getDisplayMetrics();
        activity = a;
    }

    @Override
    public int getCount() {
        return items.getSize();
    }

    @Override
    public Object getItem(int i) {
        return items.getRecipe(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = inflater.inflate(layout, null);
        switch(type)
        {
            case LIKED_LIST:
                TextView recipeName = (TextView) v.findViewById(R.id.likedRecipeName);
                recipeName.setText(items.getRecipe(i).name);

                TextView addedTime = (TextView) v.findViewById(R.id.likedRecipeAddedTime);
                addedTime.setText(items.getDateToString(i));

                ImageView saveButton = (ImageView) v.findViewById(R.id.saveButton);
                Bitmap saveImage = ImageProcessor.scaleImage(metrics, context.getResources(), R.drawable.heart, 0.035f);
                saveButton.setImageBitmap(saveImage);

                ImageView removeButton = (ImageView) v.findViewById(R.id.removeButton);
                Bitmap removeImage = ImageProcessor.scaleImage(metrics, context.getResources(), R.drawable.unchecked, 0.035f);
                removeButton.setImageBitmap(removeImage);

                removeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Singleton.getInstance().getRecipeList().removeRecipe(i);
                        notifyDataSetChanged();
                        //update the shared preference
                        Singleton.getInstance().updateSharedPreference();
                    }
                });


                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent calendarIntent = new Intent(activity.getApplicationContext(), CalendarChoice.class);
                        calendarIntent.putExtra("RECIPE", items.getRecipe(i));
                        activity.startActivityForResult(calendarIntent, InfoDefine.REQUEST_FOR_CALENDAR);
                        notifyDataSetChanged();
                    }
                });

                break;
            case SEARCH_LIST:
                TextView searchRecipeName = (TextView) v.findViewById(R.id.searchRecipeName);
                searchRecipeName.setText(items.getRecipe(i).name);

                TextView searchCuisine = (TextView) v.findViewById(R.id.searchCuisine);
                searchCuisine.setText(items.getRecipe(i).getCuisine(context));

                TextView searchDuration = (TextView) v.findViewById(R.id.searchDuration);
                searchDuration.setText(Float.toString(items.getRecipe(i).duration) + " mins");

                TextView searchCalorie = (TextView) v.findViewById(R.id.searchCalorie);
                searchCalorie.setText("Calorie: " + Float.toString(items.getRecipe(i).calorie));

                RatingBar searchDifficulty = (RatingBar) v.findViewById(R.id.searchDifficulty);
                searchDifficulty.setRating(items.getRecipe(i).difficulty);

                ImageView searchSaveButton = (ImageView) v.findViewById(R.id.searchSave);
                Bitmap searchSaveImage = ImageProcessor.scaleImage(metrics, context.getResources(), R.drawable.heart, 0.05f);
                searchSaveButton.setImageBitmap(searchSaveImage);
                searchSaveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent calendarIntent = new Intent(activity.getApplicationContext(), CalendarChoice.class);
                        calendarIntent.putExtra("RECIPE", items.getRecipe(i));
                        activity.startActivityForResult(calendarIntent, InfoDefine.REQUEST_FOR_CALENDAR);
                        notifyDataSetChanged();
                    }
                });

                ImageView searchRecipe = (ImageView) v.findViewById(R.id.searchRecipeImage);
                items.getRecipe(i).setImage(context, searchRecipe, 0.2f);
                break;
        }
        return v;
    }
}
