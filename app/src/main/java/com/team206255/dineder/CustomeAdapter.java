package com.team206255.dineder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.team206255.dineder.InfoDefine.*;

import org.json.JSONObject;

/**
 * Created by walter on 2017-10-03.
 */

//create a custome adapter that will have the information for all listview we have in this app
//including liked list, saved list, and searched list

public class CustomeAdapter extends BaseAdapter {

    Context context;
    Activity activity;
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
    }

    public CustomeAdapter(Context c, RecipeList items, int layout, ListType type, Activity a)
    {
        this.type = type;
        this.layout = layout;
        this.items = items;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = c;
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
                Bitmap saveImage = ImageProcessor.scaleImage(R.drawable.listsave, 0.05f);
                saveButton.setImageBitmap(saveImage);

                ImageView removeButton = (ImageView) v.findViewById(R.id.removeButton);
                final Bitmap removeImage = ImageProcessor.scaleImage(R.drawable.unchecked, 0.05f);
                removeButton.setImageBitmap(removeImage);

                removeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UserInformation.getInstance().getRecipeList().removeRecipe(i);
                        notifyDataSetChanged();
                        //update the shared preference
                        UserInformation.getInstance().updateSharedPreference();
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

                TextView searchCalorie = (TextView) v.findViewById(R.id.searchCalorie);
                searchCalorie.setText("Calorie: " + Float.toString(items.getRecipe(i).calorie));

                ImageView searchSaveButton = (ImageView) v.findViewById(R.id.searchSave);
                Bitmap searchSaveImage = ImageProcessor.scaleImage(R.drawable.listsave, 0.08f);
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

                final ImageView searchRecipe = (ImageView) v.findViewById(R.id.searchRecipeImage);
                ImageProcessor.setURLImage(items.getRecipe(i).pictureView, 0.2f,
                        new CallbackHelper() {
                            @Override
                            public void onSuccess(JSONObject result) {

                            }

                            @Override
                            public void onSuccess(Bitmap result) {
                                searchRecipe.setImageBitmap(result);
                            }
                        });
                break;
        }
        return v;
    }
}
