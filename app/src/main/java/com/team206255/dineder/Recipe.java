package com.team206255.dineder;

import android.content.Context;

import com.team206255.dineder.InfoDefine.*;

import java.io.Serializable;

/**
 * Created by walter on 2017-10-01.
 */

public class Recipe implements Serializable{

    int id;   //id according to the api

    String pictureView; //url of the recipe view
    String name;
    String[] steps;
    String[] ingredients;
    int difficulty;
    float duration;
    float calorie;
    Cuisine type;

    float fat;
    float protein;
    float carbs;
    int healthScore;

    boolean fullyLoaded;

    public Recipe(int id, String name, String pictureUrl)
    {
        this.id = id;
        this.name = name;
        this.pictureView = pictureUrl;
        fullyLoaded = false;
    }

    public Recipe(int id, String name, String pictureUrl, String[] steps, String[] ingredients, int difficulty, float duration, float calorie)
    {
        this.id = id;
        pictureView = pictureUrl;
        this.name = name;
        this.steps = steps;
        this.ingredients = ingredients;
        this.difficulty = difficulty;
        this.duration = duration;
        this.calorie = calorie;
        this.type = Cuisine.HONGKONG;
        fullyLoaded = true;
    }

    //this should be the recipe information when the recipe isn't passing thought the activity correctly
    //picture should be something like error picture
    public Recipe(){
        fullyLoaded = true;
        pictureView = "";
        name = "No Recipe";
        steps = new String[2];
        steps[0] = "Opps, did you set something wrong?";
        steps[1] = "We cannot find any recipe here!";
        this.ingredients = new String[1];
        this.ingredients[0] = "Unknown";
        difficulty = 1;
        duration = 10.f;
        calorie = 10.f;
        type = Cuisine.ALL;
    }

    public Recipe(int i)
    {
        fullyLoaded = true;
        steps = new String[1];
        steps[0] = "Just buy an apple";
        ingredients = new String[1];
        ingredients[0] = "apple";
        difficulty = 1;
        duration = 10.f;
        calorie = 10.f;
        type = Cuisine.INDIAN;
        if (i == 0)
        {
            pictureView = "https://spoonacular.com/recipeImages/Ramen-Noodle-Coleslaw-556177.jpg";
            name = "Milk";
        }
        else if (i == 1)
        {
            pictureView = "https://spoonacular.com/recipeImages/Grandmas-Apple-Crisp-645152.jpg";
            name = "Almond";
        }
        else if (i == 2)
        {
            pictureView = "https://spoonacular.com/recipeImages/Quick-Apple-Ginger-Pie-657563.jpg";
            name = "No reason but almond2";
        }
        else if (i == 3)
        {
            pictureView = "https://spoonacular.com/recipeImages/Cinnamon-Sugar-Fried-Apples-639487.jpg";
            name = "chicken soup";
        }
        else if (i == 4)
        {
            pictureView = "https://spoonacular.com/recipeImages/Fresh-Apple-Cake-With-Caramel-Sauce-643426.jpg";
            name = "Green Onion";
        }
    }

    public void retrieveInformation(String[] ingredients, String[] steps, float duration, float calorie, float fat, float protein, float carbs, int healthScore)
    {
        this.ingredients = ingredients;
        this.steps = steps;
        this.duration = duration;
        this.calorie = calorie;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
        this.healthScore = healthScore;
        fullyLoaded = true;
    }

    public String getCuisine(Context context)
    {
        String[] allType = context.getResources().getStringArray(R.array.cuisine);
        return allType[type.ordinal()];
    }
}
