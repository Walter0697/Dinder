package com.team206255.dineder;

import android.content.ClipData;
import android.content.ClipDescription;
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
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.Date;
import java.util.Random;

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

    //drag container for the non-transparent drag shader
    DragContainer dragContainer;
    private static final String IMAGEVIEW_TAG = "icon bitmap";

    private ImageView foodView;
    Animation animation;

    //getting display metrics
    private DisplayMetrics metrics;

    TextView testing;
    Random rand = new Random();

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
        Bitmap loveImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.savebutton, 0.25f);
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
        rightView = rightNavigationView.getHeaderView(0);

        //setup the button(image) and resize them, then setup their listeners
        ImageView listButton = (ImageView) view.findViewById(R.id.leftDrawer);
        Bitmap listImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.halloweenlist, 0.12f);
        listButton.setImageBitmap(listImage);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
        final ImageView filterButton = (ImageView) view.findViewById(R.id.rightDrawer);
        Bitmap filterImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.filterdrawer, 0.12f);
        filterButton.setImageBitmap(filterImage);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(rightNavigationView);
            }
        });

        //getting the recipe picture from the first recipe in the list
        //also set up the drag and drop listener for the food picture view
        foodView = (ImageView) view.findViewById(R.id.foodView);
        ImageProcessor.setURLImage(getContext(), Singleton.getInstance().getRecipeChoice().getChoiceRecipe().pictureView, 0.95f,
                new CallbackHelper() {
                    @Override
                    public void onSuccess(JSONObject result) {

                    }

                    @Override
                    public void onSuccess(Bitmap result) {
                        foodView.setImageBitmap(result);
                    }
                });

        //set drag and touch listener
        dragContainer = (DragContainer) view.findViewById(R.id.root);
        ViewGroup.LayoutParams layoutParams = dragContainer.getLayoutParams();
        layoutParams.height = metrics.heightPixels;
        layoutParams.width = metrics.widthPixels;
        dragContainer.setLayoutParams(layoutParams);

        foodView.setTag(IMAGEVIEW_TAG);
        foodView.setOnTouchListener(touchListener);
        foodView.setOnDragListener(dragListener);


        //getting the background recipe picture from the second recipe in the list
        //dont need to set up listener cuz it is just for the background
        final ImageView backgroundView = (ImageView) view.findViewById(R.id.backgroundfoodView);
        ImageProcessor.setURLImage(getContext(), Singleton.getInstance().getRecipeChoice().getBackgroundRecipe().pictureView, 0.95f,
                new CallbackHelper() {
                    @Override
                    public void onSuccess(JSONObject result) {

                    }

                    @Override
                    public void onSuccess(Bitmap result) {
                        backgroundView.setImageBitmap(result);
                    }
                });
        final ImageView background2View = (ImageView) view.findViewById(R.id.background2foodView);
        ImageProcessor.setURLImage(getContext(), Singleton.getInstance().getRecipeChoice().showlist[2].pictureView, 0.95f,
                new CallbackHelper() {
                    @Override
                    public void onSuccess(JSONObject result) {

                    }

                    @Override
                    public void onSuccess(Bitmap result) {
                        background2View.setImageBitmap(result);
                    }
                });

        //getting the detail icon to handle viewing recipe information for now
        //might change it to touch the recipe image in the future
        ImageView detailView = (ImageView) view.findViewById(R.id.detailIcon);
        final Bitmap detailImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.information, 0.1f);
        detailView.setImageBitmap(detailImage);
        detailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to the recipe information screen
                Intent detailIntent = new Intent(getActivity().getApplicationContext(), RecipeInformation.class);
                detailIntent.putExtra("RECIPE", Singleton.getInstance().getRecipeChoice().getChoiceRecipe());
                startActivity(detailIntent);
            }
        });

        //setup both drawer and also their listener
        listDrawerHandler = new ListDrawerHandler(getActivity().getApplicationContext(), getActivity());
        listDrawerHandler.handleDrawerSetup(leftView);
        filterDrawerHandler = new FilterDrawerHandler(getActivity().getApplicationContext());
        filterDrawerHandler.handleDrawerSetup(rightView);

        //set up the button for filter screen and recipe detail screen
        TextView advancedFilter = (TextView) rightView.findViewById(R.id.advancedButton);
        advancedFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent filterScreen = new Intent(getActivity().getApplicationContext(), FilterScreen.class);
                filterScreen.putExtra("RECIPEFILTER", Singleton.getInstance().getRecipeFilter());
                getActivity().startActivityForResult(filterScreen, InfoDefine.REQUEST_FOR_FILTER);
            }
        });

        //list view for liked drawer
        ListView likedListView = (ListView) leftView.findViewById(R.id.likedListView);
        likedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent detailIntent = new Intent(getActivity().getApplicationContext(), RecipeInformation.class);
                detailIntent.putExtra("RECIPE", Singleton.getInstance().getRecipeList().getRecipe(i));
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

        //disable scrollbar from drawer
        leftView.setVerticalScrollBarEnabled(false);
        rightView.setVerticalScrollBarEnabled(false);

        //icon of the app
        ImageView mainIcon = (ImageView) view.findViewById(R.id.mainIcon);
        Bitmap mainImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.dinderhalloween, 0.4f);
        mainIcon.setImageBitmap(mainImage);

        //things for halloween edition!!!!!
        ImageView halloween1 = (ImageView) view.findViewById(R.id.halloween1);
        Bitmap halloweenImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.hallow2, 0.4f);
        halloween1.setImageBitmap(halloweenImage);

        ImageView halloween2 = (ImageView) view.findViewById(R.id.halloween2);
        Bitmap halloweenImage2 = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.hallow3, 0.4f);
        halloween2.setImageBitmap(halloweenImage2);

        //testing textview only
        testing = (TextView) view.findViewById(R.id.testing);
        testing.setText("Welcome");

        setFoodView();

        return view;
    }

    //Drag and drop functionality
    //need both touch and drag listener to handle this
    View.OnTouchListener touchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());
            ClipData dragData = new ClipData((CharSequence) view.getTag(), new String[] {ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
            view.setAlpha(0);

            return dragContainer.startDragChild(view, dragData, null, 0);
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
                    if (getTouchPosition(view, dragEvent).x >= (int)(metrics.widthPixels * 0.75))
                    {
                        animateImage(getTouchPosition(view, dragEvent).x, getTouchPosition(view, dragEvent).y, getTouchPosition(view, dragEvent).x, metrics.widthPixels, foodView);
                        swipeLike();
                    }
                    else if (getTouchPosition(view, dragEvent).x <= (int)(metrics.widthPixels * 0.25))
                    {
                        swipeDislike();
                    }
                    else if (getTouchPosition(view, dragEvent).y <= (int)(metrics.heightPixels * 0.5))
                    {
                        swipeLove();
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

    private void animateImage(float startx, float starty, float endx, float endy, ImageView view)
    {
        animation = new TranslateAnimation(startx, starty, endx, endy);
        animation.setDuration(1000);
        animation.setRepeatCount(0);
        view.setAnimation(animation);
        animation.start();
    }

    private void swipe()
    {
        Singleton.getInstance().getRecipeChoice().addRecipe(RandomRecipeGenerator.getRandomRecipe());
        setFoodView();
    }

    private void swipeLike()
    {
        testing.setText("Like");
        Singleton.getInstance().getRecipeList().addRecipe(Singleton.getInstance().getRecipeChoice().getChoiceRecipe(), new Date());
        Singleton.getInstance().getRecipeChoice().addRecipe(RandomRecipeGenerator.getRandomRecipe());
        listDrawerHandler.updateLikedView();
        //updating shared perference
        Singleton.getInstance().updateSharedPreference();
        setFoodView();
    }

    private void swipeDislike()
    {
        testing.setText("Dislike");
        Singleton.getInstance().getRecipeChoice().addRecipe(RandomRecipeGenerator.getRandomRecipe());
        Singleton.getInstance().getRecipeChoice().getRecipe();
        //updating shared perference
        Singleton.getInstance().updateSharedPreference();
        Log.d("link", GetRequestURLGenerate.getRandomURL());
        setFoodView();
    }

    private void swipeLove()
    {
        testing.setText("Save!");
        Intent calendarIntent = new Intent(getActivity().getApplicationContext(), CalendarChoice.class);
        calendarIntent.putExtra("RECIPE", Singleton.getInstance().getRecipeChoice().getChoiceRecipe());
        getActivity().startActivityForResult(calendarIntent, InfoDefine.REQUEST_FOR_CALENDAR);
    }

    private void setFoodView()
    {

        final ImageView foregroundView = (ImageView) view.findViewById(R.id.foodView);
        ImageProcessor.setURLImage(getContext(), Singleton.getInstance().getRecipeChoice().getChoiceRecipe().pictureView, 0.95f,
                new CallbackHelper() {
                    @Override
                    public void onSuccess(JSONObject result) {

                    }

                    @Override
                    public void onSuccess(Bitmap result) { foregroundView.setImageBitmap(result); }
                });

        final ImageView backgroundView = (ImageView) view.findViewById(R.id.backgroundfoodView);
        ImageProcessor.setURLImage(getContext(), Singleton.getInstance().getRecipeChoice().getBackgroundRecipe().pictureView, 0.95f,
                new CallbackHelper() {
                    @Override
                    public void onSuccess(JSONObject result) {

                    }

                    @Override
                    public void onSuccess(Bitmap result) {
                        backgroundView.setImageBitmap(result);
                    }
                });

        final ImageView background2View = (ImageView) view.findViewById(R.id.background2foodView);
        ImageProcessor.setURLImage(getContext(), Singleton.getInstance().getRecipeChoice().showlist[2].pictureView, 0.95f,
                new CallbackHelper() {
                    @Override
                    public void onSuccess(JSONObject result) {

                    }

                    @Override
                    public void onSuccess(Bitmap result) {
                        background2View.setImageBitmap(result);
                    }
                });
    }
}
