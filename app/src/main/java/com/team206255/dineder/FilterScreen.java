package com.team206255.dineder;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SeekBar;

public class FilterScreen extends AppCompatActivity {

    private RatingBar difficultyBar;
    private SeekBar durationSeekBar;
    private EditText durationNum;
    private SeekBar calorieSeekBar;
    private EditText calorieNum;

    private StringAdapter cuisineCheck;
    private ListView cuisineList;

    private StringAdapter ingredientCheck;
    private ListView ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_screen);

        //getting the display metrics
        DisplayMetrics metrics = getResources().getDisplayMetrics();


        //setting up the icon image
        ImageView topIcon = (ImageView) findViewById(R.id.filterScreenIcon);
        Bitmap topImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.filter, 0.2f);
        topIcon.setImageBitmap(topImage);

        //setting up everything just like the drawer
        //difficultyBar
        difficultyBar = (RatingBar) findViewById(R.id.advancedDifficultyBar);
        difficultyBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                MainActivity.recipeFilter.difficulty = (int)v;
            }
        });

        //duration seek bar and edit num
        durationSeekBar = (SeekBar) findViewById(R.id.advancedDurationSeekBar);
        durationNum = (EditText) findViewById(R.id.advancedDurationNum);
        //setting up range
        //durationSeekBar.setMin(InfoDefine.minDuration);
        durationSeekBar.setMax(InfoDefine.maxDuration);
        //setting up listener
        durationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                MainActivity.recipeFilter.duration = i;
                durationNum.setText(Integer.toString(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        durationNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                MainActivity.recipeFilter.duration = Integer.parseInt(editable.toString());
                durationSeekBar.setProgress(Integer.parseInt(editable.toString()));
            }
        });

        //calorie seek bar and edit num
        calorieSeekBar = (SeekBar) findViewById(R.id.advancedCalorieSeekBar);
        calorieNum = (EditText) findViewById(R.id.advancedCalorieNum);
        //setting up range
        //calorieSeekBar.setMin(InfoDefine.minCalorie);
        calorieSeekBar.setMax(InfoDefine.maxCalorie);
        //setting up listener
        calorieSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                MainActivity.recipeFilter.calorie = i;
                calorieNum.setText(Integer.toString(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        calorieNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                MainActivity.recipeFilter.calorie = Integer.parseInt(editable.toString());
                calorieSeekBar.setProgress(Integer.parseInt(editable.toString()));
            }
        });

        //set up the cuisine checkbox
        cuisineCheck = new StringAdapter(this, R.layout.check_box_detail, getResources().getStringArray(R.array.cuisine), InfoDefine.ListType.CUISINE_BOX);
        cuisineList = (ListView) findViewById(R.id.cuisineCheck);
        cuisineList.setAdapter(cuisineCheck);

        //set up the ingredients checkbox
        ingredientCheck = new StringAdapter(this, R.layout.check_box_detail, getResources().getStringArray(R.array.ingredient), InfoDefine.ListType.INGREDIENT_BOX);
        ingredientList = (ListView) findViewById(R.id.ingredientCheck);
        ingredientList.setAdapter(ingredientCheck);

        //set up the widgets by value in filter
        setupByFilter(MainActivity.recipeFilter);

        //set up the button to save the recipe
        final Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private void setupByFilter(RecipeFilter filter)
    {
        difficultyBar.setRating(filter.difficulty);

        durationSeekBar.setProgress((int)filter.duration);
        durationNum.setText(Integer.toString((int)filter.duration));

        calorieSeekBar.setProgress((int)filter.calorie);
        calorieNum.setText(Integer.toString((int)filter.calorie));
    }
}
