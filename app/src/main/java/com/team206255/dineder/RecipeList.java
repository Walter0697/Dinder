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

    public void removeAll()
    {
        lists.clear();
        addTime.clear();
    }

    public int getSize()
    {
        return lists.size();
    }

    public Recipe getRecipe(int position)
    {
        return lists.get(position);
    }

    public Date getDate(int position)
    {
        return addTime.get(position);
    }

    public String getDateToString(int position) { return dateFormat.format(getDate(position)); }
}
