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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;

public class FilterScreen extends AppCompatActivity {

    private Spinner calorieSpinner;
    private Spinner fatSpinner;

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

        recipeFilter = UserInformation.getInstance().getRecipeFilter();

        //setting up the icon image
        ImageView topIcon = (ImageView) findViewById(R.id.filterScreenIcon);
        Bitmap topImage = ImageProcessor.scaleImage(R.drawable.filterdrawer, 0.2f);
        topIcon.setImageBitmap(topImage);

        //set up the cuisine checkbox
        cuisineCheck = new StringAdapter(this, R.layout.check_box_detail, getResources().getStringArray(R.array.cuisine), InfoDefine.ListType.CUISINE_BOX);
        cuisineList = (ListView) findViewById(R.id.cuisineCheck);
        cuisineList.setAdapter(cuisineCheck);

        //set up the ingredients checkbox
        ingredientCheck = new StringAdapter(this, R.layout.check_box_detail, getResources().getStringArray(R.array.ingredient), InfoDefine.ListType.INGREDIENT_BOX);
        ingredientList = (ListView) findViewById(R.id.ingredientCheck);
        ingredientList.setAdapter(ingredientCheck);

        //set up the calorie spinner
        calorieSpinner = (Spinner) findViewById(R.id.calorieSpinner);
        String[] calorieChoice = {"1000", "800", "500", "300", "100"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, calorieChoice);
        calorieSpinner.setAdapter(adapter);
        calorieSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                UserInformation.getInstance().getRecipeFilter().calorie_set_by_spinner(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //set up the fat spinner
        fatSpinner = (Spinner) findViewById(R.id.fatSpinner);
        String[] fatChoice = {"100", "80", "60", "40", "20"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, fatChoice);
        fatSpinner.setAdapter(adapter2);
        fatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                UserInformation.getInstance().getRecipeFilter().fat_set_by_spinner(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //set up the add more ingredients text box
        moreTextBox = (EditText) findViewById(R.id.moreTextbox);
        moreButton = (Button) findViewById(R.id.moreButton);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInformation.getInstance().getRecipeFilter().addIngredient(moreTextBox.getText().toString());
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
                recipeFilter.ingredients = UserInformation.getInstance().getRecipeFilter().ingredients;
                recipeFilter.userDefineIngredients = UserInformation.getInstance().getRecipeFilter().userDefineIngredients;
                Intent output = new Intent();
                output.putExtra("OUTPUTFILTER", recipeFilter);
                setResult(RESULT_OK, output);
                finish();
            }
        });

        //set that so the screen won't turn off
        if (UserInformation.getInstance().getEnableScreenOn())
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        else
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    private void setupByFilter(RecipeFilter filter)
    {
        calorieSpinner.setSelection(recipeFilter.calorie_spinner_position());
        fatSpinner.setSelection(recipeFilter.fat_spinner_position());
    }
}
