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

    Map<String, Recipe[]> storage = new HashMap<>();

    Date currentDate = new Date();

    public String dateToString()
    {
        DateFormat dateWithWeek = new SimpleDateFormat("EEE yyyy/MM/dd");
        return dateWithWeek.format(currentDate);
    }

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
        String selectedDay = dateFormat.format(currentDate);
        if (!storage.containsKey(selectedDay)) return null;
        return storage.get(selectedDay);
    }

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
