package com.team206255.dineder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.team206255.dineder.InfoDefine.*;
/**
 * Created by walter on 2017-10-05.
 */

public class StringAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    String[] items;

    int layout;
    ListType type;

    public StringAdapter(Context c, int layout, String[] items, ListType type)
    {
        context = c;
        this.layout = layout;
        this.items = items;
        this.type = type;

        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = inflater.inflate(layout, null);
        if (type == ListType.INGREDIENT_BOX) {
            CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBox);
            checkBox.setText(items[i]);
            checkBox.setChecked(MainActivity.recipeFilter.ingredients[i]);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    MainActivity.recipeFilter.ingredients[i] = b;
                }
            });
        }
        else if (type == ListType.INGREDIENT_LIST) {
            TextView textView = (TextView) v.findViewById(R.id.textDetail);
            textView.setText(items[i]);
        }
        else if (type == ListType.STEPS){
            TextView textView = (TextView) v.findViewById(R.id.textDetail);
            textView.setText(i+1 + ". " + items[i]);
        }
        else if (type == ListType.CUISINE_BOX){
            CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBox);
            checkBox.setText(items[i]);
            checkBox.setChecked(MainActivity.recipeFilter.cuisine[i]);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (i == 0 && b)
                        MainActivity.recipeFilter.cuisine_selected_by_position(0);
                    else
                        MainActivity.recipeFilter.cuisine[i] = b;
                }
            });

        }
        return v;
    }
}
