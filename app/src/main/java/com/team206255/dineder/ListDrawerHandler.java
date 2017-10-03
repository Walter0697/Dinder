package com.team206255.dineder;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

        //expend the drawer header to the end so that it can get rid of the menu
        LinearLayout linearLayout = (LinearLayout) headerView.findViewById(R.id.listHeader);
        LinearLayout.LayoutParams linear_params = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        linear_params.height = (int)(metrics.heightPixels);
        linearLayout.setLayoutParams(linear_params);

        //setting up the icon of the drawer
        ImageView drawerIcon = (ImageView) headerView.findViewById(R.id.drawerLeftIcon);
        Bitmap drawerImage = ImageProcessor.scaleImage(metrics, headerView.getResources(), R.drawable.list, 0.05f);
        drawerIcon.setImageBitmap(drawerImage);

    }
}
