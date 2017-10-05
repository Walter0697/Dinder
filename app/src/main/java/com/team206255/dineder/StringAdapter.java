package com.team206255.dineder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by walter on 2017-10-05.
 */

public class StringAdapter extends BaseAdapter {

    LayoutInflater inflater;
    String[] items;

    int layout;

    public StringAdapter(int layout)
    {
        this.layout = layout;
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
        return v;
    }
}
