package com.team206255.dineder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by walter on 2017-10-01.
 */

public class RecipeList {
    static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    ArrayList<Recipe> lists = new ArrayList<>();
    ArrayList<Date> addTime = new ArrayList<>();

    //adding recipe inside recipe list
    public void addRecipe(Recipe r, Date d)
    {
        lists.add(r);
        addTime.add(d);
    }

    //removing recipe inside recipe list
    public void removeRecipe(int position)
    {
        lists.remove(position);
        addTime.remove(position);
    }

    //removing all recipes inside the list
    public void removeAll()
    {
        lists.clear();
        addTime.clear();
    }

    //getting the size of the list
    public int getSize()
    {
        return lists.size();
    }

    //getting the recipe in that position
    public Recipe getRecipe(int position)
    {
        return lists.get(position);
    }

    //getting the date that add this recipe
    public Date getDate(int position)
    {
        return addTime.get(position);
    }

    //convert date to string
    public String getDateToString(int position) { return dateFormat.format(getDate(position)); }
}
