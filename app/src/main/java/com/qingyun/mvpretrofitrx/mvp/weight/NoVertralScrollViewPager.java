package com.qingyun.mvpretrofitrx.mvp.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;


public class NoVertralScrollViewPager extends ViewPager {
    private  int touchSlop;
    private float oldY;
    private float currY;
    private float oldX;
    private float currX;
    private RecyclerView rcy;
    private Boolean isMove = false;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                isMove = false;
                oldY = ev.getY();
                oldX = ev.getX();

                break;
            case MotionEvent.ACTION_UP:

                if (isMove){
                    ev.setAction(MotionEvent.ACTION_MOVE);
                    Log.e("onInterceptTouchEvent","------------8");

                }
                break;
//
//                currY = ev.getY();
//                currX = ev.getX();
//
//                if ( Math.abs(currX-oldX)>touchSlop||Math.abs(currY-oldY)>touchSlop){
//                    oldX = currX;
//                    oldY = currY;
//                    isMove = true;
//                    Log.e("onInterceptTouchEvent","------------4");
//                    return true;
//                }
//                else {
//                    oldX = currX;
//                    oldY = currY;
//                    if (isMove){
//                        ev.setAction(MotionEvent.ACTION_CANCEL);
//                    }
//                    rcy.dispatchTouchEvent(ev);
//                    return false;
//                }

//                else {
//                    ev.setAction(MotionEvent.ACTION_CANCEL);
//                    rcy.dispatchTouchEvent(ev);
//                    oldY = currY;
//                    return true;
//
//                }

            case MotionEvent.ACTION_MOVE:
                currY = ev.getY();
                currX = ev.getX();

                if (Math.abs(currX-oldX)>touchSlop){
                    isMove = true;
                    return true;
                }
//                if (Math.abs(currY-oldY)>=touchSlop){
//                    oldY = currY;
//                    oldX = currX;
//                    Log.e("onInterceptTouchEvent","------------2");
//                    isMove = true;
//                    return false;
//               }
                oldY = currY;
                oldX = currX;

                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        Log.e("onInterceptTouchEvent","------------3");
        rcy.dispatchTouchEvent(ev);
        return super.dispatchTouchEvent(ev);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                oldY = ev.getY();
//                oldX = ev.getX();
//
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_MOVE:
//            case MotionEvent.ACTION_CANCEL:
//
//                currY = ev.getY();
//                currX = ev.getX();
//
//                if ( Math.abs(currX-oldX)>=touchSlop){
//                    oldY = currY;
//                    oldX = currX;
//                    Log.e("onInterceptTouchEvent","------------2");
//                    return true;
//                }else {
//                    oldY = currY;
//                    oldX = currX;
//                    return super.onInterceptTouchEvent(ev);
//                }
//
//
//
//        }
//        Log.e("onInterceptTouchEvent","------------3");

        return super.onInterceptTouchEvent(ev);
    }

    public NoVertralScrollViewPager(@NonNull Context context) {
        super(context);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public NoVertralScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    public void setRecycler(RecyclerView rcy) {
        this.rcy =rcy;
    }
}
