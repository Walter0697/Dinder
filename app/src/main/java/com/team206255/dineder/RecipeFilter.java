package com.team206255.dineder;

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

    public RecipeFilter()
    {
        cuisine_selected_by_spinner(0);
        ingredientsSelectAll();
        duration = InfoDefine.maxDuration;
        calorie = InfoDefine.maxCalorie;
        difficulty = 1;
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


    public void cuisine_selected_by_spinner(int position)
    {
        for (int i = 0; i < Cuisine.values().length; i++)
            cuisine[i] = false;
        cuisine[position] = true;
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

    public String toString()
    {
        return Integer.toString((int)duration);
    }
}
