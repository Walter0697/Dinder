package com.team206255.dineder;

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

    private Singleton() {
        //everything that need to be stored in this app
        randomRecipeGenerator = new RandomRecipeGenerator();
        recipeChoice = new RecipeChoice();
        recipeFilter = new RecipeFilter();
        recipeList = new RecipeList();
        calendarStorage = new CalendarStorage();
        for (int i = 0; i < recipeChoice.MAX_WAIT_CHOICE; i++)
        {
            recipeChoice.addRecipe(randomRecipeGenerator.getRandomRecipe());
        }
    }

    public static Singleton getInstance()
    {
        if (instance == null) instance = new Singleton();
        return instance;
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
