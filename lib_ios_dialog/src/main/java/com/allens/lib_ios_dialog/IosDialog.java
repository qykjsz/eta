package com.allens.lib_ios_dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author allens
 */
public class IosDialog {
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private LinearLayout lLayout_alert_ll;
    private TextView txt_title;
    private TextView txt_msg;
    private Button btn_neg;
    private Button btn_pos;
    private ImageView img_line;
    private Display display;


    /***
     * 用于添加view
     */
    private Map<String, View> viewHap;

    /**
     * 是否显示title
     */
    private boolean showTitle = false;
    /***
     * 是否显示msg
     */
    private boolean showMsg = false;
    /***
     * 是否显示确定按钮
     */
    private boolean showPosBtn = false;

    /**
     * 是否显示取消按钮
     */
    private boolean showNegBtn = false;


    /**
     * dialog  宽度
     */
    private float dialogWidth = 0.7f;


    public IosDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public IosDialog builder() {
        View view = LayoutInflater.from(context).inflate(R.layout.view_dialog, null);
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        lLayout_alert_ll = (LinearLayout) view.findViewById(R.id.lLayout_alert_ll);
        lLayout_alert_ll.setVerticalGravity(View.GONE);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setVisibility(View.GONE);
        txt_msg = (TextView) view.findViewById(R.id.txt_msg);
        txt_msg.setVisibility(View.GONE);
        btn_neg = (Button) view.findViewById(R.id.btn_neg);
        btn_neg.setVisibility(View.GONE);
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        btn_pos.setVisibility(View.GONE);
        img_line = (ImageView) view.findViewById(R.id.img_line);
        img_line.setVisibility(View.GONE);

        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * dialogWidth), LayoutParams.WRAP_CONTENT));
        return this;
    }


    public IosDialog setTitle(@NonNull String title) {
        showTitle = true;
        txt_title.setText(title);
        return this;
    }

    public IosDialog setMsg(@NonNull String msg) {
        showMsg = true;
        txt_msg.setText(msg);
        return this;
    }


    public IosDialog addEdit(String tag) {
        if (viewHap == null) {
            viewHap = new HashMap<>();
        }
        viewHap.put(tag, getEt());
        return this;
    }

    /***
     * 设置对应edit  的hint
     * @param tag
     * @param hint
     */
    public IosDialog setEditHint(String tag, String hint) {
        EditText editText = getEditText(tag);
        if (editText != null) {
            editText.setHint(hint);
        }
        return this;
    }

    private EditText getEditText(String tag) {
        if (viewHap != null) {
            View view = viewHap.get(tag);
            if (view != null) {
                try {
                    return ((EditText) view);
                } catch (Exception e) {
                    Throwable throwable = new Throwable("当前tag 对应的View 不是 EditText");
                    throwable.printStackTrace();
                }
            } else {
                Throwable throwable = new Throwable("当前tag 未找到对应 的 EditText");
                throwable.printStackTrace();
            }
        } else {
            Throwable throwable = new Throwable("没有可以set 的 EditText");
            throwable.printStackTrace();
        }
        return null;
    }


    /***
     * 设置颜色
     * @param tag
     * @param color
     * @return
     */
    public IosDialog setEditTextColor(String tag, int color) {
        EditText editText = getEditText(tag);
        if (editText != null) {
            editText.setTextColor(color);
        }
        return this;
    }

    /***
     * padding
     * @param tag
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @return
     */
    public IosDialog setEditTextPadding(String tag, float left, float top, float right, float bottom) {
        EditText editText = getEditText(tag);
        if (editText != null) {
            editText.setPadding(dp2px(left), dp2px(top), dp2px(right), dp2px(bottom));
        }
        return this;
    }

    /***
     * margin
     * @param tag
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @return
     */
    public IosDialog setEditTextMargin(String tag, float left, float top, float right, float bottom) {
        EditText editText = getEditText(tag);
        if (editText != null) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(dp2px(left), dp2px(top), dp2px(right), dp2px(bottom));
            editText.setLayoutParams(lp);
        }
        return this;
    }


    /***
     * 是否显示光标
     * @param tag
     * @param isShowCursor
     * @return
     */
    public IosDialog setCursorVisible(String tag, Boolean isShowCursor) {
        EditText editText = getEditText(tag);
        if (editText != null) {
            editText.setCursorVisible(isShowCursor);
        }
        return this;
    }

    private EditText getEt() {
        LinearLayout linearLayout = getlLayout_alert_ll();
        EditText editText = new EditText(context);
        editText.setHint("请输入名称");
        editText.setBackgroundResource(R.drawable.et_bg);
        editText.setTextColor(Color.BLACK);
        editText.setTextSize(14);
        editText.setCursorVisible(false);
        editText.setPadding(dp2px(4), dp2px(4), dp2px(4), dp2px(4));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(dp2px(15), dp2px(15), dp2px(15), 0);
        editText.setLayoutParams(lp);
        linearLayout.addView(editText);
        return editText;
    }

    /***
     * 是否点击返回能够取消
     * @param cancel
     * @return
     */
    public IosDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置是否可以取消
     *
     * @param isCancelOutside
     * @return
     */
    public IosDialog setCancelOutside(boolean isCancelOutside) {
        dialog.setCanceledOnTouchOutside(isCancelOutside);
        return this;
    }

    /**
     * 设置确定
     *
     * @param text
     * @param listener
     * @return
     */
    public IosDialog setPositiveButton(String text,
                                       final OnClickListener listener) {
        showPosBtn = true;
        btn_pos.setText(text);
        btn_pos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    public IosDialog setPositiveButton(final OnClickListener listener) {
        setPositiveButton("确定", listener);
        return this;
    }

    public IosDialog setPositiveButton(final OnEdPositiveListener listener) {
        setPositiveButton("确定", listener);
        return this;
    }


    public IosDialog setPositiveButton(String text, final OnEdPositiveListener listener) {
        showPosBtn = true;
        btn_pos.setText(text);
        btn_pos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHap == null) {
                    dialog.dismiss();
                    Throwable throwable = new Throwable("当前没有可用的EditText 请使用 OnClickListener 的接口");
                    throwable.printStackTrace();
                    return;
                }
                HashMap<String, String> strings = new HashMap<>();
                for (Map.Entry<String, View> entry : viewHap.entrySet()) {
                    String key = entry.getKey();
                    EditText editText = (EditText) entry.getValue();
                    String msg = editText.getText().toString();
                    strings.put(key, msg);
                }
                listener.onClick(v, strings);
                dialog.dismiss();
            }
        });

        return this;
    }

    /***
     * 点击确定
     */
    public interface OnEdPositiveListener {
        /***
         * 确定
         * @param view
         * @param msgMap
         */
        void onClick(View view, HashMap<String, String> msgMap);
    }

    /***
     * 设置取消
     * @param text
     * @param listener
     * @return
     */
    public IosDialog setNegativeButton(String text,
                                       final OnClickListener listener) {
        showNegBtn = true;
        btn_neg.setText(text);
        btn_neg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    public IosDialog setNegativeButton(
            final OnClickListener listener) {
        setNegativeButton("取消", listener);
        return this;
    }


    private void setLayout() {
        if (!showTitle && !showMsg) {
            txt_msg.setVisibility(View.GONE);
            txt_title.setVisibility(View.GONE);
        }

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            txt_msg.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && !showNegBtn) {
            btn_pos.setVisibility(View.GONE);
            btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
            btn_pos.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if (showPosBtn && showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alertdialog_right_selector);
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.alertdialog_left_selector);
            img_line.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }

        if (!showPosBtn && showNegBtn) {
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }
    }

    public void show() {
        setLayout();
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }


    /**
     * 设置dialog  宽度
     *
     * @param dialogWidth
     * @return
     */
    public IosDialog setDialogWidth(float dialogWidth) {
        if (lLayout_bg != null) {
            lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * dialogWidth), LayoutParams.WRAP_CONTENT));
        }
        this.dialogWidth = dialogWidth;
        return this;
    }


    /***
     * 获取title
     * @return
     */
    public TextView getTxt_title() {
        return txt_title;
    }

    /***
     * 获取msg
     * @return
     */
    public TextView getTxt_msg() {
        return txt_msg;
    }

    /**
     * 获取确定按钮
     *
     * @return
     */
    public Button getBtn_neg() {
        return btn_neg;
    }

    /***
     * 获取用于添加自定义控件的ll
     * @return
     */
    public LinearLayout getlLayout_alert_ll() {
        return lLayout_alert_ll;
    }


    /***
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     * @param dpValue
     * @return
     */
    public int dp2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public int px2sp(float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public int sp2px(float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取取消按钮
     *
     * @return
     */
    public Button getBtn_pos() {
        return btn_pos;
    }
}
