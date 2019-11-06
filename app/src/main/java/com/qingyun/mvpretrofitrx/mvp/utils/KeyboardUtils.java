package com.qingyun.mvpretrofitrx.mvp.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;


public class KeyboardUtils {

    public static void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            view.requestFocus();
            imm.showSoftInput(view, 0);
        }
    }

    public static void showKeyboard(Context context) {
        View view = ((Activity)context).getCurrentFocus();

        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            view.requestFocus();
            imm.showSoftInput(view, 0);
        }
    }

    public static void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
    public static void  toggleSoftInput(View view){
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(0,0);
        }
    }


    public static void  toggleSoftInput(Context context){
        View view = ((Activity)context).getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(0,0);
        }
    }

    public static void hideKeyboard(Context context) {
        View view = ((Activity)context).getCurrentFocus();
        KeyboardUtils.hideKeyboard(view);
    }




    //一个静态变量存储高度
    public static int keyboardHeight = 0;
    static boolean isVisiableForLast = false;
    ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = null;


    public interface KeyBoardSizeListener{
        void onKeyBoardVisiable(boolean visible);
        void onKeyBoardSize(int keyboardHeight);
    }
//    public  void addOnSoftKeyBoardVisibleListener(Activity activity,KeyBoardSizeListener keyBoardSizeListener) {
//        if (keyboardHeight > 0) {
//            keyBoardSizeListener.onKeyBoardSize(keyboardHeight);
//        }
//        final View decorView = activity.getWindow().getDecorView();
//        onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Rect rect = new Rect();
//                        decorView.getWindowVisibleDisplayFrame(rect);
//                        //计算出可见屏幕的高度
//                        int displayHight = rect.bottom - rect.top;
//                        //获得屏幕整体的高度
//                        Log.e("-------onGlobalLayout",rect.bottom+"");
//
//                        int hight = decorView.getHeight();
//                        boolean visible = (double) displayHight / hight < 0.8;
//                        int statusBarHeight = 0;
//
//
//                        Rect frame = new Rect();
//                        ((Activity)CommonData.mNowContext).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//                        statusBarHeight = frame.top;
//                        if (visible && visible != isVisiableForLast) {
//                            //获得键盘高度
//                            Log.e("----------------->", "" + statusBarHeight);
//                            keyboardHeight = hight - displayHight;
//                            Log.i("keyboardHeight==1213==", "" + keyboardHeight);
//
//                            keyBoardSizeListener.onKeyBoardSize(keyboardHeight);
//                        }
//                        isVisiableForLast = visible;
//                        keyBoardSizeListener.onKeyBoardVisiable(visible);
//                    }
//                },200);
//            }
//        };
//        decorView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
//    }

}