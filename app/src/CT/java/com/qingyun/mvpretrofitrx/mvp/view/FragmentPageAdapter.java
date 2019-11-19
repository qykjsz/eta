package com.qingyun.mvpretrofitrx.mvp.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.senon.mvpretrofitrx.R;

import org.xutils.common.util.DensityUtil;

import java.util.List;

/**
 * author:  Sam
 * date:  2014/9/26
 * ClassName:
 */
public class FragmentPageAdapter extends PagerAdapter implements
        OnPageChangeListener, TabScrollView.TabInterface {

    private Context mContext;

    /** tab列表 */
    private View[] mTabs;

    protected List<Fragment> mFragments; // 每个Fragment对应一个Page
    private FragmentManager mFragmentManager;
    private ViewPager mViewPager; // viewPager对象
    private int mCurrentPageIndex = 0; // 当前page索引（切换之前）
    private OnExtraPageChangeListener onExtraPageChangeListener; // ViewPager切换页面时的额外功能添加接口

    public FragmentPageAdapter(Context mContext, View[] mTabs,
                               List<Fragment> fragments, FragmentManager fragmentManager,
                               ViewPager viewPager, int currentPageIndex) {
        super();
        this.mContext = mContext;
        this.mTabs = mTabs;
        this.mFragments = fragments;
        this.mFragmentManager = fragmentManager;
        this.mViewPager = viewPager;
        this.mCurrentPageIndex = currentPageIndex;
        if (mTabs != null && mTabs.length > 0) {
            this.mViewPager.setOnPageChangeListener(this);
        }
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mFragments.get(position).getView()); // 移出viewpager两边之外的page布局
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = mFragments.get(position);
        if (!fragment.isAdded()) { // 如果fragment还没有added
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.add(fragment, fragment.getClass().getSimpleName());
            ft.commitAllowingStateLoss();// 解决 java.lang.IllegalStateException:
            // Can not perform this action after
            // onSaveInstanceState
            /**
             * 在用FragmentTransaction.commit()方法提交FragmentTransaction对象后
             * 会在进程的主线程中，用异步的方式来执行。 如果想要立即执行这个等待中的操作，就要调用这个方法（只能在主线程中调用）。
             * 要注意的是，所有的回调和相关的行为都会在这个调用中被执行完成，因此要仔细确认这个方法的调用位置。
             */
            mFragmentManager.executePendingTransactions();
        }
        if (fragment.getView().getParent() == null) {
            container.addView(fragment.getView()); // 为viewpager增加布局
        }

        return fragment.getView();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (null != onExtraPageChangeListener) { // 如果设置了额外功能接口
            onExtraPageChangeListener.onExtraPageScrollStateChanged(state);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
            int positionOffsetPixels) {
        if (null != onExtraPageChangeListener) { // 如果设置了额外功能接口
            onExtraPageChangeListener.onExtraPageScrolled(position,
                    positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
//        ZLog.showPost("onPageSelected：" + position);
//        ZLog.showPost("mFragments.get(position).isAdded()：" + mFragments.get(position).isAdded());
        mFragments.get(mCurrentPageIndex).onPause(); // 调用切换前Fargment的onPause()
        // fragments.get(currentPageIndex).onStop(); // 调用切换前Fargment的onStop()
        if (mFragments.get(position).isAdded()) {
            // fragments.get(i).onStart(); // 调用切换后Fargment的onStart()
            // Fragment fragment = mFragments.get(position);
            // fragment.onResume(); // 调用切换后Fargment的onResume()
        }
        mCurrentPageIndex = position;
        if (null != onExtraPageChangeListener) { // 如果设置了额外功能接口
            onExtraPageChangeListener.onExtraPageSelected(position);
        }
    }

    public OnExtraPageChangeListener getOnExtraPageChangeListener() {
        return onExtraPageChangeListener;
    }

    /**
     * 设置页面切换额外功能监听器
     *
     * @param onExtraPageChangeListener
     */
    public void setOnExtraPageChangeListener(
            OnExtraPageChangeListener onExtraPageChangeListener) {
        this.onExtraPageChangeListener = onExtraPageChangeListener;
    }

    /**
     * 开放page切换的额外接口
     */
    static class OnExtraPageChangeListener {
        public void onExtraPageScrolled(int position, float positionOffset,
                int positionOffsetPixels) {
        }

        public void onExtraPageSelected(int position) {

        }

        public void onExtraPageScrollStateChanged(int state) {
        }
    }

    @Override
    public View getTabView() {
        ImageView bottom = new ImageView(mContext);
        /** 设置底部线条大小 后续可传入模式, 根据模式设置横线 */
        bottom.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, DensityUtil.dip2px(1)));
//        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) bottom.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
//        linearParams.width = 30;// 控件的宽强制设成30
//        bottom.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
//        bottom.setMaxHeight(DensityUtil.dip2px(8));

        /**Sam 设置线条颜色*/
        bottom.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));

        return bottom;
    }

    @Override
    public View getTabItem(int position) {
        return mTabs[position];
    }

    @Override
    public int getTabCount() {
        return mTabs == null ? 0 : mTabs.length;
    }
}
