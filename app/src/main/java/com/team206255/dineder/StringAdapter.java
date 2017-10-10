package com.team206255.dineder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = inflater.inflate(layout, null);
        CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBox);
        checkBox.setText(items[i]);
        return v;
    }
}
