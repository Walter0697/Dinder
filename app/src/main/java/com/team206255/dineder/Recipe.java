package com.team206255.dineder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.team206255.dineder.InfoDefine.*;

import java.io.Serializable;

/**
 * Created by walter on 2017-10-01.
 */

public class Recipe implements Serializable{

    //image not for final
    String pictureView; //url of the recipe view
    String name;
    String[] steps;
    String[] ingredients;
    int difficulty;
    float duration;
    float calorie;

    Cuisine type;

    public Recipe(String name, String[] steps, String[] ingredients, int difficulty, float duration, float calorie)
    {
        pictureView = "https://spoonacular.com/cdn/ingredients_100x100/milk.jpg";
        this.name = name;
        this.steps = steps;
        this.ingredients = ingredients;
        this.difficulty = difficulty;
        this.duration = duration;
        this.calorie = calorie;
        this.type = Cuisine.HONGKONG;
    }

    //this is just sample
    public Recipe(){
        pictureView = "https://spoonacular.com/cdn/ingredients_100x100/milk.jpg";
        name = "Apple";
        steps = new String[1];
        steps[0] = "Just buy an apple";
        this.ingredients = new String[1];
        this.ingredients[0] = "apple";
        difficulty = 1;
        duration = 10.f;
        calorie = 10.f;
        type = Cuisine.HONGKONG;
    }

    public Recipe(int i)
    {
        steps = new String[1];
        steps[0] = "Just buy an apple";
        ingredients = new String[1];
        ingredients[0] = "apple";
        difficulty = 1;
        duration = 10.f;
        calorie = 10.f;
        type = Cuisine.INDIAN;
        if (i == 0)
        {
            pictureView = "https://spoonacular.com/cdn/ingredients_100x100/milk.jpg";
            name = "Milk";
        }
        else if (i == 1)
        {
            pictureView = "https://spoonacular.com/cdn/ingredients_100x100/almonds.jpg";
            name = "Almond";
        }
        else if (i == 2)
        {
            pictureView = "https://spoonacular.com/cdn/ingredients_100x100/almonds.jpg";
            name = "No reason but almond2";
        }
        else if (i == 3)
        {
            pictureView = "https://spoonacular.com/cdn/ingredients_100x100/cream-of-chicken-soup.jpg";
            name = "chicken soup";
        }
        else if (i == 4)
        {
            pictureView = "https://spoonacular.com/cdn/ingredients_100x100/green-onion.jpg";
            name = "Green Onion";
        }
    }

    public String getCuisine(Context context)
    {
        String[] allType = context.getResources().getStringArray(R.array.cuisine);
        return allType[type.ordinal()];
    }

    public void setFullImage(final Context context, final ImageView imageView)
    {
        final Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                bitmap = ImageProcessor.scaleImage(context.getResources().getDisplayMetrics(), context.getResources(), bitmap, 1.0f);
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Bitmap bitmap = ImageProcessor.scaleImage(context.getResources().getDisplayMetrics(), context.getResources(), R.drawable.loading, 0.1f);
                bitmap = ImageProcessor.getCroppedBitmap(bitmap);
                imageView.setImageBitmap(bitmap);
            }
        };
        Picasso.with(context).load(pictureView).into(target);
    }

    public void setImage(final Context context, final ImageView imageView, final float scale) {
        final Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                bitmap = ImageProcessor.scaleImage(context.getResources().getDisplayMetrics(), context.getResources(), bitmap, scale);
                bitmap = ImageProcessor.getCroppedBitmap(bitmap);
                bitmap = ImageProcessor.drawCircleBorder(bitmap);
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Bitmap bitmap = ImageProcessor.scaleImage(context.getResources().getDisplayMetrics(), context.getResources(), R.drawable.loading, 0.1f);
                bitmap = ImageProcessor.getCroppedBitmap(bitmap);
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Bitmap bitmap = ImageProcessor.scaleImage(context.getResources().getDisplayMetrics(), context.getResources(), R.drawable.loading, 0.1f);
                bitmap = ImageProcessor.getCroppedBitmap(bitmap);
                imageView.setImageBitmap(bitmap);
            }
        };
        Picasso.with(context).load(pictureView).into(target);
    }
}
