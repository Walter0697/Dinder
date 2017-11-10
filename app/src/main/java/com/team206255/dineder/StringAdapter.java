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

import java.util.ArrayList;

/**
 * Created by walter on 2017-10-05.
 */

public class StringAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    String[] items;
    ArrayList<String> items2 = null;

    int layout;
    ListType type;

    public StringAdapter(Context c, int layout, String[] items, ListType type)
    {
        context = c;
        this.layout = layout;
        this.items = items;
        this.type = type;

        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (type == ListType.INGREDIENT_BOX)
            items2 = UserInformation.getInstance().getRecipeFilter().userDefineIngredients;
    }

    @Override
    public int getCount() {
        if (items2 != null)
            return items.length + items2.size();
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        if (i >= items.length) return items2.get(i-items.length);
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
            if (i >= items.length)
            {
                checkBox.setText(items2.get(i-items.length));
                checkBox.setChecked(true);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        UserInformation.getInstance().getRecipeFilter().removeIngredient(i-items.length);
                        notifyDataSetChanged();
                    }
                });
            }
            else {
                checkBox.setText(items[i]);
                checkBox.setChecked(UserInformation.getInstance().getRecipeFilter().ingredients[i]);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        UserInformation.getInstance().getRecipeFilter().ingredients[i] = b;
                    }
                });
            }
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
            checkBox.setChecked(UserInformation.getInstance().getRecipeFilter().cuisine[i]);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (i == 0 && b)
                        UserInformation.getInstance().getRecipeFilter().cuisine_selected_by_position(0);
                    else
                        UserInformation.getInstance().getRecipeFilter().cuisine[i] = b;
                }
            });

        }
        return v;
    }
}
