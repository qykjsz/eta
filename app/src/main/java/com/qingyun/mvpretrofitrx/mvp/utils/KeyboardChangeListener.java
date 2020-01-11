package com.qingyun.mvpretrofitrx.mvp.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * simple and powerful Keyboard show/hidden listener,view {@android.R.id.content} and {@ViewTreeObserver.OnGlobalLayoutListener}
 * Created by yes.cpu@gmail.com 2016/7/13.
 */
public class KeyboardChangeListener implements ViewTreeObserver.OnGlobalLayoutListener {
    private static final String TAG = "ListenerHandler";
    private View mContentView;
    private int mOriginHeight=0;
    private int mPreHeight=0;
    private KeyBoardListener mKeyBoardListen;
    private Rect rect;
    public static Boolean isSofeVisiable = false;

    public interface KeyBoardListener {
        /**
         * call back
         * @param isShow true is show else hidden
         * @param keyboardHeight keyboard height
         */
        void onKeyboardChange(boolean isShow, int keyboardHeight);
    }

    public void setKeyBoardListener(KeyBoardListener keyBoardListen) {
        this.mKeyBoardListen = keyBoardListen;
    }

    public KeyboardChangeListener(Activity contextObj) {
        if (contextObj == null) {
            Log.i(TAG, "contextObj is null");
            return;
        }
        mContentView = findContentView(contextObj);
        if (mContentView != null) {
            addContentTreeObserver();
        }
    }

    public KeyboardChangeListener(View view) {
        mContentView = view;
        if (mContentView != null) {
            addContentTreeObserver();
        }
    }

    private View findContentView(Activity contextObj) {
       return contextObj.getWindow().getDecorView();
//        return contextObj.findViewById(android.R.id.content);
    }

    private void addContentTreeObserver() {
        mContentView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        rect = new Rect();
        mContentView.getWindowVisibleDisplayFrame(rect);
        int currHeight = mContentView.getRootView().getHeight()-rect.bottom;
//        if (currHeight == 0) {
//            Log.i(TAG, "currHeight is 0");
//            return;
//        }
//        Log.e(">>>>>>>>>>>>",currHeight+"");
        boolean hasChange = false;

            if (mPreHeight != currHeight) {
                hasChange = true;
                mPreHeight = currHeight;
            } else {
                hasChange = false;
                return;
            }

        Log.e(TAG, "onGlobalLayout: "+hasChange);
        if (hasChange) {
            boolean isShow;
            int keyboardHeight = 0;
            if ( currHeight<300) {
                //hidden
                isShow = false;
            } else {
                //show
                isShow = true;
            }

            if (mKeyBoardListen != null) {
                isSofeVisiable = isShow;
                mKeyBoardListen.onKeyboardChange(isShow, currHeight);
            }
        }
    }

    public void destroy() {
        if (mContentView != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
    }
}
