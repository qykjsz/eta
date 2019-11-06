package com.qingyun.mvpretrofitrx.mvp.weight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;

public class CheckableTextView extends android.support.v7.widget.AppCompatTextView implements Checkable {

private boolean mChecked;

private static final int[] CHECKED_STATE_SET = {
        android.R.attr.state_checked
};

public CheckableTextView(Context context) {
    super(context);
}

public CheckableTextView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
}

@Override
protected int[] onCreateDrawableState(int extraSpace) {
    final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
    if (isChecked()) {
        mergeDrawableStates(drawableState, CHECKED_STATE_SET);
    }
    return drawableState;
}

@Override
public void setChecked(boolean checked) {
    mChecked = checked;
    refreshDrawableState();
}

@Override
public boolean isChecked() {
    return mChecked;
}

@Override
public void toggle() {
    mChecked = !mChecked;
}

}