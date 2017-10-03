package com.team206255.dineder;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by walter on 2017-10-01.
 */

public class RecipeList {
    ArrayList<Recipe> lists = new ArrayList<>();
    ArrayList<Date> addTime = new ArrayList<>();

    public void addRecipe(Recipe r, Date d)
    {
        lists.add(r);
        addTime.add(d);
    }

    public void removeRecipe(int position)
    {
        lists.remove(position);
        addTime.remove(position);
    }

    public Recipe getRecipe(int position)
    {
        return lists.get(position);
    }

    public Date getDate(int position)
    {
        return addTime.get(position);
    }
}
