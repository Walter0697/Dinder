package com.team206255.dineder;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainScreen extends Fragment{

    DrawerLayout drawer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance)
    {
        View view = inflater.inflate(R.layout.activity_main_screen, container, false);
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        ImageProcessor imageProcessor = new ImageProcessor();

        //getting the drawerLayout from the view
        drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);

        //getting the navigation view from the view and setting up listener for them
        final NavigationView leftNavigationView = (NavigationView) view.findViewById(R.id.nav_view);
        leftNavigationView.setNavigationItemSelectedListener(new LeftDrawerListener());
        final NavigationView rightNavigationView = (NavigationView) view.findViewById(R.id.right_nav_view);
        rightNavigationView.setNavigationItemSelectedListener(new RightDrawerListener());

        //getting the view object from the navigation view
        View leftView = leftNavigationView.getHeaderView(0);
        View rightView = rightNavigationView.getHeaderView(0);

        //setup the button(image) and resize them, then setup their listeners
        ImageView listButton = (ImageView) view.findViewById(R.id.leftDrawer);
        Bitmap listImage = imageProcessor.scaleImage(metrics, getResources(), R.drawable.list, 0.12f);
        listButton.setImageBitmap(listImage);
        listButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                drawer.openDrawer(Gravity.LEFT);
            }
        });


        ImageView filterButton = (ImageView) view.findViewById(R.id.rightDrawer);
        Bitmap filterImage = imageProcessor.scaleImage(metrics, getResources(), R.drawable.filter, 0.12f);
        filterButton.setImageBitmap(filterImage);
        filterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                drawer.openDrawer(rightNavigationView);
            }
        });

        //handle the tick and cross button
        ImageView tickButton = (ImageView) view.findViewById(R.id.tickButton);
        Bitmap tickImage = imageProcessor.scaleImage(metrics, getResources(), R.drawable.checked, 0.2f);
        tickButton.setImageBitmap(tickImage);

        ImageView crossButton = (ImageView) view.findViewById(R.id.crossButton);
        Bitmap crossImage = imageProcessor.scaleImage(metrics, getResources(), R.drawable.unchecked, 0.2f);
        crossButton.setImageBitmap(crossImage);

        ImageView loveButton = (ImageView) view.findViewById(R.id.loveButton);
        Bitmap loveImage = imageProcessor.scaleImage(metrics, getResources(), R.drawable.heart, 0.2f);
        loveButton.setImageBitmap(loveImage);

        //set up the drag and drop listener for the food picture view
        ImageView foodView = (ImageView) view.findViewById(R.id.foodView);
        Bitmap foodImage = imageProcessor.scaleImage(metrics, getResources(), R.drawable.dinner, 0.9f);
        foodView.setImageBitmap(foodImage);
        foodView.setOnTouchListener(touchListener);
        foodView.setOnDragListener(dragListener);

        //setup drawer icon
        ImageView leftDrawerIcon = (ImageView) leftView.findViewById(R.id.drawerLeftIcon);
        Bitmap leftDrawerImage = imageProcessor.scaleImage(metrics, getResources(), R.drawable.list, 0.05f);
        leftDrawerIcon.setImageBitmap(leftDrawerImage);

        ImageView rightDrawerIcon = (ImageView) rightView.findViewById(R.id.drawerRightIcon);
        Bitmap rightDrawerImage = imageProcessor.scaleImage(metrics, getResources(), R.drawable.filter, 0.05f);
        rightDrawerIcon.setImageBitmap(rightDrawerImage);

        return view;
    }

    View.OnTouchListener touchListener = new View.OnTouchListener(){

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int event = motionEvent.getAction();
            switch(event)
            {
                case MotionEvent.ACTION_DOWN:
                    View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(view);
                    //dragging without original picture
                    dragShadowBuilder.getView().setAlpha(0);
                    view.startDragAndDrop(null, dragShadowBuilder, view, 0);
            }
            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            int event = dragEvent.getAction();
            switch(event)
            {
                case DragEvent.ACTION_DRAG_ENDED:
                    view.setAlpha(1);
                    view.setVisibility(View.VISIBLE);
                    break;
            }
            return true;
        }
    };

    public static Point getTouchPosition(View item, DragEvent event){
        Rect rItem = new Rect();
        item.getGlobalVisibleRect(rItem);
        return new Point(rItem.left + Math.round(event.getX()),
                         rItem.right + Math.round(event.getY()));
    }
}
