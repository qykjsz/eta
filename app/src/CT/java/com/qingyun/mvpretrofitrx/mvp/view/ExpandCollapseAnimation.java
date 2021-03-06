package com.qingyun.mvpretrofitrx.mvp.view;


import android.view.View;
import android.view.animation.Animation;

import com.bumptech.glide.load.Transformation;

/**
 * Date 2019/11/16.
 * Created by Sam
 * ClassExplain：
 */
public class ExpandCollapseAnimation extends Animation {
    private final View mTargetView;//动画执行view
    private final int mStartHeight;//动画执行的开始高度
    private final int mEndHeight;//动画结束后的高度

    public ExpandCollapseAnimation(View contentview , int startHeight, int endHeight) {
        mTargetView = contentview;
        mStartHeight = startHeight;
        mEndHeight = endHeight;
        setDuration(2000);
    }

    protected void applyTransformation(float interpolatedTime, Transformation t) {
        //applyTransformation()方法就是动画具体的实现，每隔一段时间会调用此方法
        //计算出每次应该显示的高度
        final int newHeight = (int)((mEndHeight - mStartHeight) * interpolatedTime + mStartHeight);
        //改变执行view的高度，实现动画
        mTargetView.getLayoutParams().height = newHeight;
        mTargetView.requestLayout();
    }
}
