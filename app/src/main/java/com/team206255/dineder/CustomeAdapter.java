package com.team206255.dineder;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.team206255.dineder.InfoDefine.*;

/**
 * Created by walter on 2017-10-03.
 */

//create a custome adapter that will have the information for all listview we have in this app
//including liked list, saved list, and searched list

public class CustomeAdapter extends BaseAdapter {

    Context context;
    DisplayMetrics metrics;
    LayoutInflater inflater;
    RecipeList items;

    //temp variable for button click
    int j;

    int layout;
    ListType type;

    public CustomeAdapter(Context c, RecipeList items, int layout, ListType type)
    {
        this.type = type;
        this.layout = layout;
        this.items = items;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = c;
        metrics = c.getResources().getDisplayMetrics();
    }

    @Override
    public int getCount() {
        return items.getSize();
    }

    @Override
    public Object getItem(int i) {
        return items.getRecipe(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = inflater.inflate(layout, null);
        switch(type)
        {
            case LIKED_LIST:
                TextView recipeName = (TextView) v.findViewById(R.id.likedRecipeName);
                recipeName.setText(items.getRecipe(i).name);

                TextView addedTime = (TextView) v.findViewById(R.id.likedRecipeAddedTime);
                addedTime.setText(items.getDateToString(i));

                ImageView saveButton = (ImageView) v.findViewById(R.id.saveButton);
                Bitmap saveImage = ImageProcessor.scaleImage(metrics, context.getResources(), R.drawable.heart, 0.03f);
                saveButton.setImageBitmap(saveImage);

                ImageView removeButton = (ImageView) v.findViewById(R.id.removeButton);
                Bitmap removeImage = ImageProcessor.scaleImage(metrics, context.getResources(), R.drawable.unchecked, 0.03f);
                removeButton.setImageBitmap(removeImage);

                removeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.likeList.removeRecipe(i);
                        notifyDataSetChanged();
                    }
                });

                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.saveList.addRecipe(MainActivity.likeList.getRecipe(i), MainActivity.likeList.getDate(i));
                        MainActivity.likeList.removeRecipe(i);
                        notifyDataSetChanged();
                    }
                });

                break;
            case SAVED_LIST:
                break;
            case SEARCH_LIST:
                break;
        }
        return v;
    }
}
