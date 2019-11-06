package com.gongwen.marqueen;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by GongWen on 17/9/15.
 */
public class SimpleMF<E extends CharSequence> extends MarqueeFactory<BoldTextView, E> {
    public SimpleMF(Context mContext) {
        super(mContext);
    }

    @Override
    public BoldTextView generateMarqueeItemView(E data) {
        BoldTextView mView = new BoldTextView(mContext);
        mView.setText(data);
        return mView;
    }
}