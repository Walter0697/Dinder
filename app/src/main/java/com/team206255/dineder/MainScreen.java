package com.team206255.dineder;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;

import java.util.Date;

public class MainScreen extends Fragment{

    private DrawerLayout drawer;
    private View view;

    //all widgets for the drawer
    //left drawer
    private NavigationView leftNavigationView;
    private View leftView;
    //right drawer
    private NavigationView rightNavigationView;
    private View rightView;

    //set up the handler here
    ListDrawerHandler listDrawerHandler;
    FilterDrawerHandler filterDrawerHandler;

    //setting space for drag and drop features
    Space likeArea;
    Space dislikeArea;
    Space loveArea;

    //getting display metrics
    private DisplayMetrics metrics;

    TextView testing;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstance) {
        view = inflater.inflate(R.layout.activity_main_screen, container, false);
        metrics = getContext().getResources().getDisplayMetrics();

        //getting the drawerLayout from the view
        drawer = (DrawerLayout) view.findViewById(R.id.main_fragment);

        //handle the tick and cross button
        ImageView tickButton = (ImageView) view.findViewById(R.id.tickButton);
        Bitmap tickImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.checked, 0.2f);
        tickButton.setImageBitmap(tickImage);
        tickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeLike();
            }
        });

        ImageView crossButton = (ImageView) view.findViewById(R.id.crossButton);
        Bitmap crossImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.unchecked, 0.2f);
        crossButton.setImageBitmap(crossImage);
        crossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeDislike();
            }
        });

        ImageView loveButton = (ImageView) view.findViewById(R.id.loveButton);
        Bitmap loveImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.heart, 0.2f);
        loveButton.setImageBitmap(loveImage);
        loveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeLove();
            }
        });

        //getting the navigation view from the view and setting up listener for them
        leftNavigationView = (NavigationView) view.findViewById(R.id.nav_view);
        rightNavigationView = (NavigationView) view.findViewById(R.id.right_nav_view);

        leftView = leftNavigationView.inflateHeaderView(R.layout.nav_header_main_screen);
        //getting the view object from the navigation view
        //leftView = leftNavigationView.getHeaderView(0);
        rightView = rightNavigationView.getHeaderView(0);

        //setup the button(image) and resize them, then setup their listeners
        ImageView listButton = (ImageView) view.findViewById(R.id.leftDrawer);
        Bitmap listImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.list, 0.12f);
        listButton.setImageBitmap(listImage);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
        ImageView filterButton = (ImageView) view.findViewById(R.id.rightDrawer);
        Bitmap filterImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.filter, 0.12f);
        filterButton.setImageBitmap(filterImage);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(rightNavigationView);
            }
        });

        //getting the recipe picture from the first recipe in the list
        //also set up the drag and drop listener for the food picture view
        ImageView foodView = (ImageView) view.findViewById(R.id.foodView);
        MainActivity.recipeChoice.getChoiceRecipe().setBigImage(getContext(), foodView);
        //Bitmap unsized = MainActivity.recipeChoice.getChoiceRecipe().getImage(getContext(), foodView);
        //Bitmap foodImage = ImageProcessor.scaleImage(metrics, getResources(), unsized, 0.9f);
        //foodView.setImageBitmap(foodImage);
        //set drag and touch listener
        foodView.setOnTouchListener(touchListener);
        foodView.setOnDragListener(dragListener);

        //getting the background recipe picture from the second recipe in the list
        //dont need to set up listener cuz it is just for the background
        ImageView backgroundView = (ImageView) view.findViewById(R.id.backgroundfoodView);
        MainActivity.recipeChoice.getBackgroundRecipe().setBigImage(getContext(), backgroundView);
        //Bitmap unsizedBackground = MainActivity.recipeChoice.getBackgroundRecipe().getImage(getContext(), backgroundView);
        //Bitmap foodImageBackground = ImageProcessor.scaleImage(metrics, getResources(), unsizedBackground, 0.9f);
        //backgroundView.setImageBitmap(foodImageBackground);

        //space for the dragging and dropping event
        dislikeArea = (Space) view.findViewById(R.id.dislikeSpace);
        dislikeArea.setOnDragListener(dragListener);
        likeArea = (Space) view.findViewById(R.id.likeSpace);
        likeArea.setOnDragListener(dragListener);
        loveArea = (Space) view.findViewById(R.id.loveSpace);
        loveArea.setOnDragListener(dragListener);
        //set the height and width to match parent for the space so that it can fit for every devices
        dislikeArea.getLayoutParams().height = metrics.heightPixels;
        likeArea.getLayoutParams().height = metrics.heightPixels;
        loveArea.getLayoutParams().width = metrics.widthPixels;

        //getting the detail icon to handle viewing recipe information for now
        //might change it to touch the recipe image in the future
        ImageView detailView = (ImageView) view.findViewById(R.id.detailIcon);
        final Bitmap detailImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.icon, 0.1f);
        detailView.setImageBitmap(detailImage);
        detailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to the recipe information screen
                Intent detailIntent = new Intent(getActivity().getApplicationContext(), RecipeInformation.class);
                detailIntent.putExtra("RECIPE", MainActivity.recipeChoice.getChoiceRecipe());
                startActivity(detailIntent);
            }
        });

        //setup both drawer and also their listener
        listDrawerHandler = new ListDrawerHandler(getActivity().getApplicationContext());
        listDrawerHandler.handleDrawerSetup(leftView);
        filterDrawerHandler = new FilterDrawerHandler(getActivity().getApplicationContext());
        filterDrawerHandler.handleDrawerSetup(rightView, MainActivity.recipeFilter);

        //set up the button for filter screen and recipe detail screen
        TextView advancedFilter = (TextView) rightView.findViewById(R.id.advancedButton);
        advancedFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent filterScreen = new Intent(getActivity().getApplicationContext(), FilterScreen.class);
                getActivity().startActivityForResult(filterScreen, InfoDefine.REQUEST_FOR_FILTER);
            }
        });

        //list view for liked drawer
        ListView likedListView = (ListView) leftView.findViewById(R.id.likedListView);
        likedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent detailIntent = new Intent(getActivity().getApplicationContext(), RecipeInformation.class);
                detailIntent.putExtra("RECIPE", MainActivity.likeList.getRecipe(i));
                startActivity(detailIntent);
            }
        });
        //disable scrolling for background but enable it for listview
        likedListView.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch(action)
                {
                    case MotionEvent.ACTION_DOWN:
                        view.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                view.onTouchEvent(motionEvent);
                return true;
            }
        });

        ScrollView filterView = (ScrollView) rightView.findViewById(R.id.filterScrollView);
        //disable scrolling for background but enable it for listview
        filterView.setOnTouchListener(new ScrollView.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch(action)
                {
                    case MotionEvent.ACTION_DOWN:
                        view.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                view.onTouchEvent(motionEvent);
                return true;
            }
        });

        //testing textview only
        testing = (TextView) view.findViewById(R.id.testing);

        return view;
    }

    //Drag and drop functionality
    //need both touch and drag listener to handle this
    View.OnTouchListener touchListener = new View.OnTouchListener(){

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int event = motionEvent.getAction();
            switch (event) {
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
                case DragEvent.ACTION_DROP:
                    if (getTouchPosition(view, dragEvent).x >= likeArea.getX())
                    {
                        swipeLike();
                    }
                    else if (getTouchPosition(view, dragEvent).x <= dislikeArea.getX() + dislikeArea.getWidth())
                    {
                        swipeDislike();
                    }
                    else if (getTouchPosition(view, dragEvent).y <= loveArea.getY() + loveArea.getHeight())
                    {
                        //swipeLove();
                    }
                    break;
            }
            return true;
        }
    };

    //getting the point of the finger(touch position)
    private static Point getTouchPosition(View item, DragEvent event){
        Rect rItem = new Rect();
        item.getGlobalVisibleRect(rItem);
        return new Point(rItem.left + Math.round(event.getX()),
                         rItem.right + Math.round(event.getY()));
    }
    //end of drag and drop functionality

    private void swipeLike()
    {
        testing.setText("Like");
        MainActivity.likeList.addRecipe(MainActivity.recipeChoice.getChoiceRecipe(), new Date());
        MainActivity.recipeChoice.addRecipe(RandomRecipeGenerator.getRandomRecipe());
        listDrawerHandler.updateLikedView();
        setFoodView();
    }

    private void swipeDislike()
    {
        testing.setText("Dislike");
        MainActivity.recipeChoice.addRecipe(RandomRecipeGenerator.getRandomRecipe());
        setFoodView();
    }

    private void swipeLove()
    {
        testing.setText("Love");
        MainActivity.recipeChoice.addRecipe(RandomRecipeGenerator.getRandomRecipe());
        setFoodView();
    }

    private void setFoodView()
    {

        ImageView foregroundView = (ImageView) view.findViewById(R.id.foodView);
        MainActivity.recipeChoice.getChoiceRecipe().setBigImage(getContext(), foregroundView);
        //Bitmap unsized = MainActivity.recipeChoice.getChoiceRecipe().getImage(getContext(), foregroundView);
        //Bitmap foodImage = ImageProcessor.scaleImage(metrics, getResources(), unsized, 0.9f);
        //foregroundView.setImageBitmap(foodImage);
        ImageView backgroundView = (ImageView) view.findViewById(R.id.backgroundfoodView);
        MainActivity.recipeChoice.getBackgroundRecipe().setBigImage(getContext(), backgroundView);
        //Bitmap backgroundUnsized = MainActivity.recipeChoice.getBackgroundRecipe().getImage(getContext(), backgroundView);
        //Bitmap backgroundFoodImage = ImageProcessor.scaleImage(metrics, getResources(), backgroundUnsized, 0.9f);
        //backgroundView.setImageBitmap(backgroundFoodImage);
    }
}
