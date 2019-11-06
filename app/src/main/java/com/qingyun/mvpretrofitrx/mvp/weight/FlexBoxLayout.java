package com.qingyun.mvpretrofitrx.mvp.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FlexBoxLayout extends ViewGroup {
    /**
     * 居中换行
     */
    public int foxheight= 5;//换行H间隙
    public FlexBoxLayout(Context context) {
        this(context, null);
    }

    public FlexBoxLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int childCount = getChildCount();
        int x = 0,y = 0,row = 0,ih=0;
        for(int i = 0; i<childCount; i++){
            getChildAt(i).measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            if(getChildAt(i).getMeasuredHeight()>ih)
            {
                ih = getChildAt(i).getMeasuredHeight();
            }
        }

        for (int index = 0; index < childCount; index++) {
            final View child = this.getChildAt(index);
            if (child.getVisibility() != View.GONE ) {
                child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
                // 此处增加onlayout中的换行判断，用于计算所需的高度
                int width = child.getMeasuredWidth();
                int height = ih;

                x += width;
                y = row * height + height;
                if (x > maxWidth) {
                    x = width;
                    row++;
                    ih = ih+foxheight;
                    height = ih;
                    y = row * (height) + height;
                }
            }
        }

        setMeasuredDimension(maxWidth, y);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        final int childCount = getChildCount();
        int maxWidth = r - l;
        int x = 0,y = 0,row = 0,ih=0;
        for(int i = 0; i<childCount; i++){
            getChildAt(i).measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            if(getChildAt(i).getMeasuredHeight()>ih)
            {
                ih = getChildAt(i).getMeasuredHeight();
            }
        }

        for (int i = 0; i < childCount; i++) {
            final View child = this.getChildAt(i);
            if (child.getVisibility() != View.GONE ) {
                int width = child.getMeasuredWidth();
                int height = ih;
                x += width;
                y = row * height + height;
                if (x > maxWidth) {
                    x = width;
                    row++;
                    ih = ih+foxheight;
                    height = ih;
                    y = row * (height) + height;
                }
                if( child.getMeasuredHeight()< ih)
                {
                    child.layout((x - width) + getPaddingLeft(), (y - height)+(ih-child.getMeasuredHeight())/2 ,
                            x- + getPaddingRight(), y-(ih-child.getMeasuredHeight())/2);
                }else{
                    child.layout((x - width) + getPaddingLeft(),  y - height, x + getPaddingRight(), y);
                }
            }
        }
    }

}