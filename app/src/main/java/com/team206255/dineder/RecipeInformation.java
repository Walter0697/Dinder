package com.team206255.dineder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class RecipeInformation extends AppCompatActivity {

    Recipe currentRecipe = new Recipe();

    DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_information);

        metrics = getResources().getDisplayMetrics();

        if (getIntent().hasExtra("RECIPE"))
            currentRecipe = (Recipe)getIntent().getSerializableExtra("RECIPE");

        ImageView recipeView = (ImageView) findViewById(R.id.RecipeView);
        currentRecipe.setImage(getApplicationContext(), recipeView, 0.35f);
        recipeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recipeImage = new Intent(getApplicationContext(), ImageViewScreen.class);
                recipeImage.putExtra("RECIPE", currentRecipe);
                startActivity(recipeImage);
            }
        });

        TextView nameText = (TextView) findViewById(R.id.recipeNameText);
        nameText.setText(currentRecipe.name);

        RatingBar difficultyBar = (RatingBar) findViewById(R.id.currentDifficultyBar);
        difficultyBar.setRating(currentRecipe.difficulty);

        TextView cuisineText = (TextView) findViewById(R.id.cuisineView);
        cuisineText.setText(currentRecipe.getCuisine(getApplicationContext()));

        TextView durationText = (TextView) findViewById(R.id.durationView);
        durationText.setText("Duration: " + currentRecipe.duration + " mins");

        TextView calorieText = (TextView) findViewById(R.id.calorieView);
        calorieText.setText("Calorie: " + currentRecipe.calorie);

        StringAdapter ingredientsAdapter = new StringAdapter(this, R.layout.string_detail, currentRecipe.ingredients, InfoDefine.ListType.INGREDIENT_LIST);
        ListView ingredients = (ListView) findViewById(R.id.ingredientList);
        ingredients.setAdapter(ingredientsAdapter);

        StringAdapter stepsAdapter = new StringAdapter(this, R.layout.string_detail, currentRecipe.steps, InfoDefine.ListType.STEPS);
        ListView steps = (ListView) findViewById(R.id.stepList);
        steps.setAdapter(stepsAdapter);

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
