package com.qingyun.mvpretrofitrx.mvp.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.senon.mvpretrofitrx.R;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.List;

public class IndicatorUtils {


    public static void initMagicIndicator3(MagicIndicator magicIndicator, final ViewPager vp, final List<String> titles, final Activity activity) {
        CommonNavigator commonNavigator = new CommonNavigator(activity);

        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return titles.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {

                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(activity);
                simplePagerTitleView.setNormalColor(activity.getResources().getColor(R.color.color_text_1));
                simplePagerTitleView.setmNormalTextSize((float) activity.getResources().getDimensionPixelSize(R.dimen.sp_13));
                simplePagerTitleView.setmSelectedTextSize((float) activity.getResources().getDimensionPixelSize(R.dimen.sp_16));
                simplePagerTitleView.setSelectedColor(activity.getResources().getColor(R.color.color_main_text));
                simplePagerTitleView.setText(titles.get(index));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vp.setCurrentItem(index);
                    }
                });

                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setLineHeight(activity.getResources().getDimensionPixelSize(R.dimen.dp_1_5));
                linePagerIndicator.setColors(activity.getResources().getColor(R.color.color_text_2));
                return linePagerIndicator;
            }
        }

        );
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, vp);

    }

    /**
     * @param vp
     * @param titles
     * @param activity
     * @param magicIndicator
     * @param defaultPosition 默认选中index
     * @param indiW           下标长度
     */

    public static void initMagicIndicator3M(final ViewPager vp, final List<String> titles, final Activity activity, MagicIndicator magicIndicator, int defaultPosition, final int indiW) {
        CommonNavigator commonNavigator = new CommonNavigator(activity);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return titles.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(activity);
                commonPagerTitleView.setContentView(R.layout.simple_pager_title_layout1);

                final TextView titleText = (TextView) commonPagerTitleView.findViewById(R.id.tv);
                titleText.setText(titles.get(index));

                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {

                    @Override
                    public void onSelected(int index, int totalCount) {
                        titleText.setTextSize(17);
                        titleText.setTextColor(activity.getResources().getColor(R.color.color_FFFFFF));
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        titleText.setTextSize(14);
                        titleText.setTextColor(activity.getResources().getColor(R.color.color_CDD6FD));

                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {

                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {

                    }


                });

                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vp.setCurrentItem(index);
                    }
                });

                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                linePagerIndicator.setLineWidth(UIUtil.dip2px(context, indiW));
                linePagerIndicator.setYOffset(context.getResources().getDimension(R.dimen.dp_65));
                linePagerIndicator.setLineHeight(activity.getResources().getDimensionPixelSize(R.dimen.dp_2));
                linePagerIndicator.setColors(activity.getResources().getColor(R.color.color_FFFFFF));
                return linePagerIndicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        magicIndicator.onPageSelected(defaultPosition);
        ViewPagerHelper.bind(magicIndicator, vp);
    }




    /**
     * @param vp
     * @param titles
     * @param activity
     * @param magicIndicator
     * @param defaultPosition 默认选中index
     * @param indiW           下标长度
     */

    public static void initMagicIndicator3(final ViewPager vp, final List<String> titles, final Activity activity, MagicIndicator magicIndicator, int defaultPosition, final int indiW) {
        CommonNavigator commonNavigator = new CommonNavigator(activity);
//        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return titles.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(activity);
                commonPagerTitleView.setContentView(R.layout.simple_pager_title_layout);

                final TextView titleText = (TextView) commonPagerTitleView.findViewById(R.id.tv);
                titleText.setText(titles.get(index));

                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {

                    @Override
                    public void onSelected(int index, int totalCount) {

                        titleText.setTextSize(TypedValue.COMPLEX_UNIT_PX,activity.getResources().getDimensionPixelSize(R.dimen.sp_16));
                        titleText.setTextColor(activity.getResources().getColor(R.color.color_main_text));
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        titleText.setTextSize(TypedValue.COMPLEX_UNIT_PX,activity.getResources().getDimensionPixelSize(R.dimen.sp_13));

                        titleText.setTextColor(activity.getResources().getColor(R.color.color_text_1));

                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {

                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {

                    }


                });

                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vp.setCurrentItem(index);
                    }
                });

                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                linePagerIndicator.setLineWidth(UIUtil.dip2px(context, indiW));

                linePagerIndicator.setLineHeight(activity.getResources().getDimensionPixelSize(R.dimen.dp_2));
                linePagerIndicator.setColors(activity.getResources().getColor(R.color.color_FFFFFF));
                return linePagerIndicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        magicIndicator.onPageSelected(defaultPosition);
        ViewPagerHelper.bind(magicIndicator, vp);
    }




}
