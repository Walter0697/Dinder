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
    String pictureView;
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
        steps[0] = "1.Just buy an apple";
        difficulty = 1;
        duration = 10.f;
        calorie = 10.f;
        type = Cuisine.HONGKONG;
    }

    public Recipe(int i)
    {
        steps = new String[1];
        steps[0] = "1.Just buy an apple";
        difficulty = 1;
        duration = 10.f;
        calorie = 10.f;
        type = Cuisine.INDIAN;
        if (i == 0)
        {
            image = R.drawable.testing;
            pictureView = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Red_Apple.jpg/1200px-Red_Apple.jpg";
            name = "Apple";
        }
        else if (i == 1)
        {
            image = R.drawable.tester;
            pictureView = "https://cdn-images-1.medium.com/max/2000/1*-Yl16t7mr6dE9hKU-w4LGg.jpeg";
            name = "Banana";
        }
        else if (i == 2)
        {
            image = R.drawable.filter;
            pictureView = "https://www.organicfacts.net/wp-content/uploads/2013/06/Pineapple.jpg";
            name = "Filter";
        }
        else if (i == 3)
        {
            image = R.drawable.heart;
            pictureView = "http://www.staples.co.uk/content/images/product/GenesisExtraLarge/15/01/asset.291501.jpg";
            name = "heart";
        }
        else if (i == 4)
        {
            image = R.drawable.dinner;
            pictureView = "http://www.newmarket.ca/LivingHere/PublishingImages/Pages/Waste,%20Recycling%20and%20Organics/Garbage-collection-information/Open%20Top%20Garbage%20Can%20with%20Handles.jpg";
            name = "dinner";
        }
    }

    public String getCuisine(Context context)
    {
        String[] allType = context.getResources().getStringArray(R.array.cuisine);
        return allType[type.ordinal()];
    }

    public void setBigImage(final Context context, final ImageView imageView)
    {
        Picasso.with(context).load(pictureView).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                //bitmap = ImageProcessor.scaleImage(context.getResources().getDisplayMetrics(), context.getResources(), bitmap, 0.9f);
                //bitmap = ImageProcessor.getCroppedBitmap(bitmap);
                imageView.setImageBitmap(bitmap);

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                imageView.setImageDrawable(context.getDrawable(image));
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                imageView.setImageDrawable(context.getDrawable(image));
            }
        });
    }

}
