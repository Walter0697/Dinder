package com.team206255.dineder;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.team206255.dineder.InfoDefine.*;

/**
 * Created by walter on 2017-09-29.
 */

public class ListDrawerHandler {

    Context context;
    Activity activity;

    ListView likedView;
    CustomeAdapter customeAdapter;

    public ListDrawerHandler(Context c, Activity a)
    {
        this.context = c;
        this.activity = a;
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
        Bitmap drawerImage = ImageProcessor.scaleImage(metrics, headerView.getResources(), R.drawable.listdrawer, 0.1f);
        drawerIcon.setImageBitmap(drawerImage);

        //setting up the list view
        customeAdapter = new CustomeAdapter(context, UserInformation.getInstance().getRecipeList(), R.layout.liked_list_detail, ListType.LIKED_LIST, activity);
        likedView = (ListView) headerView.findViewById(R.id.likedListView);
        likedView.setAdapter(customeAdapter);
    }

    public void updateLikedView()
    {
        likedView.invalidate();
        ((BaseAdapter)likedView.getAdapter()).notifyDataSetChanged();
    }
}
