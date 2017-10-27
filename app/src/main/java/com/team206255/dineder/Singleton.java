package com.team206255.dineder;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by walter on 2017-10-22.
 */

public class Singleton {
    private static Singleton instance = null;

    private RandomRecipeGenerator randomRecipeGenerator;
    private RecipeChoice recipeChoice;
    private RecipeFilter recipeFilter;
    private RecipeList recipeList;
    private CalendarStorage calendarStorage;

    private SharedPreferences sharedPreferences;

    private Singleton() {
        //everything that need to be stored in this app
        randomRecipeGenerator = new RandomRecipeGenerator();
    }

    public static Singleton getInstance()
    {
        if (instance == null) instance = new Singleton();
        return instance;
    }

    //setting up the shared preference from the database if any
    public void setSharedPreferences(Context context)
    {
        Gson gson = new Gson();
        sharedPreferences = context.getSharedPreferences("com.team206255.dineder", Context.MODE_PRIVATE);
        //check for recipe list
        if (sharedPreferences.contains("recipeList"))
        {
            String listJson = sharedPreferences.getString("recipeList", "");
            recipeList = gson.fromJson(listJson, RecipeList.class);
        }
        else
        {
            recipeList = new RecipeList();
            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
            String listJson = gson.toJson(recipeList);
            prefsEditor.putString("recipeList", listJson);
            prefsEditor.commit();
        }

        if (sharedPreferences.contains("calendarStorage"))
        {
            String storageJson = sharedPreferences.getString("calendarStorage", "");
            calendarStorage = gson.fromJson(storageJson, CalendarStorage.class);
        }
        else
        {
            calendarStorage = new CalendarStorage();
            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
            String storageJson = gson.toJson(calendarStorage);
            prefsEditor.putString("calendarStorage", storageJson);
            prefsEditor.commit();
        }

        if (sharedPreferences.contains("recipeChoice"))
        {
            String choiceJson = sharedPreferences.getString("recipeChoice", "");
            recipeChoice = gson.fromJson(choiceJson, RecipeChoice.class);
        }
        else
        {
            recipeChoice = new RecipeChoice();
            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
            String choiceJson = gson.toJson(recipeChoice);
            prefsEditor.putString("recipeChoice", choiceJson);
            prefsEditor.commit();
        }

        recipeFilter = new RecipeFilter();
    }

    //updating the shared preference
    public void updateSharedPreference()
    {
        Gson gson = new Gson();
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

        String listJson = gson.toJson(recipeList);
        prefsEditor.putString("recipeList", listJson);

        String storageJson = gson.toJson(calendarStorage);
        prefsEditor.putString("calendarStorage", storageJson);

        String choiceJson = gson.toJson(recipeChoice);
        prefsEditor.putString("recipeChoice", choiceJson);

        prefsEditor.commit();
    }

    //resetting the shared preference
    public void resetSharedPreference()
    {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.clear();
        prefsEditor.commit();

        recipeChoice = new RecipeChoice();
        recipeFilter = new RecipeFilter();
        calendarStorage = new CalendarStorage();
        recipeList = new RecipeList();

        updateSharedPreference();
    }

    public RecipeChoice getRecipeChoice()
    {
        return recipeChoice;
    }

    public RecipeFilter getRecipeFilter()
    {
        return recipeFilter;
    }

    public RecipeList getRecipeList()
    {
        return recipeList;
    }

    public CalendarStorage getCalendarStorage()
    {
        return calendarStorage;
    }

    public RandomRecipeGenerator getRandomRecipeGenerator() { return randomRecipeGenerator; }

    public void setRecipeChoice(RecipeChoice recipeChoice) { this.recipeChoice = recipeChoice; }

    public void setRecipeFilter(RecipeFilter recipeFilter) { this.recipeFilter = recipeFilter; }

    public void setRecipeList(RecipeList recipeList) { this.recipeList = recipeList; }

    public void setCalendarStorage(CalendarStorage calendarStorage) { this.calendarStorage = calendarStorage; }

    public void setRandomRecipeGenerator(RandomRecipeGenerator randomRecipeGenerator) { this.randomRecipeGenerator = randomRecipeGenerator; }
}
