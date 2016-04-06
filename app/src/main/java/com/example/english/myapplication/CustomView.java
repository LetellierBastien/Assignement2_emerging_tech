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
import android.widget.TextView;
import android.widget.Toast;

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
    public boolean death;
    public boolean uncover =true;
    public TextView txt;
    Context context;

    public CustomView(Context context , AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        RecPaint = new Paint();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CustomView, 0, 0);
        try {
            RecColor = a.getInteger(R.styleable.CustomView_circleColor, 0);
        } finally {
            a.recycle();
        }
        reset();
    }

    public void reset()
    {
        uncoverCase=0;
        for(int i=0; i<100;i++)
        {
            tabHide[i]=0;
            tab[i]
                    =0;
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

        }

        for(int i=0; i<100;i++)
        {
            if(tabHide[i]==0)
            {
                int nbMine=0;
                int borneXMin=-1;
                int borneXMax=1;
                int borneYMin=-1;
                int borneYMax=1;
                x=i/10;
                y=i%10;
                if (x==0)
                {
                    borneXMin=0;
                }
                else if(x==9)
                {
                    borneXMax = 0;
                }

                if (y==0)
                {
                    borneYMin = 0;
                }
                else if (y==9)
                {
                    borneYMax = 0;
                }

                for(;borneXMin<=borneXMax;borneXMin++)
                {
                    for(int z = borneYMin;z<=borneYMax;z++)
                    {
                        if (tabHide[i+(borneXMin*10+z)]==10)
                        {
                            nbMine++;
                        }
                    }
                }
                tabHide[i]=nbMine;

            }
        }
        nbMarkedMine =0;
        death = false;
        invalidate();
    }
    public int nbMarkedMine;
    public int uncoverCase;

    public boolean onTouchEvent( MotionEvent event) {



        if (event.getAction() == MotionEvent.ACTION_DOWN && !death){
            if (tab[((int)event.getX()/40)*10+((int)event.getY()/40)]==0) {
                if(uncover) {

                    tab[((int) event.getX() / 40) * 10 + ((int) event.getY() / 40)] = 1;
                    uncoverCase++;
                    if (tabHide[((int) event.getX() / 40) * 10 + ((int) event.getY() / 40)] == 10) {
                        death = true;

                        CharSequence text = "Sorry you lose! :(";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                    else if (uncoverCase == 80)
                    {

                        death = true;
                        CharSequence text = "Congratulation! You win ;)";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }

                    invalidate();
                }
                else
                {

                        tab[((int) event.getX() / 40) * 10 + ((int) event.getY() / 40)] = 2;
                    nbMarkedMine++;
                    invalidate();
                }

            }
            else if (tab[((int)event.getX()/40)*10+((int)event.getY()/40)]==2)
            {
                if (!uncover) {
                    tab[((int) event.getX() / 40) * 10 + ((int) event.getY() / 40)] = 0;
                    nbMarkedMine--;
                }
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

        txt.setText("Marked: " + nbMarkedMine);

        int viewWidth = this.getMeasuredWidth();
        int viewHeight = this.getMeasuredHeight();
        RecPaint.setStyle(Paint.Style.FILL);
        RecPaint.setAntiAlias(true);
        RecPaint.setColor(RecColor);
        canvas.drawRect(0, 0, viewHeight, viewWidth, RecPaint);


        for (int i = 0; i < 100; i++) {
            RecPaint.setTextSize(16);
            if (tab[i] == 2) {
                RecPaint.setColor(Color.parseColor("#FFFF00"));
                canvas.drawRect((i / 10) * 40, (i % 10) * 40, (i / 10) * 40 + 40, (i % 10) * 40 + 40, RecPaint);
            } else if (tab[i] == 1 && tabHide[i] == 10) {
                RecPaint.setColor(Color.parseColor("#FF0000"));
                canvas.drawRect((i / 10) * 40, (i % 10) * 40, (i / 10) * 40 + 40, (i % 10) * 40 + 40, RecPaint);
            } else if (tab[i] != 0) {
                RecPaint.setColor(Color.parseColor("#CCCCCC"));
                canvas.drawRect((i / 10) * 40, (i % 10) * 40, (i / 10) * 40 + 40, (i % 10) * 40 + 40, RecPaint);
                if (tab[i] == 1 && tabHide[i] == 1) {
                    RecPaint.setColor(Color.parseColor("#0000FF"));
                    canvas.drawText("1", (i / 10) * 40 + 18, (i % 10) * 40 + 25, RecPaint);
                } else if (tab[i] == 1 && tabHide[i] == 2) {
                    RecPaint.setColor(Color.parseColor("#CCCCCC"));
                    canvas.drawRect((i / 10) * 40, (i % 10) * 40, (i / 10) * 40 + 40, (i % 10) * 40 + 40, RecPaint);
                    RecPaint.setColor(Color.parseColor("#00FF00"));
                    canvas.drawText("2", (i / 10) * 40 + 18, (i % 10) * 40 + 25, RecPaint);

                } else if (tab[i] == 1 && tabHide[i] == 3) {
                    RecPaint.setColor(Color.parseColor("#CCCCCC"));
                    canvas.drawRect((i / 10) * 40, (i % 10) * 40, (i / 10) * 40 + 40, (i % 10) * 40 + 40, RecPaint);
                    RecPaint.setColor(Color.parseColor("#FFFF00"));
                    canvas.drawText("3", (i / 10) * 40 + 18, (i % 10) * 40 + 25, RecPaint);
                } else if (tab[i] == 1 && tabHide[i] > 3) {
                    RecPaint.setColor(Color.parseColor("#CCCCCC"));
                    canvas.drawRect((i / 10) * 40, (i % 10) * 40, (i / 10) * 40 + 40, (i % 10) * 40 + 40, RecPaint);
                    RecPaint.setColor(Color.parseColor("#FF0000"));
                    canvas.drawText("" + tabHide[i], (i / 10) * 40 + 18, (i % 10) * 40 + 25, RecPaint);
                }
            }

        }
        RecPaint.setColor(Color.parseColor("#FFFFFF"));
        for (int i = 0; i <= viewHeight; i = i + 40) {
            canvas.drawLine(i, 0, i, viewWidth, RecPaint);
            canvas.drawLine(0, i, viewHeight, i, RecPaint);
        }

    }




}
