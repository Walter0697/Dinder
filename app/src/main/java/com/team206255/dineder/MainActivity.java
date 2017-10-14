package com.team206255.dineder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //view pager and the button for their fragment page
    private NonSwipeableViewPager viewPager;
    SectionsPagerAdapter adapter;
    private ImageView searchButton;
    private ImageView favouriteButton;
    private ImageView mainFeatureButton;

    //setting the class for different screen
    SearchScreen searchScreen;
    MainScreen mainScreen;
    CalendarScreen calendarScreen;
    //static FavouriteScreen favouriteScreen;


    //getting display metrics
    private DisplayMetrics metrics;

    //data for the app should store in the main activity, so that all fragments will call this
    static RecipeFilter recipeFilter = new RecipeFilter();
    static RecipeChoice recipeChoice = new RecipeChoice();
    static RecipeList likeList = new RecipeList();
    static CalendarStorage calendarStorage = new CalendarStorage();
    //static RecipeList saveList = new RecipeList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        metrics = this.getResources().getDisplayMetrics();
        setContentView(R.layout.activity_main);

        //setting up the context for the recipe filter so you can accesss R.string
        recipeFilter.setUpContext(getApplicationContext());
        //creating new class
        searchScreen = new SearchScreen();
        mainScreen = new MainScreen();
        calendarScreen = new CalendarScreen();

        //set up the view pager
        viewPager = (NonSwipeableViewPager) findViewById(R.id.container);
        setUpViewPager(viewPager);
        viewPager.setCurrentItem(1);

        //set up the button and their listener
        searchButton = (ImageView) findViewById(R.id.leftfrag);
        favouriteButton = (ImageView) findViewById(R.id.rightfrag);
        mainFeatureButton = (ImageView) findViewById(R.id.centerImage);

        //setup the image of the icon
        //set up the center image and push its position downward in order to have a better look
        Bitmap mainFeatureImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.internet, 0.42f);
        mainFeatureButton.setImageBitmap(mainFeatureImage);
        AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams)mainFeatureButton.getLayoutParams();
        int dpValue = -100;
        float den = getResources().getDisplayMetrics().density;
        int margin = (int)(dpValue * den);
        layoutParams.bottomMargin = margin;
        mainFeatureButton.setLayoutParams(layoutParams);

        //set up the onclick listener in order to change the page in viewPager
        searchButton.setOnClickListener(fragmentListener);
        favouriteButton.setOnClickListener(fragmentListener);
        mainFeatureButton.setOnClickListener(fragmentListener);
    }

    //adding all the pages into the viewPager
    private void setUpViewPager(ViewPager viewPager)
    {
        adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(searchScreen);
        adapter.addFragment(mainScreen);
        adapter.addFragment(calendarScreen);
        viewPager.setAdapter(adapter);
    }

    //change to the corresponding page when button click
    //0:searchScreen
    //1:mainScreen
    //2:favouriteScreen
    View.OnClickListener fragmentListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.leftfrag:
                    viewPager.setCurrentItem(0, true);
                    break;
                case R.id.rightfrag:
                    calendarStorage.today();
                    calendarScreen.updateCalendarView();
                    viewPager.setCurrentItem(2, true);
                    break;
                case R.id.centerImage:
                    viewPager.setCurrentItem(1, true);
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == InfoDefine.REQUEST_FOR_FILTER) {
            if (resultCode == RESULT_OK)
                mainScreen.filterDrawerHandler.setValueByFilter(recipeFilter);
        }
        else if (requestCode == InfoDefine.REQUEST_FOR_CALENDAR){
            if (resultCode == RESULT_OK) {
                calendarScreen.updateCalendarView();
            }
        }
    }
}
