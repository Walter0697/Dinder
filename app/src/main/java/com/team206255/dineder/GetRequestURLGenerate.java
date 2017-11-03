package com.team206255.dineder;

import android.content.Context;

import java.util.Random;

/**
 * Created by walter on 2017-11-03.
 */

//complex one : https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/searchComplex?addRecipeInformation=false&cuisine=hongkong&fillIngredients=false&includeIngredients=onions%2C+lettuce%2C+tomato&instructionsRequired=false&intolerances=peanut%2C+shellfish&limitLicense=false&maxCalories=1500&maxCarbs=100&maxFat=100&maxProtein=100&minCalories=0&minCarbs=0&minFat=0&minProtein=0&number=1&offset=0&query=0&ranking=1&type=main+course
//random one : https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?limitLicense=false&number=1&tags=vegetarian%2Cdessert
//similar one : https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/156992/similar
//food information : https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/479101/information?includeNutrition=false

public class GetRequestURLGenerate {

    static Context mcontext;
    static Random rand = new Random();

    public static void setContact(Context contact)
    {
        mcontext = contact;
    }

    public static String getRandomURL()
    {
        String ingredients = combineString(Singleton.getInstance().getRecipeFilter().ingredientToList(mcontext, rand.nextInt(3) + 2));
        return "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?limitLicense=true&number=1&tags=" + ingredients;
    }

    private static String combineString(String[] list)
    {
        String output = "";
        for (int i = 0; i < list.length; i++)
        {
            output += list[i] + "%2C";
        }
        output = output.substring(0, output.length() - 3);
        return output;
    }
}
