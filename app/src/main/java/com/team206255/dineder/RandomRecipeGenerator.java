package com.team206255.dineder;

import java.util.Random;

/**
 * Created by walter on 2017-10-03.
 */

public class RandomRecipeGenerator {
    static Random rand = new Random();

    public RandomRecipeGenerator() {}

    public static Recipe getRandomRecipe()
    {

        return new Recipe(rand.nextInt(5));
        //return JsonFactory.getRecipe();
    }
}
