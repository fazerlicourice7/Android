package com.atrium.lightsout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by Elijah on 11/7/2015.
 */
public class AnimatedSurface extends SurfaceView implements Runnable {
    //Instance variables
    candle Candle = new candle();

    Context context;
    MediaPlayer buttonClickSound;
    Random random = new Random();
    Rect backgroundSize = new Rect();
    Light[][] buttonArray = new Light[5][5];
    long startTime = System.currentTimeMillis();
    static int frames = 0;
    int screenH = 0;
    int screenW = 0;
    int screenDensity;
    int dpPxFactor;
    Bitmap gameBackground;

    Paint sharedPaint = new Paint(Paint.ANTI_ALIAS_FLAG); //A shared paint object

    Thread thread = null;
    SurfaceHolder surfaceHolder;
    volatile boolean running = false;


    /**
     * CONSTRUCTOR -
     *
     * @param context
     */
    public AnimatedSurface(Context context) {
        super(context);
        this.context = context;
        // TODO Auto-generated constructor stub
        surfaceHolder = getHolder();
        //backgroundSize.set(0, 0, 2560, 1440);
        screenDensity = getResources().getDisplayMetrics().densityDpi;
        dpPxFactor = screenDensity / 160;
        int y = (11 * dpPxFactor); // 38px for 2560x1440
        for (int i = 0; i < 5; i++) {
            int x = (165 * dpPxFactor);//583px for 2560x1440
            for (int j = 0; j < 5; j++) {
                buttonArray[i][j] = new Light(x, y, context, true);
                x += (79 * dpPxFactor);// x+=280px for 2560x1440
            }
            y += (79 * dpPxFactor);//y+=280 for 2560x1440
        }
        int number = 0;
        //while (number < 5) {
        number = random.nextInt(10) + 5;
        //}
        for (int loop = 0; loop < number; loop++) {
            int x2 = random.nextInt(5);
            int y2 = random.nextInt(5);
            buttonArray[x2][y2].onHit();

            try {
                buttonArray[x2 + 1][y2].onHit();
            } catch (IndexOutOfBoundsException e) {
            }
            try {
                buttonArray[x2 - 1][y2].onHit();
            } catch (IndexOutOfBoundsException ex) {
            }
            try {
                buttonArray[x2][y2 + 1].onHit();
            } catch (IndexOutOfBoundsException exw) {
            }
            try {
                buttonArray[x2][y2 - 1].onHit();
            } catch (IndexOutOfBoundsException exc) {

            }
        }
        gameBackground = BitmapFactory.decodeResource(getResources(), R.drawable.gamebackground);
        buttonClickSound = MediaPlayer.create(context, R.raw.buttonpressedsound);
    }

    /**
     * This method (re)starts the animation Thread when this Surface resumes.
     */
    public void onResumeMySurfaceView() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * This method stops the Thread when paused.
     */
    public void onPauseMySurfaceView() {
        boolean retry = true;
        running = false;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    /**
     * equivalent to main method. Method that gets run on start.
     */
    public void run() {
        int minutes = 0;
        // TODO Auto-generated method stub
        while (running) {
            if (surfaceHolder.getSurface().isValid()) {
                Canvas canvas = surfaceHolder.lockCanvas();
                screenH = canvas.getHeight();
                screenW = canvas.getWidth();
                backgroundSize.set(0, 0, screenW, screenH);

                frames++;
                int seconds = (int) ((System.currentTimeMillis() - startTime) / 1000);

                if (seconds > 60) {
                    minutes++;
                    startTime = System.currentTimeMillis();
                }
                String time;
                if (seconds < 10) {
                    time = String.valueOf(minutes) + ":" + "0" + String.valueOf(seconds);
                } else {
                    time = String.valueOf(minutes) + ":" + String.valueOf(seconds);
                }
                //Drawing stationary objects.
                //clearScreen(canvas);
                drawBackground(canvas);
                //Candle.Pulse(54,423,canvas);
                //Candle.Pulse(97,504,canvas);
                int dpx = 14; //dip for the x coordinate of the time
                int dpy = 57; //dip for the y coordinate of the time
                int dpSize = 57; // dip for the size of the time
                int pxX = dpx * dpPxFactor, pxY = dpy * dpPxFactor, pxSize = dpSize * dpPxFactor;
                drawText(canvas, time, pxX, pxY, pxSize); //50px,200px,200px for 2560x1440 display
                boolean done = true;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        buttonArray[i][j].draw(canvas);
                        if (!buttonArray[i][j].getState()) {
                            done = false;
                        }
                    }
                }
                if (done) {
                    gameOver(time);
                }
                //===========================================================================
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }


    public void clearScreen(Canvas c) {
        //This fills the screen with whatever color (r,g,b) you choose.
        c.drawRGB(255, 255, 255);
    }

    /**
     * This method provides an example for drawing text to the screen.
     *
     * @param c the Canvas being drawn on.
     */
    public void drawText(Canvas c, String text, int x, int y, int size) {
        Paint pen = new Paint(Paint.LINEAR_TEXT_FLAG);
        pen.setStyle(Paint.Style.FILL);
        pen.setStrokeWidth(5);
        pen.setColor(Color.BLACK);
        pen.setTypeface(Typeface.SANS_SERIF);
        pen.setTextSize(size);

        c.drawText(text, x, y, pen);
    }

    /**
     * Draws an Image to the screen.
     *
     * @param c the Canvas being drawn on.
     */
    public void drawBackground(Canvas c) {
        c.drawBitmap(gameBackground, null, backgroundSize, sharedPaint);
    }

    /**
     * Called on touch
     *
     * @param event The MotionEvent object contains details of the touchEvent.
     * @return - currently always returns true.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            int[] input = inLight((int) event.getX(), (int) event.getY());
            if (input[0] != -1 && input[1] != -1) {
                buttonArray[input[1]][input[0]].onHit();
                buttonClickSound.start();
                try {
                    buttonArray[input[1] + 1][input[0]].onHit();
                } catch (IndexOutOfBoundsException e) {
                }
                try {
                    buttonArray[input[1] - 1][input[0]].onHit();
                } catch (IndexOutOfBoundsException ex) {
                }
                try {
                    buttonArray[input[1]][input[0] + 1].onHit();
                } catch (IndexOutOfBoundsException exw) {
                }
                try {
                    buttonArray[input[1]][input[0] - 1].onHit();
                } catch (IndexOutOfBoundsException exc) {

                }
            }
        }
        return true;
    }


    public void gameOver(String time) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("scoreMSG", time);
        context.startActivity(intent);
    }


    public int[] inLight(int x, int y) {
        int row = -1;
        int col = -1;
        //px values for 2560x1440 display
        // px = dp * (dpi / 160) Formula relating pixels, dpi and dp => calculate dp for everything
        if (x > (165 * dpPxFactor) && x < (244 * dpPxFactor))
            col = 0;
        else if (x > (244 * dpPxFactor) && x < (324 * dpPxFactor))
            col = 1;
        else if (x > (324 * dpPxFactor) && x < (402 * dpPxFactor))
            col = 2;
        else if (x > (402 * dpPxFactor) && x < (482 * dpPxFactor))
            col = 3;
        else if (x > (482 * dpPxFactor) && x < (562 * dpPxFactor))
            col = 4;

        if (y > (11 * dpPxFactor) && y < (90 * dpPxFactor))
            row = 0;
        else if (y > (90 * dpPxFactor) && y < (169 * dpPxFactor))
            row = 1;
        else if (y > (169 * dpPxFactor) && y < (249 * dpPxFactor))
            row = 2;
        else if (y > (249 * dpPxFactor) && y < (328 * dpPxFactor))
            row = 3;
        else if (y > (328 * dpPxFactor) && y < (407 * dpPxFactor))
            row = 4;

        int[] location = new int[2];
        location[0] = col;
        location[1] = row;
        return location;
    }
}
