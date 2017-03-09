package com.example.apache.fragments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * Created by Apache on 09.03.2017.
 */

public class BounceView extends View {
    float currentX;
    float currentY;

    public BounceView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentX > 0 && currentY > 0) {
            Paint p = new Paint();
            p.setAntiAlias(true);
            p.setColor(Color.RED);
            p.setTextSize(49);

            canvas.drawCircle(currentX - 43, currentY - 42, 40, p);
            canvas.drawText("Туда-сюда", currentX, currentY, p);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX = event.getX();
        currentY = event.getY();

        TranslateAnimation translateanim = new TranslateAnimation(-100, 0, 0, 0);
        translateanim.setInterpolator(new BounceInterpolator());
        translateanim.setDuration(1000);

        startAnimation(translateanim);
        invalidate();
        return true;
    }
}