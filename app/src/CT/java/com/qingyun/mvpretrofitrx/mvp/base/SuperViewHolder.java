package com.qingyun.mvpretrofitrx.mvp.base;

import android.view.View;

/**
 * Created by Sam on 2016/11/24.
 */
public abstract class SuperViewHolder {
    public SuperViewHolder(View view) {
        view.setTag(this);
    }
}
