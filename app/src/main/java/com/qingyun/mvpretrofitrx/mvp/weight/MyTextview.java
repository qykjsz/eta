package com.qingyun.mvpretrofitrx.mvp.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.senon.mvpretrofitrx.R;

public class MyTextview extends LinearLayout {
    private TextView textView = null;
    private ImageView img = null;

    //字体颜色
    protected int textColor;
    //字体大小
    protected float textSize;
    //最大行数
    protected int maxLine;
    //文字
    protected String text;

    //默认颜色
    public int defaultTextColor = Color.BLACK;
    //默认字体大小
    public int defaultTextSize = 14;
    //默认行数
    public int defaultLine = 2;


    public MyTextview(Context context) {
        super(context);
        initView();
    }

    public MyTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        initAttrs(context, attrs);
        textListener();
    }

    public MyTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initAttrs(context, attrs);
        textListener();
    }

    public void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextStyle);
        textColor = typedArray.getColor(R.styleable.MyTextStyle_textColor, defaultTextColor);
        textSize = typedArray.getDimensionPixelSize(R.styleable.MyTextStyle_textSize, defaultTextSize);
        maxLine = typedArray.getInt(R.styleable.MyTextStyle_maxLine, defaultLine);
        text = typedArray.getString(R.styleable.MyTextStyle_text);
        setMyView(textColor, textSize, maxLine, text);
        //用完 回收一下
        typedArray.recycle();
    }

    public void initView() {
        setOrientation(VERTICAL);
        int padding = dip2px(getContext(), 10);
        textView = new TextView(getContext());
        //行间距
        textView.setLineSpacing(3f, 1f);
        addView(textView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        img = new ImageView(getContext());
        img.setPadding(0, padding, 0, 0);
        //imageview设置图片
        img.setImageResource(R.mipmap.otc_xz_icon);
        LayoutParams llp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        llp.gravity = Gravity.RIGHT;
        addView(img, llp);
    }


    protected void setMyView(int color, float size, final int line, String text) {
        //文本设置颜色
        textView.setTextColor(color);
        //字体大小
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        //设置文字
        textView.setText(text);
        //设置TextView的高度
        textView.setHeight(textView.getLineHeight() * line+10);

        ViewTreeObserver observer = textView.getViewTreeObserver(); // textAbstract为TextView控件
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                if (textView.getLineCount() > line) {
                    img.setVisibility(VISIBLE);
                } else {
                    img.setVisibility(GONE);
                }
            }
        });

    }


    protected void textListener() {
        setOnClickListener(new OnClickListener() {
            boolean isGo;

            @Override
            public void onClick(View v) {
                isGo = !isGo;
                textView.clearAnimation();
                //相差的高度
                final int deltaValue;
                //初始的高度
                final int startValue = textView.getHeight();
                //动画播放的时间
                int duration = 200;
                if (isGo) {
                    //Image图片打开的动画
                    deltaValue = textView.getLineHeight() * textView.getLineCount() - startValue;
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(duration);
                    animation.setFillAfter(true);
                    img.startAnimation(animation);
                } else {
                    //Image图片关闭的动画
                    deltaValue = textView.getLineHeight() * maxLine - startValue;
                    RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(duration);
                    animation.setFillAfter(true);
                    img.startAnimation(animation);
                }
                //打开或者关闭的时候textview下面的展开动画
                Animation animation = new Animation() {
                    protected void applyTransformation(float interpolatedTime, Transformation t) {
                        textView.setHeight((int) (startValue + deltaValue * interpolatedTime));
                    }
                };
                animation.setDuration(duration);
                textView.startAnimation(animation);
            }
        });
    }


    public TextView getTextView() {
        return textView;
    }

    public void setText(CharSequence charSequence) {
        textView.setText(charSequence);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
