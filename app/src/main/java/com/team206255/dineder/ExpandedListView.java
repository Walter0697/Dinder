package com.team206255.dineder;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by walter on 2017-10-06.
 */

//used for expand the list view
//code from https://stackoverflow.com/questions/2312683/calculate-the-size-of-a-list-view-or-how-to-tell-it-to-fully-expand

public class ExpandedListView extends ListView {

    private android.view.ViewGroup.LayoutParams params;
    private int old_count = 0;

    public ExpandedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getCount() != old_count) {
            old_count = getCount();
            params = getLayoutParams();
            params.height = getCount() * (old_count > 0 ? getChildAt(0).getHeight() : 0);
            setLayoutParams(params);
        }

        super.onDraw(canvas);
    }

}