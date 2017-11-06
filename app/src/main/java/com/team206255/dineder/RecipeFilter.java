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
    float duration;
    float calorie;

    ArrayList<String> userDefineIngredients = new ArrayList<>();

    public RecipeFilter()
    {
        cuisine_selected_by_position(0);
        ingredientsSelectAll();
        duration = InfoDefine.maxDuration;
        calorie = InfoDefine.maxCalorie;
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

    public String[] ingredientToList(Context context)
    {
        String[] output = new String[ingredients.length + userDefineIngredients.size()];
        String[] list = context.getResources().getStringArray(R.array.ingredient);
        for (int i = 0; i < ingredients.length; i++)
            output[i] = list[i];
        for (int i = 0; i < userDefineIngredients.size(); i++)
            output[i + ingredients.length] = userDefineIngredients.get(i);
        return output;
    }

    public boolean[] ingredientChance()
    {
        boolean[] output = new boolean[ingredients.length + userDefineIngredients.size()];
        for (int i = 0 ;i < ingredients.length; i++)
            output[i] = ingredients[i];
        for (int i = 0; i < userDefineIngredients.size(); i++)
            output[i + ingredients.length] = true;
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

    public String[] selectedCuisine(Context context)
    {
        String[] list = context.getResources().getStringArray(R.array.cuisine);
        String[] output = new String[number_of_selected_cuisine()];
        int j = 0;
        for (int i = 0; i < list.length; i++)
        {
            if (cuisine[i])
                output[j++] = list[i];
        }
        return output;
    }

    public String randomSelectedCuisine(Context context)
    {
        String[] list = selectedCuisine(context);
        Random rand = new Random();
        return list[rand.nextInt(list.length)];
    }

    public void combineRecipe(RecipeFilter other)
    {
        //do some shits
        //later
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
