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

import java.util.Random;


/**
 * Created by english on 30/03/2016.
 */
public class CustomView extends View
{ 


    private int RecColor;
    private Paint RecPaint;
    private int [] tabHide = new int [100];
    private int [] tab = new int [100];

    public CustomView(Context context , AttributeSet attrs) {
        super(context, attrs);

        for(int i=0; i<100;i++)
        {
            tabHide[i]=1;
            tab[i]=0;
        }
        int x;
        int y;

        Random r = new Random();
        for(int i=0;i<20;)
        {

            y =r.nextInt(10);
            x=r.nextInt(10);

            if(tabHide[x*10+y]!=10)
            {
                i++;
                tabHide[x*10+y]=10;
            }
            else
            {
                Log.d("rnd?", "OOOOOOOOOOOOOK " + y + " " + x);
            }
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

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if (tab[((int)event.getX()/40)*10+((int)event.getY()/40)]==0) {
                tab[((int) event.getX() / 40) * 10 + ((int) event.getY() / 40)] = 1;
                invalidate();
            }

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


        for(int i=0; i<100; i++)
        {
            if (tab[i]==1 && tabHide[i]==1 )
            {
                RecPaint.setColor(Color.parseColor("#CCCCCC"));
                canvas.drawRect((i/10)*40, (i%10)*40, (i/10)*40+40, (i%10)*40+40, RecPaint);
            }
            if (tab[i]==1 && tabHide[i]==10 )
            {
                RecPaint.setColor(Color.parseColor("#FF0000"));
                canvas.drawRect((i/10)*40, (i%10)*40, (i/10)*40+40, (i%10)*40+40, RecPaint);
            }

        }
        RecPaint.setColor(Color.parseColor("#FFFFFF"));
            for (int i = 0; i <= viewHeight; i = i + 40) {
                canvas.drawLine(i, 0, i, viewWidth, RecPaint);
                canvas.drawLine(0, i, viewHeight, i, RecPaint);
            }




    }

}
