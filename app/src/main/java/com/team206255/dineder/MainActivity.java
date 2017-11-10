package com.team206255.dineder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

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

    //storing the bitmap here so they don't have to reload afterward
    Bitmap mainFeatureImage, mainFeatureClick;
    Bitmap searchFeatureImage, searchFeatureClick;
    Bitmap calendarFeatureImage, calendarFeatureClick;

    //getting display metrics
    private DisplayMetrics metrics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        metrics = this.getResources().getDisplayMetrics();
        setContentView(R.layout.activity_main);

        //creating new class
        searchScreen = new SearchScreen();
        mainScreen = new MainScreen();
        calendarScreen = new CalendarScreen();

        //set up the view pager
        viewPager = (NonSwipeableViewPager) findViewById(R.id.container);
        setUpViewPager(viewPager);
        //1 -> Mainscreen
        viewPager.setCurrentItem(1);

        //set up the button and their listener
        searchButton = (ImageView) findViewById(R.id.leftfrag);
        favouriteButton = (ImageView) findViewById(R.id.rightfrag);
        mainFeatureButton = (ImageView) findViewById(R.id.centerImage);

        //setup the bitmap for all icons
        mainFeatureImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.mainscreen, 0.42f);
        searchFeatureImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.search, 0.5f);
        calendarFeatureImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.calendar, 0.5f);
        mainFeatureClick = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.mainscreen_action, 0.42f);
        searchFeatureClick = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.search_action, 0.5f);
        calendarFeatureClick = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.calendar_action, 0.5f);

        //just little fun feature, don't mind
        Random rand = new Random();
        if (rand.nextInt(100) < 10) {
            searchFeatureImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.searchscreen, 0.5f);
            searchFeatureClick = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.searchscreen_action, 0.5f);
        }
        if (rand.nextInt(100) < 10) {
            calendarFeatureImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.menu, 0.5f);
            calendarFeatureClick = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.menu_action, 0.5f);
        }

        //setup the image of the icon
        //set up the center image and push its position downward in order to have a better look
        mainFeatureButton.setImageBitmap(mainFeatureClick);
        AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams)mainFeatureButton.getLayoutParams();
        int dpValue = -100;
        float den = getResources().getDisplayMetrics().density;
        int margin = (int)(dpValue * den);
        layoutParams.bottomMargin = margin;
        mainFeatureButton.setLayoutParams(layoutParams);

        //setup the image of the rest of the icons
        searchButton.setImageBitmap(searchFeatureImage);
        favouriteButton.setImageBitmap(calendarFeatureImage);

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
    //2:calendarScreen
    View.OnClickListener fragmentListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.leftfrag:
                    //setting up the image
                    searchButton.setImageBitmap(searchFeatureClick);
                    mainFeatureButton.setImageBitmap(mainFeatureImage);
                    favouriteButton.setImageBitmap(calendarFeatureImage);

                    viewPager.setCurrentItem(0, true);
                    break;
                case R.id.rightfrag:
                    //setting up the image
                    searchButton.setImageBitmap(searchFeatureImage);
                    mainFeatureButton.setImageBitmap(mainFeatureImage);
                    favouriteButton.setImageBitmap(calendarFeatureClick);

                    //whenever this button is clicked
                    //current day for calendar screen will become today
                    UserInformation.getInstance().getCalendarStorage().today();
                    calendarScreen.updateCalendarView();
                    viewPager.setCurrentItem(2, true);
                    break;
                case R.id.centerImage:
                    //setting up the image
                    searchButton.setImageBitmap(searchFeatureImage);
                    mainFeatureButton.setImageBitmap(mainFeatureClick);
                    favouriteButton.setImageBitmap(calendarFeatureImage);

                    viewPager.setCurrentItem(1, true);
                    break;
            }
        }
    };

    //this is for interacting with other class
    //since main three screens are fragment
    //it cannot support the return value for the startActivityForResult
    //therefore we will set every result here
    //and call the function inside the corresponding class
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //After calling the filter screen
        //Called by : Filter Drawer (not anymore)
        if (requestCode == InfoDefine.REQUEST_FOR_FILTER) {
            if (resultCode == RESULT_OK) {
                UserInformation.getInstance().setRecipeFilter((RecipeFilter) data.getSerializableExtra("OUTPUTFILTER"));
                mainScreen.filterDrawerHandler.setValueByFilter(UserInformation.getInstance().getRecipeFilter());
                mainScreen.filterView.scrollTo(0, mainScreen.filterView.getBottom());
            }
        }
        //Called by : Search Screen
        else if (requestCode == InfoDefine.REQUEST_FOR_SEARCH){
            if (requestCode == RESULT_OK)
                searchScreen.searchFilter = (RecipeFilter) data.getSerializableExtra("OUTPUTFILTER");
        }
        //After saving recipes inside calendar
        //Called by : Main Screen
        else if (requestCode == InfoDefine.REQUEST_FOR_CALENDAR){
            if (resultCode == RESULT_OK) {
                calendarScreen.updateCalendarView();
            }
        }
    }
}
