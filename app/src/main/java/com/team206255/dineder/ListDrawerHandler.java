package com.team206255.dineder;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by walter on 2017-09-29.
 */

public class ListDrawerHandler {

    Context context;

    public ListDrawerHandler(Context c)
    {
        this.context = c;
    }

    public void handleDrawerSetup(View headerView)
    {
        DisplayMetrics metrics = headerView.getResources().getDisplayMetrics();

        //setting up the icon of the drawer
        ImageView drawerIcon = (ImageView) headerView.findViewById(R.id.drawerLeftIcon);
        Bitmap drawerImage = ImageProcessor.scaleImage(metrics, headerView.getResources(), R.drawable.list, 0.05f);
        drawerIcon.setImageBitmap(drawerImage);

    }
}
