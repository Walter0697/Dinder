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

    int image; //temp variable, don't have to care
    //image not for final
    String pictureView; //url of the recipe view
    String name;
    String[] steps;
    String[] ingredients;
    int difficulty;
    float duration;
    float calorie;
    int serves;

    Cuisine type;

    public Recipe(String name, String[] steps, String[] ingredients, int difficulty, float duration, float calorie)
    {
        this.image = R.drawable.testing; //don't care about this
        this.name = name;
        this.steps = steps;
        this.ingredients = ingredients;
        this.difficulty = difficulty;
        this.duration = duration;
        this.calorie = calorie;
    }

    //this is just sample
    public Recipe(){
        pictureView = "https://cdn-images-1.medium.com/max/2000/1*-Yl16t7mr6dE9hKU-w4LGg.jpeg";

        name = "Apple";
        steps = new String[1];
        steps[0] = "Just buy an apple";
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
            image = R.drawable.testing;
            pictureView = "https://www.nudefoodmovers.com.au/wp-content/uploads/2014/10/fruit-group.png";
            name = "Apple";
        }
        else if (i == 1)
        {
            image = R.drawable.tester;
            pictureView = "http://www.mccain.com/SiteCollectionImages/McCainCorporate/goodfood-products/McCain-Smiles-N-America.png";
            name = "Banana";
        }
        else if (i == 2)
        {
            image = R.drawable.filter;
            pictureView = "https://static.pexels.com/photos/70497/pexels-photo-70497.jpeg";
            name = "Filter";
        }
        else if (i == 3)
        {
            image = R.drawable.heart;
            pictureView = "http://www.2sfg.com/globalassets/corporate/home-page/2sisters-food-group-roast-banner5.jpg";
            name = "heart";
        }
        else if (i == 4)
        {
            image = R.drawable.dinner;
            pictureView = "http://food.fnr.sndimg.com/content/dam/images/food/fullset/2012/2/22/0/BX0110H_orange-baked-ham_s4x3.jpg.rend.hgtvcom.966.725.suffix/1371606081476.jpeg";
            name = "dinner";
        }
    }

    public String getCuisine(Context context)
    {
        String[] allType = context.getResources().getStringArray(R.array.cuisine);
        return allType[type.ordinal()];
    }

    public void setImage(final Context context, final ImageView imageView, final float scale)
    {
        Picasso.with(context).load(pictureView).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                bitmap = ImageProcessor.scaleImage(context.getResources().getDisplayMetrics(), context.getResources(), bitmap, scale);
                bitmap = ImageProcessor.getCroppedBitmap(bitmap);
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Bitmap bitmap = ImageProcessor.scaleImage(context.getResources().getDisplayMetrics(), context.getResources(), image, scale);
                bitmap = ImageProcessor.getCroppedBitmap(bitmap);
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Bitmap bitmap = ImageProcessor.scaleImage(context.getResources().getDisplayMetrics(), context.getResources(), image, scale);
                bitmap = ImageProcessor.getCroppedBitmap(bitmap);
                imageView.setImageBitmap(bitmap);
            }
        });
    }
}
