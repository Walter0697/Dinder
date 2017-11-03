package com.team206255.dineder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SeekBar;

public class FilterScreen extends AppCompatActivity {

    private SeekBar durationSeekBar;
    private EditText durationNum;
    private SeekBar calorieSeekBar;
    private EditText calorieNum;

    private StringAdapter cuisineCheck;
    private ListView cuisineList;

    private StringAdapter ingredientCheck;
    private ListView ingredientList;

    private EditText moreTextBox;
    private Button moreButton;

    //storing the recipe filter for different usage
    RecipeFilter recipeFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_screen);

        //getting the display metrics
        DisplayMetrics metrics = getResources().getDisplayMetrics();

        if (getIntent().hasExtra("RECIPEFILTER"))
            recipeFilter = (RecipeFilter)getIntent().getSerializableExtra("RECIPEFILTER");
        else
            recipeFilter = new RecipeFilter();

        //setting up the icon image
        ImageView topIcon = (ImageView) findViewById(R.id.filterScreenIcon);
        Bitmap topImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.filterdrawer, 0.2f);
        topIcon.setImageBitmap(topImage);

        //duration seek bar and edit num
        durationSeekBar = (SeekBar) findViewById(R.id.advancedDurationSeekBar);
        durationNum = (EditText) findViewById(R.id.advancedDurationNum);
        durationNum.setFilters(new InputFilter[] {new InputFilterMinMax(1, InfoDefine.maxDuration)});
        //setting up range
        //durationSeekBar.setMin(InfoDefine.minDuration);
        durationSeekBar.setMax(InfoDefine.maxDuration);
        //setting up listener
        durationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                recipeFilter.duration = i;
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
                recipeFilter.duration = Integer.parseInt(editable.toString());
                durationSeekBar.setProgress(Integer.parseInt(editable.toString()));
            }
        });

        //calorie seek bar and edit num
        calorieSeekBar = (SeekBar) findViewById(R.id.advancedCalorieSeekBar);
        calorieNum = (EditText) findViewById(R.id.advancedCalorieNum);
        calorieNum.setFilters(new InputFilter[] {new InputFilterMinMax(1, InfoDefine.maxCalorie)});
        //setting up range
        //calorieSeekBar.setMin(InfoDefine.minCalorie);
        calorieSeekBar.setMax(InfoDefine.maxCalorie);
        //setting up listener
        calorieSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                recipeFilter.calorie = i;
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
                recipeFilter.calorie = Integer.parseInt(editable.toString());
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

        //set up the add more ingredients text box
        moreTextBox = (EditText) findViewById(R.id.moreTextbox);
        moreButton = (Button) findViewById(R.id.moreButton);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.getInstance().getRecipeFilter().addIngredient(moreTextBox.getText().toString());
                ingredientList.invalidate();
                ((BaseAdapter)ingredientList.getAdapter()).notifyDataSetChanged();
                moreTextBox.setText("");
            }
        });

        //set up the widgets by value in filter
        setupByFilter(recipeFilter);

        //set up the button to save the recipe
        final Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recipeFilter.ingredients = Singleton.getInstance().getRecipeFilter().ingredients;
                recipeFilter.userDefineIngredients = Singleton.getInstance().getRecipeFilter().userDefineIngredients;
                Intent output = new Intent();
                output.putExtra("OUTPUTFILTER", recipeFilter);
                setResult(RESULT_OK, output);
                finish();
            }
        });
    }

    private void setupByFilter(RecipeFilter filter)
    {
        durationSeekBar.setProgress((int)filter.duration);
        durationNum.setText(Integer.toString((int)filter.duration));

        calorieSeekBar.setProgress((int)filter.calorie);
        calorieNum.setText(Integer.toString((int)filter.calorie));
    }
}
