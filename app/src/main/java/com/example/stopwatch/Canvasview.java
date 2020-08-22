package com.example.stopwatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;


import androidx.annotation.Nullable;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Canvasview extends View {
    public int x;
    public int y;
    public int z;
    public Paint paintcircle;
    public Paint paintline;
    private int mHeight;
    private int mWidth;
    private int mRadius;
    private double mAngle;
    private int mCentreX;
    private int mCentreY;
    private int mPadding;
    private boolean mIsInit;
    private Paint mPaint;
    private Path mPath;
    private int[] mNumbers;
    private int mMinimum;
    public static float mMinute;
    public static float mSecond ;
    private int mHourHandSize;
    private int mHandSize;
    private Rect mRect;
    private View v;
    private Button startbutton;
    private Button stopbutton;
    public static boolean drawing ;
    public static boolean first =true;
    public static boolean p = false;
    public static int time = 0;

    public Canvasview(Context context) {
        super(context);

    }

    public Canvasview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public Canvasview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public Canvasview(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    public void init()
    {
        paintcircle=new Paint();
        paintline = new Paint();
        paintline.setColor(Color.BLACK);
        paintline.setStrokeWidth(5);

        paintcircle.setStrokeWidth(5);
        paintcircle.setStyle(Paint.Style.STROKE);
        paintcircle.setColor(Color.GREEN);
        mHeight = getHeight();
        mWidth = getWidth();
        mPadding = 50;
        mCentreX = mWidth/2;
        mCentreY = mHeight/2;
        mMinimum = Math.min(mHeight, mWidth);
        mRadius = mMinimum/2 - mPadding;
        mAngle = (float) ((Math.PI/30) - (Math.PI/2));
        mPaint = new Paint();
        mPath = new Path();
        mRect = new Rect();
        mHourHandSize = mRadius/4;
        mHandSize = mRadius;
        mNumbers = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};


        v = LayoutInflater.from(getContext()).inflate(R.layout.activity_main , null , false);
        startbutton=v.findViewById(R.id.button);
        stopbutton=v.findViewById(R.id.button2);
        mIsInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        if (!mIsInit) {
            init();
        }
        if (first) {
            drawCircle(canvas);
            drawCircle2(canvas);
            drawSecondsHand(canvas,mSecond);
            drawMinuteHand(canvas, mMinute);
            drawNumerals(canvas);
            drawNumerals2(canvas);
            first = false;
        }
        if (drawing) {
            drawCircle(canvas);
            drawCircle2(canvas);
        drawSecondsHand(canvas,mSecond++);
        drawMinuteHand(canvas , mMinute);
        if (time % 60 == 0 && time!=0) {
            mMinute += 5;
        }
        drawNumerals(canvas);
        drawNumerals2(canvas);
        }
        if (p) {
            postInvalidateDelayed(1000);
            time += 1;
        }


        }
//        startbutton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawing = true;
//                postInvalidateDelayed(1000);
//            }
//        });
//        stopbutton.setOnClickListener(new OnClickListener() {
//                                          @Override
//                                          public void onClick(View v) {
//                                              drawing = false;
//
//                                          }
//        x=getWidth();
//        y=getHeight();
//        z=getWidth()/2;
//        canvas.drawLine(x/2,y/2,x/2,y/2-z,paintline);
//
//        canvas.drawCircle(x/2,y/2,z,paintcircle);

//    private void drawCircle(Canvas canvas) {
//        mPaint.reset();
//        mPaint.setColor(Color.BLACK);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(8);
//        mPaint.setAntiAlias(true);
//        mPaint.setColor(getResources().getColor(android.R.color.black));
//        canvas.drawCircle(mCentreX,mCentreY,mRadius,mPaint);
//    }
    private void setPaintAttributes(int colour, Paint.Style stroke, int strokeWidth) {
        mPaint.reset();
        mPaint.setColor(colour);
        mPaint.setStyle(stroke);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setAntiAlias(true);
    }
    private void drawCircle(Canvas canvas) {
        mPaint.reset();
        setPaintAttributes(Color.BLACK, Paint.Style.STROKE,8);
        canvas.drawCircle(mCentreX,mCentreY,mRadius,mPaint);
    }
    private void drawCircle2(Canvas canvas) {
        mPaint.reset();
        setPaintAttributes(Color.BLACK, Paint.Style.STROKE,8);
        canvas.drawCircle(mCentreX,mCentreY - mRadius/2,mRadius / 4,mPaint);
    }
    private void drawSecondsHand(Canvas canvas, float location) {
        mPaint.reset();
        setPaintAttributes(Color.RED, Paint.Style.STROKE,8);
        mAngle = Math.PI * location / 30 - Math.PI / 2;
        canvas.drawLine(mCentreX,mCentreY,(float) (mCentreX+Math.cos(mAngle)*mHandSize)
                , (float) (mCentreY+Math.sin(mAngle)*mHandSize),mPaint);
    }
    private void drawNumerals(Canvas canvas) {
        float mFontSize=50;
        mPaint.setTextSize(mFontSize);
        for (int number : mNumbers) {
            String num = String.valueOf(number);

            mPaint.getTextBounds(num, 0, num.length(), mRect);
            double angle = Math.PI / 6 * (number - 3);
            int x = (int) (mCentreX + Math.cos(angle) * mRadius - mRect.width() / 2);
            int y = (int) (mCentreY + Math.sin(angle) * mRadius + mRect.height() / 2);
            int temp=Integer.parseInt(num);

            temp=temp*5;
            if(temp==60)
            {
                temp=0;
            }
            num=String.valueOf(temp);
            canvas.drawText(num, x, y, mPaint);
        }
    }

    private void drawNumerals2(Canvas canvas) {
        float mFontSize=40;
        mPaint.setTextSize(mFontSize);
        mPaint.setColor(Color.GREEN);
        for (int number : mNumbers) {
            String num = String.valueOf(number);

            mPaint.getTextBounds(num, 0, num.length(), mRect);
            double angle = Math.PI / 6 * (number - 3);
            int x = (int) (mCentreX + Math.cos(angle) * (mRadius/4) - mRect.width() / 2);
            int y = (int) ((mCentreY- mRadius/2)+ Math.sin(angle) * (mRadius/4) + mRect.height() / 2);
            int temp=Integer.parseInt(num);


            if(temp==12)
            {
                temp=0;
            }
            num=String.valueOf(temp);

            canvas.drawText(num, x, y, mPaint);
        }
    }
    private void drawMinuteHand(Canvas canvas, float location) {
        mPaint.reset();
        setPaintAttributes(Color.BLACK, Paint.Style.STROKE,8);
        mAngle = Math.PI * location / 30 - Math.PI / 2;
        canvas.drawLine(mCentreX,mCentreY - mRadius/2,(float) (mCentreX+Math.cos(mAngle)*mHourHandSize)
                , (float) ((mCentreY- mRadius/2)+Math.sin(mAngle)*mHourHandSize),mPaint);
    }

}
