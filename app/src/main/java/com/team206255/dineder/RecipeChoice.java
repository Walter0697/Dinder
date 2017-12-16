package com.team206255.dineder;

import android.graphics.Bitmap;
import android.icu.text.LocaleDisplayNames;
import android.util.Log;

//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.Random;

/**
 * Created by walter on 2017-10-01.
 */

public class RecipeChoice {
    final int MAX_WAIT_CHOICE = 15;
    private int current;

    Recipe[] showlist;

    public RecipeChoice() {
        showlist = new Recipe[MAX_WAIT_CHOICE];
        for (int i = 0; i < MAX_WAIT_CHOICE; i++)
            addRecipe(RandomRecipeGenerator.getTutorial());
        current = 0;
    }

    public void initializeChoice()
    {
        for (int i = 0; i < MAX_WAIT_CHOICE - 1; i++) {
        //for (int i = 0; i < MAX_WAIT_CHOICE; i++) {
            generateRecipe();
        }
    }

    public void swipe() {
        if (current != MAX_WAIT_CHOICE - 1) current++;
        Log.d("current", Integer.toString(current));

        if (current > 0) generateRecipe();
    }

    public void addRecipe(Recipe recipe) {
        for (int i = 0; i < MAX_WAIT_CHOICE - 1; i++) {
            showlist[i] = showlist[i + 1];
        }
        showlist[MAX_WAIT_CHOICE - 1] = recipe;

        current--;
        Log.d("currentadd", Integer.toString(current));
        if (current < 0) current = 0;
        else if (current > 0) generateRecipe();

    }

    public void generateRecipe()
    {

        //addRecipe(RandomRecipeGenerator.getRandomRecipe());

        /*if (UserInformation.getInstance().getRecipeFilter().festivalFilter)
        {
            RandomRecipeGenerator.getChristmasRecipeAPI();
        }
        else{
            //addRecipe(RandomRecipeGenerator.getRandomRecipe());
            Random rand = new Random();
            int getType = rand.nextInt();
            if (getType >= 70)
            {
                RandomRecipeGenerator.getNutrientsRecipeAPI();
            }
            else if (getType >= 40)
            {
                RandomRecipeGenerator.getComplexRecipeAPI();
            }
            else
            {
                if (UserInformation.getInstance().getUserpreference().getRandomID() == -1)
                    RandomRecipeGenerator.getRandomRecipeAPI();
                else
                    RandomRecipeGenerator.getSimilarRecipeAPI();
            }
        }*/
        addRecipe(RandomRecipeGenerator.getRandomRecipe());
    }

    public Recipe getChoiceRecipe()
    {
        return showlist[current];
    }

    public Recipe getBackgroundRecipe()
    {
        if (current == MAX_WAIT_CHOICE - 1) return null;
        return showlist[current + 1];
    }
}
