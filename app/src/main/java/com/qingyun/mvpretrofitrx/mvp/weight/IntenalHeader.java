package com.qingyun.mvpretrofitrx.mvp.weight;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;
import com.senon.mvpretrofitrx.R;

public class IntenalHeader extends InternalAbstract implements RefreshHeader {

    private final AnimationDrawable animationDrawable;
    private ImageView header;//动画视图
    private ConstraintLayout primary;//布局id，用于设置背景颜色，非必要

    public IntenalHeader(Context context, @LayoutRes int resource) {
        this(context, null,resource);
    }

    public IntenalHeader(Context context, AttributeSet attrs, @LayoutRes int resource) {
        this(context, attrs, 0,resource);
    }

    public IntenalHeader(Context context, AttributeSet attrs, int defStyleAttr,@LayoutRes int resource) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, resource, this);
        header = view.findViewById(R.id.header);
        primary = view.findViewById(R.id.primary);
        animationDrawable = (AnimationDrawable) header.getBackground();

    }

//    @Override
//    protected void onAnimationStart() {
//        super.onAnimationStart();
//        //获取背景，并将其强转成AnimationDrawable
//        //判断是否在运行
//        if(!animationDrawable.isRunning()){
//            //开启帧动画
//            animationDrawable.start();
//        }
//
//    }

    @Override
    protected void onAnimationEnd() {
        super.onAnimationEnd();
        //判断是否在运行
        if(animationDrawable.isRunning()){
            //开启帧动画
            animationDrawable.stop();
        }
    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        super.onReleased(refreshLayout, height, maxDragHeight);
        //获取背景，并将其强转成AnimationDrawable
        //判断是否在运行
        if(!animationDrawable.isRunning()){
            //开启帧动画
            animationDrawable.start();
        }
    }


    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        //判断是否在运行
        if(animationDrawable.isRunning()){
            //开启帧动画
            animationDrawable.stop();
        }
        return super.onFinish(refreshLayout, success);


    }


}
