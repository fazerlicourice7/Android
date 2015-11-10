package com.atrium.lightsout;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import static com.atrium.lightsout.AnimatedSurface.dpPxFactor;

/**
 * Created by elija_000 on 11/7/2015.
 */
public class candle {

    Paint candleLight = new Paint(Paint.ANTI_ALIAS_FLAG);


    public candle(){
        candleLight.setColor(Color.YELLOW);
    }

    public void Pulse(int x, int y, Canvas c){
        int alpha = 0;
        boolean increase = true;
        if(AnimatedSurface.frames%5 == 0) {
            if(alpha < 0){
                increase = true;
            } else if(alpha > 30){
                increase = false;
            }
            if(increase) {
                candleLight.setAlpha(alpha++);
            } else{
                candleLight.setAlpha(alpha--);
            }
        }
        c.drawCircle(x, y, (31 * dpPxFactor), candleLight);
    }
}
