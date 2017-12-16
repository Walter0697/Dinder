package com.team206255.dineder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by walter on 2017-10-12.
 */

public class CalendarStorage {
    static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    //hashmap to store the recipes
    //the values should always be the array of 4
    Map<String, Recipe[]> storage = new HashMap<>();

    //storing current date to show
    Date currentDate = new Date();

    public String dateToString()
    {
        DateFormat dateWithWeek = new SimpleDateFormat("EEE yyyy/MM/dd");
        return dateWithWeek.format(currentDate);
    }

    //add recipe into the selectedDate
    public void addRecipe(Date selectedDate, Recipe addRecipe, int index)
    {
        String selectedDay = dateFormat.format(selectedDate);
        if (storage.containsKey(selectedDay))
        {
            Recipe[] currentRecipe = storage.get(selectedDay);
            currentRecipe[index] = addRecipe;
            storage.put(selectedDay, currentRecipe);
        }
        //if not, put the new key
        else
        {
            Recipe[] currentRecipe = new Recipe[4];
            for (int i = 0; i < 4; i++)
                currentRecipe[i] = null;
            currentRecipe[index] = addRecipe;
            storage.put(selectedDay, currentRecipe);
        }
    }

    //remove recipe from the current date
    //remove can only be done inside calendar screen
    //therefore the current date should be valid
    public void removeRecipe(int index)
    {
        String selectedDay = dateFormat.format(currentDate);
        if (storage.containsKey(selectedDay))
        {
            Recipe[] currentRecipe = storage.get(selectedDay);
            currentRecipe[index] = null;
            storage.put(selectedDay, currentRecipe);
        }
    }

    //set current date to today, nextday and lastday
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

    //get current date recipes
    public Recipe[] getRecipe()
    {
        String selectedDay = dateFormat.format(currentDate);
        if (!storage.containsKey(selectedDay)) return null;
        return storage.get(selectedDay);
    }

    //get recipe according to index
    public Recipe getRecipe(int i)
    {
        //directly return if i is bigger than 4
        if (i >= 4) return null;
        //convert the date and find the corresponding string inside the map
        String selectedDay = dateFormat.format(currentDate);
        if (!storage.containsKey(selectedDay)) return null;

        Recipe[] current = getRecipe();
        return current[i];
    }
}
