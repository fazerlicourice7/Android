package com.atrium.lightsout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import static com.atrium.lightsout.AnimatedSurface.dpPxFactor;


public class Light {
    int width = (68 * dpPxFactor);
    int x;
    int y;
    Bitmap imageOn;
    Bitmap imageOff;
    boolean currentImage;
    Rect dst = new Rect();
    Context context;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public Light(int xPos, int yPos, Context xcontext, boolean stateIn) {
        x = xPos;
        y = yPos;
        context = xcontext;
        currentImage = stateIn;

        dst.set(x, y, x + (68 * dpPxFactor), y + (68 * dpPxFactor));
        imageOn = BitmapFactory.decodeResource(context.getResources(), R.drawable.lightbuttonon);
        imageOff = BitmapFactory.decodeResource(context.getResources(), R.drawable.lightbuttonoff);
    }

    //Accessors
    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getWidth() {
        return (int) width;
    }

    //Modifiers
    public void setX(int xx) {
        x = xx;
    }

    public void setY(int yy) {
        y = yy;
    }

    public boolean getState() {
        return currentImage;
    }

    public void draw(Canvas c) {
        if (!currentImage) {
            c.drawBitmap(imageOn, null, dst, paint);
        }
        if (currentImage) {

            c.drawBitmap(imageOff, null, dst, paint);
        }
    }

    public void onHit() {
        currentImage = !currentImage;
    }

}
