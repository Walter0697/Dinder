package com.team206255.dineder;

import android.content.Context;

import java.util.Random;
import java.util.ArrayList;

/**
 * Created by walter on 2017-10-03.
 */

public class RandomRecipeGenerator {
    Random rand = new Random();
    Context context;

    public RandomRecipeGenerator() {
    }

    public Recipe getRandomRecipe()
    {
        return new Recipe(rand.nextInt(5));
    }
}
