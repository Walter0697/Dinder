package com.team206255.dineder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;

import com.team206255.dineder.InfoDefine.*;

import java.io.Serializable;

/**
 * Created by walter on 2017-10-01.
 */

public class Recipe implements Serializable{

    int image;
    //image not for final
    String pictureView;
    String name;
    String[] steps;
    int difficulty;
    float duration;
    float calorie;
    Cuisine type;

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
        pictureView = "https://cdn-images-1.medium.com/max/2000/1*-Yl16t7mr6dE9hKU-w4LGg.jpeg";

        steps = new String[1];
        steps[0] = "1.Just buy an apple";
        difficulty = 1;
        duration = 10.f;
        calorie = 10.f;
        type = Cuisine.INDIAN;
        if (i == 0)
        {
            image = R.drawable.testing;
            name = "Apple";
        }
        else if (i == 1)
        {
            image = R.drawable.tester;
            name = "Banana";
        }
        else if (i == 2)
        {
            image = R.drawable.filter;
            name = "Filter";
        }
        else if (i == 3)
        {
            image = R.drawable.heart;
            name = "heart";
        }
        else if (i == 4)
        {
            image = R.drawable.dinner;
            name = "dinner";
        }
    }

    public String getCuisine(Context context)
    {
        String[] allType = context.getResources().getStringArray(R.array.cuisine);
        return allType[type.ordinal()];
    }

    public Bitmap getImage(Resources res)
    {
        //change it to url later
        Bitmap fromResource = ImageProcessor.ResourceDrawableToBitmap(res, image);
        //later add border before returning
        return ImageProcessor.getCroppedBitmap(fromResource);
    }

}
