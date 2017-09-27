package com.team206255.dineder;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private NonSwipeableViewPager viewPager;
    private ImageView searchButton;
    private ImageView favouriteButton;
    private ImageView mainFeatureButton;

    //setting the class for different screen
    private SearchScreen searchScreen = new SearchScreen();
    private MainScreen mainScreen = new MainScreen();
    private FavouriteScreen favouriteScreen = new FavouriteScreen();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up the view pager
        viewPager = (NonSwipeableViewPager) findViewById(R.id.container);
        setUpViewPager(viewPager);
        viewPager.setCurrentItem(1);

        //set up the button and their listener
        searchButton = (ImageView) findViewById(R.id.leftfrag);
        favouriteButton = (ImageView) findViewById(R.id.rightfrag);
        mainFeatureButton = (ImageView) findViewById(R.id.centerImage);

        searchButton.setOnClickListener(fragmentListener);
        favouriteButton.setOnClickListener(fragmentListener);
        mainFeatureButton.setOnClickListener(fragmentListener);
    }

    private void setUpViewPager(ViewPager viewPager)
    {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(searchScreen);
        adapter.addFragment(mainScreen);
        adapter.addFragment(favouriteScreen);
        viewPager.setAdapter(adapter);
    }

    View.OnClickListener fragmentListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.leftfrag:
                    viewPager.setCurrentItem(0, true);
                    break;
                case R.id.rightfrag:
                    viewPager.setCurrentItem(2, true);
                    break;
                case R.id.centerImage:
                    viewPager.setCurrentItem(1, true);
                    break;
            }
        }
    };
}
