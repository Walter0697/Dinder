package com.team206255.dineder;

import android.content.Context;

import java.util.Random;

/**
 * Created by walter on 2017-11-03.
 */

//complex one : "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/searchComplex?addRecipeInformation=true&cuisine=chinese&fillIngredients=true&includeIngredients=tomato&instructionsRequired=true&intolerances=peanut%2C+shellfish&limitLicense=true&maxCalories=1500&maxCarbs=100&maxFat=100&maxProtein=100&number=1&offset=0&query=0&ranking=1"
//nutrients one : https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByNutrients?maxCalories=250&maxCarbs=100&maxFat=20&maxProtein=100&number=1&offset=0&random=true
//random one : https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?limitLicense=false&number=1&tags=vegetarian%2Cdessert
//similar one : https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/156992/similar
//food information : https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/479101/information?includeNutrition=true

//for search feature
//multiple requests : "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/searchComplex?addRecipeInformation=false&cuisine=chinese&excludeIngredients=mango&fillIngredients=false&includeIngredients=onions%2C+lettuce%2C+tomato&instructionsRequired=false&maxCalories=1500&maxCarbs=100&maxFat=100&maxProtein=100&number=5&offset=0&ranking=1"

public class GetRequestURLGenerate {

    static Context mcontext;
    static Random rand = new Random();

    public static void setContact(Context contact)
    {
        mcontext = contact;
    }

    public static String getRandomURL()
    {
        String[] ingredients = randomElement(UserInformation.getInstance().getRecipeFilter().ingredientToList(mcontext),
                                            UserInformation.getInstance().getRecipeFilter().ingredientChance(),
                                            1);
        String ingre = combineString(ingredients);
        return "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?limitLicense=true&number=1&tags=" + ingre;
    }

    public static String getChristmasURL()
    {
        return "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?limitLicense=true&number=1&tags=christmas";
    }

    public static String getSimilarURL()
    {
        int similarid = UserInformation.getInstance().getUserpreference().getRandomID();
        return "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/"+similarid+"/similar";
    }

    public static String getComplexURL()
    {
        int offset = rand.nextInt(30);

        int calories = (int) UserInformation.getInstance().getRecipeFilter().calorie;
        int carbs = (int) UserInformation.getInstance().getRecipeFilter().carbs;
        int fat = (int) UserInformation.getInstance().getRecipeFilter().fat;
        int protein = (int) UserInformation.getInstance().getRecipeFilter().protein;

        String output = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/searchComplex?addRecipeInformation=false&" +
                "cuisine=american" +
                "&fillIngredients=false" +
                "&instructionsRequired=false" +
                "&maxCalories=" + Integer.toString(calories) +
                "&maxCarbs=" + Integer.toString(carbs) +
                "&maxFat=" + Integer.toString(fat) +
                "&maxProtein=" + Integer.toString(protein) +
                "&number=1" +
                "&offset=" + Integer.toString(offset) +
                "&ranking=1";
        return output;

    }

    public static String getSearchURL(int offset)
    {
        String[] ingredients = randomElement(UserInformation.getInstance().getRecipeFilter().ingredientToList(mcontext),
                                UserInformation.getInstance().getRecipeFilter().ingredientChance(),
                                2);
        String ingre = combineString(ingredients);

        int calories = (int) UserInformation.getInstance().getRecipeFilter().calorie;
        int carbs = (int) UserInformation.getInstance().getRecipeFilter().carbs;
        int fat = (int) UserInformation.getInstance().getRecipeFilter().fat;
        int protein = (int) UserInformation.getInstance().getRecipeFilter().protein;

        String output = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/searchComplex?addRecipeInformation=false&" +
                "cuisine=american" +
                "&fillIngredients=false" +
                "&includeIngredients=" + ingre +
                "&instructionsRequired=false" +
                "&maxCalories=" + Integer.toString(calories) +
                "&maxCarbs=" + Integer.toString(carbs) +
                "&maxFat=" + Integer.toString(fat) +
                "&maxProtein=" + Integer.toString(protein) +
                "&number=5" +
                "&offset=" + Integer.toString(offset) +
                "&ranking=1";
        return output;
    }

    public static String getNutrientsURL()
    {
        int calories = (int) UserInformation.getInstance().getRecipeFilter().calorie;
        int carbs = (int) UserInformation.getInstance().getRecipeFilter().carbs;
        int fat = (int) UserInformation.getInstance().getRecipeFilter().fat;
        int protein = (int) UserInformation.getInstance().getRecipeFilter().protein;

        //https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByNutrients?maxCalories=250&maxCarbs=100&maxFat=100&maxProtein=100&number=1&offset=0&random=true
        String output = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByNutrients?" +
                "maxCalories=" + Integer.toString(calories) +
                //"&maxCarbs=" + Integer.toString(carbs) +
                "&maxFat=" + Integer.toString(fat) +
                //"&maxProtein=" + Integer.toString(protein) +
                "&number=1&offset=0&random=true";
        return output;
    }

    public static String getFoodinfoURL(int id)
    {
        return "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/"+Integer.toString(id)+"/information?includeNutrition=true";
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
            int index = rand.nextInt(list.length);
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
