package com.example.english.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by english on 30/03/2016.
 */
public class CustomView extends View
{


    private int RecColor;
    private Paint RecPaint;

    public CustomView(Context context , AttributeSet attrs) {
        super(context, attrs);

        RecPaint = new Paint();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CustomView, 0, 0);
        try {
            RecColor = a.getInteger(R.styleable.CustomView_circleColor, 0);
        } finally {
            a.recycle();
        }
    }
    public int getRecColor(){
        return RecColor;
    }
    

    public void setRecColor(int newColor){
        RecColor=newColor;
        invalidate();
        requestLayout();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int viewWidth = this.getMeasuredWidth();
        int viewHeight = this.getMeasuredHeight();
        RecPaint.setStyle(Paint.Style.FILL);
        RecPaint.setAntiAlias(true);
        RecPaint.setColor(RecColor);
        canvas.drawRect(0, 0, viewHeight, viewWidth, RecPaint);
        RecPaint.setColor(Color.parseColor("#FFFFFF"));
        for(int i=0; i<= viewHeight; i=i+30)
        {
            canvas.drawLine(i,0,i,viewWidth, RecPaint);
            canvas.drawLine(0,i,viewHeight,i, RecPaint);
        }

    }

}
