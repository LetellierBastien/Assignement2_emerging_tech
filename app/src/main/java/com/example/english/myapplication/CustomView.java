package com.example.english.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.EventListener;


/**
 * Created by english on 30/03/2016.
 */
public class CustomView extends View
{


    private int RecColor;
    private Paint RecPaint;
    private int [] tab = new int [169];

    public CustomView(Context context , AttributeSet attrs) {
        super(context, attrs);

        for(int i=0; i<169;i++)
        {
            tab[i]=0;
        }

        RecPaint = new Paint();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CustomView, 0, 0);
        try {
            RecColor = a.getInteger(R.styleable.CustomView_circleColor, 0);
        } finally {
            a.recycle();
        }
    }


    public boolean onTouchEvent( MotionEvent event) {
        Log.d("enter?", "%f" + (event.getX()));
        Log.d("enter?", "%f" + (event.getY()));
        Log.d("enter?", "%f" + ((int)event.getX()/30));
        Log.d("enter?", "%f" + ((int)event.getY()/30)%13);
        if (event.getAction() == MotionEvent.ACTION_DOWN){

            tab[((int)event.getX()/30)*13+((int)event.getY()/30)]=1;
            invalidate();
        }
        return true;
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


        for(int i=0; i<169; i++)
        {
            if (tab[i]==1)
            {
                RecPaint.setColor(Color.parseColor("#CCCCCC"));
                canvas.drawRect((i/13)*30, (i%13)*30, (i/13)*30+30, (i%13)*30+30, RecPaint);
            }
        }
        RecPaint.setColor(Color.parseColor("#FFFFFF"));
            for (int i = 0; i <= viewHeight; i = i + 30) {
                canvas.drawLine(i, 0, i, viewWidth, RecPaint);
                canvas.drawLine(0, i, viewHeight, i, RecPaint);
            }




    }

}
