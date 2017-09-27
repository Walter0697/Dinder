package com.team206255.dineder;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FavouriteScreen extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance)
    {
        View view = inflater.inflate(R.layout.activity_favourite_screen, container, false);
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        ImageProcessor imageProcessor = new ImageProcessor();

        ImageView favouriteIcon = (ImageView) view.findViewById(R.id.favouriteIcon);
        Bitmap favouriteImage = imageProcessor.scaleImage(metrics, getResources(), R.drawable.star, 0.1f);
        favouriteIcon.setImageBitmap(favouriteImage);

        return view;
    }
}
