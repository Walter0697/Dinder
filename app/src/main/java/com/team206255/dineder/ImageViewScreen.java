package com.team206255.dineder;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.json.JSONObject;

public class ImageViewScreen extends AppCompatActivity {

    Recipe viewRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_screen);

        if (getIntent().hasExtra("RECIPE"))
            viewRecipe = (Recipe)getIntent().getSerializableExtra("RECIPE");
        else
            viewRecipe = new Recipe();

        final ImageView viewImage = (ImageView)findViewById(R.id.imageView);
        ImageProcessor.setFullURLImage(viewRecipe.pictureView, new CallbackHelper() {
            @Override
            public void onSuccess(JSONObject result) {
                //do nothing here
            }

            @Override
            public void onSuccess(Bitmap result) {
                viewImage.setImageBitmap(result);
            }
        });

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
