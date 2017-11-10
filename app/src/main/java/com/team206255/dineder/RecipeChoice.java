package com.team206255.dineder;

import android.graphics.Bitmap;
import android.icu.text.LocaleDisplayNames;
import android.util.Log;

//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by walter on 2017-10-01.
 */

public class RecipeChoice {
    final int MAX_WAIT_CHOICE = 5;

    Recipe[] showlist;

    public RecipeChoice()
    {
        showlist = new Recipe[MAX_WAIT_CHOICE];
        for (int i = 0; i < MAX_WAIT_CHOICE; i++)
        {
            addRecipe(RandomRecipeGenerator.getRandomRecipe());
        }
    }

    public void addRecipe(Recipe recipe) {
        for (int i = 0; i < MAX_WAIT_CHOICE - 1; i++)
        {
            showlist[i] = showlist[i+1];
        }
        showlist[MAX_WAIT_CHOICE - 1] = recipe;
    }

    public void getRecipeTest()
    {
        RandomRecipeGenerator.getNutrientsRecipeAPI();
        //RandomRecipeGenerator.getRandomRecipeAPI();
    }

    public Recipe getChoiceRecipe()
    {
        return showlist[0];
    }

    public Recipe getBackgroundRecipe() { return showlist[1]; }
}
