package com.team206255.dineder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONObject;

public class RecipeInformation extends AppCompatActivity {

    Recipe currentRecipe = new Recipe();
    private ScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_information);

        scroll = (ScrollView) findViewById(R.id.scrolling);

        if (getIntent().hasExtra("RECIPE"))
            currentRecipe = (Recipe) getIntent().getSerializableExtra("RECIPE");

        final ImageView recipeView = (ImageView) findViewById(R.id.RecipeView);
        ImageProcessor.setURLImage(currentRecipe.pictureView, 0.35f,
                new CallbackHelper() {
                    @Override
                    public void onSuccess(JSONObject result) {

                    }

                    @Override
                    public void onSuccess(Bitmap result) {
                        recipeView.setImageBitmap(result);
                    }
                });
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

        TextView durationText = (TextView) findViewById(R.id.durationView);
        durationText.setText("Duration: " + currentRecipe.duration + " mins");

        TextView healthScoreText = (TextView) findViewById(R.id.healthScoreView);
        healthScoreText.setText("Health score: " + currentRecipe.healthScore);

        TextView caloriesText = (TextView) findViewById(R.id.calories);
        caloriesText.setText(Float.toString(currentRecipe.calorie));

        TextView fatText = (TextView) findViewById(R.id.fat);
        fatText.setText(Float.toString(currentRecipe.fat));

        TextView carbsText = (TextView) findViewById(R.id.carbs);
        carbsText.setText(Float.toString(currentRecipe.carbs));

        TextView proteinText = (TextView) findViewById(R.id.protein);
        proteinText.setText(Float.toString(currentRecipe.protein));

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

    //scrolling controlled by volume key!!!!
    //won't affect the volume of the device
    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {

        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch(keyCode)
        {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN)
                {
                    int yposition = scroll.getScrollY();
                    scroll.scrollTo(0, yposition - 50);
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN)
                {
                    int yposition = scroll.getScrollY();
                    scroll.scrollTo(0, yposition + 50);
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }
}

