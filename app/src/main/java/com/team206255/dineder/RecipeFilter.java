package com.team206255.dineder;

import android.content.Context;

import com.team206255.dineder.InfoDefine.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by walter on 2017-09-29.
 */

public class RecipeFilter implements Serializable{

    boolean[] cuisine = new boolean[Cuisine.values().length];
    boolean[] ingredients = new boolean[Ingredient.values().length];
    int difficulty;
    float duration;
    float calorie;

    ArrayList<String> userDefineIngredients = new ArrayList<>();

    public RecipeFilter()
    {
        cuisine_selected_by_position(0);
        ingredientsSelectAll();
        duration = InfoDefine.maxDuration;
        calorie = InfoDefine.maxCalorie;
        difficulty = 1;
    }

    //select all of the ingredients
    //later might be useful for button
    public void ingredientsSelectAll()
    {
        for (int i = 0; i < Ingredient.values().length; i++)
            ingredients[i] = true;
    }

    //select no ingredients
    //that shouldn't be useful lol
    public void ingredientsSelectNone()
    {
        for (int i = 0; i < Ingredient.values().length; i++)
            ingredients[i] = false;
    }

    //selected by spinner
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

    //return the numbers of selected cuisine
    public int number_of_selected_cuisine()
    {
        int num = 0;
        for (int i = 0; i < Cuisine.values().length; i++)
            if (cuisine[i])
                num++;
        return num;
    }

    //return the numbers of selected ingredients
    public int number_of_selected_ingredients()
    {
        int num = 0;
        for (int i = 0; i < Ingredient.values().length; i++)
            if (ingredients[i])
                num++;
        return num;
    }

    //return the first cuisine(mainly for spinner)
    public int get_first_selected_cuisine()
    {
        for (int i = 0; i < Cuisine.values().length; i++)
            if (cuisine[i])
                return i;
        return -1;
    }

    //function for user define ingredient
    public void addIngredient(String name)
    {
        userDefineIngredients.add(name);
    }

    public void removeIngredient(int n)
    {
        if (n >= userDefineIngredients.size()) return;
        userDefineIngredients.remove(n);
    }

    public String[] ingredientToList(Context context, int num)
    {
        String[] output = new String[num];
        String[] list = selectedIngredients(context);
        Random rand = new Random();
        for (int i = 0; i < num; i++)
        {
            int randnum = rand.nextInt(list.length + userDefineIngredients.size());
            if (randnum >= list.length)
                output[i] = userDefineIngredients.get(randnum - list.length);
            else
                output[i] = list[randnum];

            //check if duplicate
            for (int j = 0; j < i; j++)
            {
                if (output[i] == output[j]) {
                    i--;
                    break;
                }
            }
        }
        return output;
    }

    public String[] selectedIngredients(Context context)
    {
        String[] list = context.getResources().getStringArray(R.array.ingredient);
        String[] output = new String[number_of_selected_ingredients()];
        int j = 0;
        for (int i = 0; i < list.length; i++)
        {
            if (ingredients[i])
                output[j++] = list[i];
        }
        return output;
    }

    public String toString(String[] test)
    {
        String output = "";
        for (int i = 0; i < test.length; i++)
        {
            output += test[i] + ", ";
        }
        return output;
    }

    public void combineRecipe(RecipeFilter other)
    {
        //do some shits
        //later
    }

    public String cuisineToString(Context context)
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
