package com.allens.lib_ios_dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.service.autofill.FillEventHistory;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author allens
 */
public class IosSheetDialog {


    private String TAG = "IOSDialog";
    private Context context;
    private Dialog dialog;


    /***
     * 标题文字
     */
    private String title;

    /***
     * 标题
     */
    private TextView txt_title;
    /***
     * 取消
     */
    private TextView txt_cancel;


    /***
     * 最大单项数目  如果超过次数字  会有滚动效果
     */
    private int maxItem = 7;


    /**
     * LinearLayout   用来添加 item
     */
    private LinearLayout lLayout_content;

    /***
     * 滚动布局
     */
    private ScrollView sLayout_content;


    /***
     * 是否显示设置标记
     */
    private boolean showTitle = false;


    /***
     * 单条数据的高度
     */
    private int itemHeight = 45;

    /***
     * 单条数据集合
     */
    private List<SheetItem> sheetItemList;


    private Display display;

    /***
     * 单条数据的字体大小
     */
    private int itemTvSize = 18;

    public IosSheetDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        assert windowManager != null;
        display = windowManager.getDefaultDisplay();
    }

    public IosSheetDialog builder() {
        View view = LayoutInflater.from(context).inflate(R.layout.view_actionsheet, null);

        view.setMinimumWidth(display.getWidth());

        sLayout_content = view.findViewById(R.id.sLayout_content);
        lLayout_content = view.findViewById(R.id.lLayout_content);
        txt_title = view.findViewById(R.id.txt_title);
        txt_cancel = view.findViewById(R.id.txt_cancel);
        txt_cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);

        return this;
    }

    /***
     * 设置标题
     * @param title
     * @return
     */
    public IosSheetDialog setTitle(String title) {
        this.title = title;
        showTitle = true;
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setText(title);
        return this;
    }


    /***
     * 设置取消的文字
     * @param msg
     * @return
     */
    public IosSheetDialog setCancelTvMsg(String msg) {
        txt_cancel.setText(msg);
        return this;
    }

    /***
     * 设置取消的字体大小
     * @param size
     * @return
     */
    public IosSheetDialog setCancelTvSize(int size) {
        txt_cancel.setTextSize(size);
        return this;
    }


    /***
     * 设置取消的颜色
     * @param color
     * @return
     */
    public IosSheetDialog setCancelTvColor(int color) {
        txt_cancel.setTextColor(color);
        return this;
    }

    /***
     * 设置标题颜色
     * @param color
     * @return
     */
    public IosSheetDialog setTitleColor(int color) {
        showTitle = true;
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setTextColor(color);
        if (title.isEmpty()) {
            txt_title.setText("标题");
        }
        return this;
    }


    /***
     * 设置标题大小
     * @param size
     * @return
     */
    public IosSheetDialog setTitleSize(int size) {
        showTitle = true;
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setTextSize(size);
        if (title.isEmpty()) {
            txt_title.setText("标题");
        }
        return this;
    }


    /***
     * 返回title  tv
     * @return
     */
    public TextView getTitleTv() {
        return txt_title;
    }


    /***
     * 返回 取消 tv
     * @return
     */
    public TextView getCancelTv() {
        return txt_cancel;
    }

    /***
     * 是否点击返回能够取消
     * @param cancel
     * @return
     */
    public IosSheetDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /***
     * 点击其他位置是否能够取消
     * @param cancel
     * @return
     */
    public IosSheetDialog setCancelOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }


    /***
     * 会有一个默认的颜色
     * @param strItem
     * @param listener
     * @return
     */
    public IosSheetDialog addSheetItem(String strItem,
                                       OnSheetItemClickListener listener) {
        addSheetItem(strItem, 0,0, listener);
        return this;
    }


    /**
     * 自定义颜色
     *
     * @param strItem
     * @param color
     * @param listener
     * @return
     */
    public IosSheetDialog addSheetItem(String strItem, int color,int drawableLeft,
                                       OnSheetItemClickListener listener) {
        if (sheetItemList == null) {
            sheetItemList = new ArrayList<>();
        }
        sheetItemList.add(new SheetItem(strItem, color,drawableLeft, listener));
        return this;
    }

    private void setSheetItems() {
        if (sheetItemList == null || sheetItemList.size() <= 0) {
            Log.e(TAG, "item  size <= 0  you need add one");
            return;
        }

        int size = sheetItemList.size();

        //设置 滚动布局的高度为屏幕的一半
        if (size >= maxItem) {
            LayoutParams params = (LayoutParams) sLayout_content.getLayoutParams();
            params.height = display.getHeight() / 2;
            sLayout_content.setLayoutParams(params);
        }

        for (int i = 1; i <= size; i++) {
            final int index = i;
            SheetItem sheetItem = sheetItemList.get(i - 1);

            String strItem = sheetItem.name;
            int color = sheetItem.color;
            final OnSheetItemClickListener listener = sheetItem.itemClickListener;
            TextView textView = new TextView(context);
            sheetItem.textView = textView;
            textView.setText(strItem);
            if (sheetItem.drawableLeft!=0)
            textView.setCompoundDrawablesWithIntrinsicBounds(context.getResources().getDrawable(sheetItem.drawableLeft),null,null,null);
            textView.setCompoundDrawablePadding(20);
            textView.setTextSize(itemTvSize);
            textView.setGravity(Gravity.CENTER);

            if (size == 1) {
                if (showTitle) {
                    lLayout_content.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                } else {
                    lLayout_content.setBackgroundResource(R.drawable.actionsheet_single_selector);
                }
            } else {
                if (showTitle) {
                    if (i >= 1 && i < size) {
                        lLayout_content.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                    } else {
                        lLayout_content.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                    }
                } else {
                    if (i == 1) {
                        lLayout_content.setBackgroundResource(R.drawable.actionsheet_top_selector);
                    } else if (i < size) {
                        lLayout_content.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                    } else {
                        lLayout_content.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                    }
                }
            }

            if (color == 0) {
                textView.setTextColor(Color.parseColor(SheetItemColor.Blue.getName()));
            } else {
                textView.setTextColor(color);
            }

            //dp --> px
            float scale = context.getResources().getDisplayMetrics().density;
            int height = (int) (itemHeight * scale + 0.5f);
            textView.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT, height));

            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(index);
                    dialog.dismiss();
                }
            });

            lLayout_content.addView(textView);
        }
    }


    public interface OnSheetItemClickListener {
        /***
         * click
         * @param which
         */
        void onClick(int which);
    }

    public class SheetItem {
        String name;
        OnSheetItemClickListener itemClickListener;
        int color;
        TextView textView;
        int drawableLeft;


        public SheetItem(String name, int color,
                         int drawableLeft,
                         OnSheetItemClickListener itemClickListener) {
            this.name = name;
            this.color = color;
            this.drawableLeft = drawableLeft;
            this.itemClickListener = itemClickListener;
        }
    }


    /**
     * 设置item 字体大小
     *
     * @param size
     */
    public IosSheetDialog setItemTextSize(int size) {
        this.itemTvSize = size;
        return this;
    }


    /***
     * 设置item  高度
     * @param height
     * @return
     */
    public IosSheetDialog setItemHeight(int height) {
        this.itemHeight = height;
        return this;
    }

    /***
     * 设置最大数目多少时候  会有滚动效果
     * @param maxItemSize
     * @return
     */
    public IosSheetDialog setMaxItemSize(int maxItemSize) {
        this.maxItem = maxItemSize;
        return this;
    }


    /***
     * 获取item  的tv
     * @param pos
     * @return
     */
    public TextView getItemTextView(int pos) {
        SheetItem sheetItem = sheetItemList.get(pos);
        return sheetItem.textView;
    }

    /***
     * 显示dialog
     */
    public void show() {
        setSheetItems();
        dialog.show();
    }


    /***
     * 取消dialog
     */
    public void dismiss() {
        dialog.dismiss();
    }

    /***
     * 默认颜色
     */
    public enum SheetItemColor {
        Blue("#037BFF"),
        Red("#FD4A2E");

        private String name;

        SheetItemColor(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
