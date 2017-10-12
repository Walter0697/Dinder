package com.team206255.dineder;

import android.content.Context;

import com.team206255.dineder.InfoDefine.*;

import java.io.Serializable;

/**
 * Created by walter on 2017-09-29.
 */

public class RecipeFilter implements Serializable{

    boolean[] cuisine = new boolean[Cuisine.values().length];
    boolean[] ingredients = new boolean[Ingredient.values().length];
    int difficulty;
    float duration;
    float calorie;

    Context context;

    public RecipeFilter()
    {
        cuisine_selected_by_position(0);
        ingredientsSelectAll();
        duration = InfoDefine.maxDuration;
        calorie = InfoDefine.maxCalorie;
        difficulty = 1;
    }

    void setUpContext(Context c)
    {
        context = c;
    }

    public void ingredientsSelectAll()
    {
        for (int i = 0; i < Ingredient.values().length; i++)
            ingredients[i] = true;
    }

    public void ingredientsSelectNone()
    {
        for (int i = 0; i < Ingredient.values().length; i++)
            ingredients[i] = false;
    }


    public void cuisine_selected_by_position(int position)
    {
        if (position == 0)
            for (int i = 0; i < Cuisine.values().length; i++)
                cuisine[i] = true;
        else {
            for (int i = 0; i < Cuisine.values().length; i++)
                cuisine[i] = false;
            cuisine[position] = true;
        }
    }

    public int number_of_selected_cuisine()
    {
        int num = 0;
        for (int i = 0; i < Cuisine.values().length; i++)
            if (cuisine[i])
                num++;
        return num;
    }

    public int get_first_selected_cuisine()
    {
        for (int i = 0; i < Cuisine.values().length; i++)
            if (cuisine[i])
                return i;
        return -1;
    }

    public String ingredientToString()
    {
        String result = "";
        String[] list = context.getResources().getStringArray(R.array.ingredient);

        for (int i = 0; i < list.length; i++)
        {
            if (ingredients[i])
                result += list[i] + ", ";
        }
         result = result.substring(0, result.length() - 2);
        return result;
    }

    public String cuisineToString()
    {
        String result = "";
        String[] list = context.getResources().getStringArray(R.array.cuisine);

        for (int i = 1; i < list.length; i++)
        {
            if (cuisine[i])
                result += list[i] + ", ";
        }
        result = result.substring(0, result.length() - 2);
        return result;
    }

    public String combineString(String a, String b, String middle)
    {
        return a + middle + b;
    }

    public String combineString(String a, String b)
    {
        return a + ", " + b;
    }
}
