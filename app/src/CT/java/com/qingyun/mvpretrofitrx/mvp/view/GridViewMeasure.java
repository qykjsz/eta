package com.qingyun.mvpretrofitrx.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * author:  Sam
 * date:  2014/9/26
 * ClassName:
 */
public class GridViewMeasure extends GridView {
    public GridViewMeasure(Context context) {
        super(context);
    }

    public GridViewMeasure(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewMeasure(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
