package com.team206255.dineder;


import android.content.Context;
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
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by walter on 2017-09-25.
 */

public class ImageProcessor {

    static Context context;

    public static void setContext(Context c)
    {context = c;
    }

    //to scale the image according to the screen width
    public static Bitmap scaleImage(int pic, float ratio)
    {
        Bitmap icon = ResourceDrawableToBitmap(pic);
        return scaleImage(icon, ratio);
    }

    public static Bitmap scaleImage(Bitmap pic, float ratio)
    {
        int width = pic.getWidth();
        int height = pic.getHeight();
        float ratioBitmap = (float) width / (float) height;

        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
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

        int radius = h / 2; //since we are scaling the image by width, the radius should only be related to width
        Bitmap output = Bitmap.createBitmap(h + 8, h + 8, Bitmap.Config.ARGB_8888);

        Paint p = new Paint();
        p.setAntiAlias(true);

        Canvas c = new Canvas(output);
        c.drawARGB(0, 255, 255, 255);
        p.setStyle(Paint.Style.FILL);

        c.drawCircle((h / 2) + 4, (h / 2) + 4, radius, p);
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        c.drawBitmap(bitmap, 4, 4, p);
        p.setXfermode(null);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.BLUE);
        p.setStrokeWidth(10);
        c.drawCircle((h / 2) + 4, (h / 2) + 4, radius, p);

        return output;
    }

    //turning R.drawable into bitmap
    public static Bitmap ResourceDrawableToBitmap(int image)
    {
        return BitmapFactory.decodeResource(context.getResources(), image);
    }

    //setting the full iamge into the screen
    public static void setFullURLImage(final String url, final CallbackHelper callback)
    {
        final Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                bitmap = scaleImage(bitmap, 1.0f);
                callback.onSuccess(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Bitmap bitmap = scaleImage(R.drawable.failed, 0.1f);
                callback.onSuccess(bitmap);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Bitmap bitmap = scaleImage(R.drawable.loading, 0.1f);
                //callback.onSuccess(bitmap);
            }
        };

        Picasso.with(context).load(url).into(target);
    }

    //setting the scaled cropped image into the imageview
    public static void setURLImage(final String url, final float scale, final CallbackHelper callback)
    {
        final Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                bitmap = getCroppedBitmap(bitmap);
                bitmap = drawCircleBorder(bitmap);
                bitmap = scaleImage(bitmap, scale);
                callback.onSuccess(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Bitmap bitmap = scaleImage(R.drawable.failed, 0.1f);
                callback.onSuccess(bitmap);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Bitmap bitmap = scaleImage(R.drawable.loading, 1.0f);
                bitmap = getCroppedBitmap(bitmap);
                bitmap = drawCircleBorder(bitmap);
                bitmap = scaleImage(bitmap, scale);
                callback.onSuccess((Bitmap)null);
            }
        };

        Picasso.with(context).load(url).into(target);
    }

    //preload the image before showing
    public static void preLoad(String url)
    {
        Picasso.with(context).load(url).fetch();
    }
}
