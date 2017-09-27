package com.team206255.dineder;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

/**
 * Created by walter on 2017-09-25.
 */

public class ImageProcessor {
    public Bitmap scaleImage(DisplayMetrics displayMetrics, Resources res, int pic, float ratio)
    {
        Bitmap icon = BitmapFactory.decodeResource(res, pic);
        int width = icon.getWidth();
        int height = icon.getHeight();
        float ratioBitmap = (float) width / (float) height;

        int screenWidth = displayMetrics.widthPixels;
        int maxWidth = (int)(screenWidth * ratio);
        int maxHeight = (int)((float) maxWidth / ratioBitmap);
        Bitmap image = Bitmap.createScaledBitmap(icon, maxWidth, maxHeight, true);

        return image;
    }
}
