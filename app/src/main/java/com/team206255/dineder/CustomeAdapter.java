package com.team206255.dineder;

import android.content.Context;
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
                Bitmap saveImage = ImageProcessor.scaleImage(metrics, context.getResources(), R.drawable.heart, 0.03f);
                saveButton.setImageBitmap(saveImage);

                ImageView removeButton = (ImageView) v.findViewById(R.id.removeButton);
                Bitmap removeImage = ImageProcessor.scaleImage(metrics, context.getResources(), R.drawable.unchecked, 0.03f);
                removeButton.setImageBitmap(removeImage);

                removeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.likeList.removeRecipe(i);
                        notifyDataSetChanged();
                    }
                });


                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        MainActivity.likeList.removeRecipe(i);
                        notifyDataSetChanged();
                    }
                });

                break;
            case SAVED_LIST:
                ImageView recipeImageView = (ImageView) v.findViewById(R.id.savedRecipeImage);
                items.getRecipe(i).setImage(context, recipeImageView, 0.23f);
                //Bitmap unsized = items.getRecipe(i).getImage(context, recipeImageView);
                //Bitmap recipeImage = ImageProcessor.scaleImage(metrics, context.getResources(), unsized, 0.23f);
                //recipeImageView.setImageBitmap(recipeImage);

                TextView savedRecipeName = (TextView) v.findViewById(R.id.savedRecipeName);
                savedRecipeName.setText(items.getRecipe(i).name);

                TextView savedRecipeCuisine = (TextView) v.findViewById(R.id.savedCuisine);
                savedRecipeCuisine.setText(items.getRecipe(i).getCuisine(context));

                TextView savedDate = (TextView) v.findViewById(R.id.savedDate);
                savedDate.setText(items.getDateToString(i));

                TextView savedDuration = (TextView) v.findViewById(R.id.savedDuration);
                savedDuration.setText("Duration: " + items.getRecipe(i).duration + " mins");

                TextView savedCalorie = (TextView) v.findViewById(R.id.savedCalorie);
                savedCalorie.setText("Calorie: " + items.getRecipe(i).calorie);

                RatingBar savedDifficulty = (RatingBar) v.findViewById(R.id.savedDifficulty);
                savedDifficulty.setRating(items.getRecipe(i).difficulty);

                ImageView savedRemoveButton = (ImageView) v.findViewById(R.id.savedRemoveButton);
                Bitmap savedRemoveImage = ImageProcessor.scaleImage(metrics, context.getResources(), R.drawable.unchecked, 0.05f);
                savedRemoveButton.setImageBitmap(savedRemoveImage);

                savedRemoveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //MainActivity.saveList.removeRecipe(i);
                        //notifyDataSetChanged();
                    }
                });

                break;
            case SEARCH_LIST:
                break;
        }
        return v;
    }
}
