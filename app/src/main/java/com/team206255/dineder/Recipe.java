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
        this.steps = new String[1];
        steps[0] = "loading";
        ingredients = new String[1];
        ingredients[0] = "loading";
        duration = 0;
        calorie = 0;
        fat = 0;
        protein = 0;
        carbs = 0;
        healthScore = 0;

        ImageProcessor.preLoad(pictureUrl);

        fullyLoaded = false;
    }

    public Recipe(int id, String name, String pictureUrl, int calorie)
    {
        this.id = id;
        this.name = name;
        this.pictureView = pictureUrl;
        this.steps = new String[1];
        steps[0] = "loading";
        ingredients = new String[1];
        ingredients[0] = "loading";
        duration = 0;
        this.calorie = calorie;
        fat = 0;
        protein = 0;
        carbs = 0;
        healthScore = 0;

        ImageProcessor.preLoad(pictureUrl);

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
        fat = 0;
        protein = 0;
        carbs = 0;
        healthScore = 0;

        ImageProcessor.preLoad(pictureUrl);

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
