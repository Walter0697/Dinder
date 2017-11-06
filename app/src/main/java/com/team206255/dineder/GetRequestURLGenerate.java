package com.team206255.dineder;

import android.content.Context;
import android.util.Log;

import java.util.Random;

/**
 * Created by walter on 2017-11-03.
 */

//complex one : "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/searchComplex?addRecipeInformation=true&cuisine=hongkong&fillIngredients=true&includeIngredients=onions%2Clettuce%2Ctomato&instructionsRequired=true&intolerances=peanut%2C+shellfish&limitLicense=true&maxCalories=1500&maxCarbs=100&maxFat=100&maxProtein=100&number=1&offset=0&query=0&ranking=1"
//random one : https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?limitLicense=false&number=1&tags=vegetarian%2Cdessert
//similar one : https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/156992/similar
//food information : https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/479101/information?includeNutrition=true

public class GetRequestURLGenerate {

    static Context mcontext;
    static Random rand = new Random();

    public static void setContact(Context contact)
    {
        mcontext = contact;
    }

    public static String getRandomURL()
    {
        String[] ingredients = randomElement(Singleton.getInstance().getRecipeFilter().ingredientToList(mcontext),
                                            Singleton.getInstance().getRecipeFilter().ingredientChance(),
                                            rand.nextInt(3) + 2);
        String ingre = combineString(ingredients);
        Log.d("result", "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?limitLicense=true&number=1&tags=" + ingre);
        return "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?limitLicense=true&number=1&tags=" + ingre;
    }

    public static String getComplexURL()
    {
        String[] ingredients = randomElement(Singleton.getInstance().getRecipeFilter().ingredientToList(mcontext),
                                            Singleton.getInstance().getRecipeFilter().ingredientChance(),
                                            rand.nextInt(3) + 2);
        String ingre = combineString(ingredients);
        String cuisine = Singleton.getInstance().getRecipeFilter().randomSelectedCuisine(mcontext);

        return "";
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

    private static String[] randomElement(String[] list, boolean[] chance, int num)
    {
        String[] output = new String[num];
        for (int i = 0; i < num; i++)
        {
            int index = rand.nextInt(output.length);
            if (chance[index])
            {
                if (rand.nextInt(100) < 50) {
                    output[i] = list[index];
                    for (int j = 0; j < i; j++)
                    {
                        if (output[i] == output[j])
                            i--;
                    }
                }
                else
                    i--;
            }
            else
            {
                if (rand.nextInt(100) < 10)
                {
                    output[i] = list[index];
                    for (int j = 0; j < i; j++)
                    {
                        if (output[i] == output[j])
                            i--;
                    }
                }
                else
                    i--;

            }
        }
        return output;
    }
}
