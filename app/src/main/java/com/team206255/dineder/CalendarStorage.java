package com.team206255.dineder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by walter on 2017-10-12.
 */

public class CalendarStorage {
    static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    ArrayList<Recipe[]> calendar = new ArrayList<>();
    ArrayList<Date> recipeDate = new ArrayList<>();

    Date currentDate = new Date();

    public String dateToString()
    {
        DateFormat dateWithWeek = new SimpleDateFormat("EEE yyyy/MM/dd");
        return dateWithWeek.format(currentDate);
    }

    public void addRecipe(Date selectedDate, Recipe addRecipe, int index)
    {
        currentDate = selectedDate;
        if (getRecipePosition() != -1)
        {
            Recipe[] currentRecipe = calendar.get(getRecipePosition());
            currentRecipe[index] = addRecipe;
            calendar.set(getRecipePosition(), currentRecipe);
        }
        else
        {
            Recipe[] currentRecipe = new Recipe[4];
            for (int i = 0; i < 4; i++)
                currentRecipe[i] = null;
            currentRecipe[index] = addRecipe;
            calendar.add(currentRecipe);
            recipeDate.add(selectedDate);
        }
    }

    public void removeRecipe(int index)
    {
        if (getRecipePosition() != -1)
        {
            Recipe[] currentRecipe = calendar.get(getRecipePosition());
            currentRecipe[index] = null;
            calendar.set(getRecipePosition(), currentRecipe);
        }
    }

    public void today()
    {
        currentDate = new Date();
    }

    public void nextDay()
    {
        String dt = dateFormat.format(currentDate);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(dateFormat.parse(dt));
            cal.add(Calendar.DATE, 1);
            currentDate.setTime(cal.getTimeInMillis());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void lastDay()
    {
        String dt = dateFormat.format(currentDate);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(dateFormat.parse(dt));
            cal.add(Calendar.DATE, -1);
            currentDate.setTime(cal.getTimeInMillis());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Recipe[] getRecipe()
    {
        if (getRecipePosition() == -1) return null;
        return calendar.get(getRecipePosition());
    }

    public Recipe getRecipe(int i)
    {
        if (i >= 4) return null;
        Recipe[] current = getRecipe();
        if (current == null) return null;
        return current[i];
    }

    public int getRecipePosition()
    {
        for (int i = 0; i < recipeDate.size(); i++)
        {
            //cuz we just want to check the date but not the time
            if (dateFormat.format(recipeDate.get(i)).equals(dateFormat.format(currentDate)))
            {
                return i;
            }
        }
        return -1;
    }
}
