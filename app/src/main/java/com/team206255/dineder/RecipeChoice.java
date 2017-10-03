package com.team206255.dineder;

/**
 * Created by walter on 2017-10-01.
 */

public class RecipeChoice {
    final int MAX_WAIT_CHOICE = 5;

    Recipe[] showlist;
    int currentNumber;

    public RecipeChoice()
    {
        showlist = new Recipe[MAX_WAIT_CHOICE];
        currentNumber = 0;
    }

    public void addRecipe(Recipe recipe) {
        if (currentNumber != MAX_WAIT_CHOICE - 1){
            showlist[currentNumber++] = recipe;
        }
        else {
            for (int i = 0; i < MAX_WAIT_CHOICE - 1; i++)
                showlist[i] = showlist[i + 1];
            showlist[MAX_WAIT_CHOICE - 1] = recipe;
        }
    }

    public Recipe getChoiceRecipe()
    {
        return showlist[0];
    }

    public Recipe getBackgroundRecipe()
    {
        return showlist[1];
    }
}
