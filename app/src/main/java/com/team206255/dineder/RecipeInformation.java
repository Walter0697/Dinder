package com.team206255.dineder;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class RecipeInformation extends AppCompatActivity {

    Recipe curentRecipe = new Recipe();

    DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_information);

        metrics = getResources().getDisplayMetrics();

        if (getIntent().hasExtra("RECIPE"))
            curentRecipe = (Recipe)getIntent().getSerializableExtra("RECIPE");

        ImageView recipeView = (ImageView) findViewById(R.id.RecipeView);
        Bitmap unsized = curentRecipe.getImage(getResources());
        Bitmap recipeImage = ImageProcessor.scaleImage(metrics, getResources(), unsized, 0.35f);
        recipeView.setImageBitmap(recipeImage);

        TextView nameText = (TextView) findViewById(R.id.recipeNameText);
        nameText.setText(curentRecipe.name);

        RatingBar difficultyBar = (RatingBar) findViewById(R.id.currentDifficultyBar);
        difficultyBar.setRating(curentRecipe.difficulty);

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
