package com.team206255.dineder;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

/**
 * Created by walter on 2017-09-25.
 */

public class ImageProcessor {

    //to scale the image according to the screen width
    public static Bitmap scaleImage(DisplayMetrics displayMetrics, Resources res, int pic, float ratio)
    {
        Bitmap icon = ResourceDrawableToBitmap(res, pic);
        return scaleImage(displayMetrics, res, icon, ratio);
    }

    public static Bitmap scaleImage(DisplayMetrics displayMetrics, Resources res, Bitmap pic, float ratio)
    {
        int width = pic.getWidth();
        int height = pic.getHeight();
        float ratioBitmap = (float) width / (float) height;

        int screenWidth = displayMetrics.widthPixels;
        int maxWidth = (int)(screenWidth * ratio);
        int maxHeight = (int)((float) maxWidth / ratioBitmap);
        Bitmap image = Bitmap.createScaledBitmap(pic, maxWidth, maxHeight, true);

        return image;
    }

    //convert drawable into bitmap
    //code from https://stackoverflow.com/questions/3035692/how-to-convert-a-drawable-to-a-bitmap/9390776
    public static Bitmap drawableToBitmap(Drawable drawable)
    {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable)
        {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null)
            {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0)
        {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    //turning bitmap into a circle
    //code from https://stackoverflow.com/questions/11932805/cropping-circular-area-from-bitmap-in-android
    public static Bitmap getCroppedBitmap(Bitmap bitmap)
    {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    //adding a circle border into the bitmap
    //code from https://stackoverflow.com/questions/17040475/adding-a-round-frame-circle-on-rounded-bitmap
    //I modified it to make it better fit to my code
    public static Bitmap drawCircleBorder(Bitmap bitmap)
    {
        int h = bitmap.getHeight();
        int w = bitmap.getWidth();

        int radius = w / 2; //since we are scaling the image by width, the radius should only be related to width
        Bitmap output = Bitmap.createBitmap(w + 8, w + 8, Bitmap.Config.ARGB_8888);

        Paint p = new Paint();
        p.setAntiAlias(true);

        Canvas c = new Canvas(output);
        c.drawARGB(0, 255, 255, 255);
        p.setStyle(Paint.Style.FILL);

        c.drawCircle((w / 2) + 4, (w / 2) + 4, radius, p);
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        c.drawBitmap(bitmap, 4, (w / 2) - (h / 2), p);
        p.setXfermode(null);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.BLUE);
        p.setStrokeWidth(30);
        c.drawCircle((w/2) + 4, (w / 2) + 4, radius, p);

        return output;
    }

    //turning R.drawable into bitmap
    public static Bitmap ResourceDrawableToBitmap(Resources res, int image)
    {
        return BitmapFactory.decodeResource(res, image);
    }
}
