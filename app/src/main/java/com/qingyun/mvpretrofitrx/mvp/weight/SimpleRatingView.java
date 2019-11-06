package com.qingyun.mvpretrofitrx.mvp.weight;

import android.content.Context;
import android.widget.ImageView;

import com.example.xlhratingbar_lib.IRatingView;
import com.senon.mvpretrofitrx.R;

public class SimpleRatingView implements IRatingView {
 
    @Override
    public int getStateRes(int posi, int state) {
        int id = R.mipmap.wd_kwjx_icon;
        switch (state) {
            case STATE_NONE:
                id = R.mipmap.wd_kwjx_icon;
                break;
            case STATE_HALF:
                id = R.mipmap.wd_kwjx_icon;
                break;
            case STATE_FULL:
                id = R.mipmap.wd_swjx_icon;
                break;
            default:
                break;
        }
        return id;
    }
 
    @Override
    public int getCurrentState(float rating, int numStars, int position) {
        position++;
        float dis = position - rating;
        if (dis <= 0) {
            return STATE_FULL;
        }
        if (dis == 0.5) {
            return STATE_HALF;
        }
        if (dis > 0.5) {
            return STATE_NONE;
        }
        return 0;
    }
 
 
    @Override
    public ImageView getRatingView(Context context, int numStars, int posi) {
        ImageView imageView = new ImageView(context);
        return imageView;
    }
}
