package com.team206255.dineder;

import android.graphics.Bitmap;
import android.icu.text.LocaleDisplayNames;
import android.util.Log;

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

    public void getRecipe()
    {
        RandomRecipeGenerator.getJSONObject(new CallbackHelper() {
            @Override
            public void onSuccess(JSONObject result) {
                //parsing into recipe
                Log.d("result", result.toString());


                //--->I set it so whenever you clicked dislike, it will get one recipe from the api
                //--->and then it will be set inside the CalendarScreen
                //Recipe parsedRecipe = new Recipe(xxxxxxxxx);

                //--->using log to print out the result you want to test
                //--->the first Log.d shows you the whole thing for the recipe
                //--->tell me if you want to show more things (like i saw "serve" inside the api) in the Recipe
                //--->you can modify Recipe.java to store more information
                //--->the link for this request right now is random one recipe, idk if the json will change for other request
                //--->(I believe it will tbh, but we might not need to change to another kind of request)

                //--->use this function here after parsing the recipes, so that you won't mess up with the main feature
                //--->parsed Recipe should be found inside calendar screen
                //Singleton.getInstance().getCalendarStorage().addRecipe(new Date(), parsedRecipe, 0);
            }

            @Override
            public void onSuccess(Bitmap result) {
                //do nothing here
            }
        });
    }

    public Recipe getChoiceRecipe()
    {
        return showlist[0];
    }

    public Recipe getBackgroundRecipe() { return showlist[1]; }
}
