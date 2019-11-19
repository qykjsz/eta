package com.qingyun.mvpretrofitrx.mvp.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.Scroller;


/**
 * author:  Sam
 * date:  2014/3/26
 * ClassName:
 */
public class TabScrollView extends LinearLayout implements OnGestureListener,
        OnClickListener {

    private static final String TAG = "TabScrollView";

    public static interface TabInterface {
        public View getTabView();

        public View getTabItem(int position);

        public int getTabCount();
    }

    /**
     * 设置每个item点击改变后的监听器
     *
     *
     * @date 2014-6-20
     */
    public static interface OnItemSelectListener {
        /**
         * @Description: 选项的编号，Object为setData中设置的数据
         *
         * @date 2014-6-20
         */
        public void onItemSelect(int index, View itemView);
    }

    /**
     * 设置滚动监听器
     *
     */
    public static interface OnScrollListener {
        /**
         * @Description: 滚动到头部，滚动到尾部，滚动到中间，是否能滚动的回调
         *
         * @date 2014-6-20
         */
        public void onScroll(boolean atStart, boolean atEnd, boolean atMiddle,
                             boolean noScroll);
    }

    private final int ID_TAB_LAYOUT = 10086;

    /** 整个空间的宽度，包括滚动部分的 */
    private int mWidth;

    /** 控件显示在界面上的宽度 */
    private int mLayoutWidth;

    private GestureDetector mDetector;
    private OverScroller mScroller;

    /** 滚动监听 */
    private OnScrollListener mScrollListener;

    /** 每个选项点击监听 */
    private OnItemSelectListener mSelectListener;

    private TabInterface mTabInterface;

    private OnPageChangeListener mPageChangeListener;

    private ViewPager mViewPager;

    /** 自动移动显示下一个条目所移动的距离 */
    private int mLitterMove;

    /** 可以滚动的条 */
    private TabLayout mTabLayout;

    /** 当前是否已经附加到当前界面上,防止第一次设置item的时候滚动条显示 */
    private boolean isAttachedToWindow;
    private boolean isEventCancel;

    /** 当前选中的item的位置 */
    private int mCurrentPosition;

    private int mVerticalSpace;
    private int mTabViewSpace;

    public TabScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        // initDefaultValue();
    }

    public TabScrollView(Context context) {
        super(context);
        init(context);
        initDefaultValue();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
//        ZLog.i(TAG, "onAttachedToWindow");
        isAttachedToWindow = true;
        postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mTabInterface != null) {
                    onClick(getChildAt(mCurrentPosition));
                }
            }
        }, 300);
    }

    private void init(Context ctx) {
        mDetector = new GestureDetector(ctx, this);
        mScroller = new OverScroller(ctx,
                new AccelerateDecelerateInterpolator());
        setStaticTransformationsEnabled(true);
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    private void initDefaultValue() {
        mVerticalSpace = dp2px(3);
        mTabViewSpace = dp2px(2);

    }

    // DataSetObservable
    private void reLayoutViews() {
        removeAllViews();
        scrollTo(0, 0);

        if (mTabInterface == null) {
            return;
        }

        final int length = mTabInterface.getTabCount();
        // final int padding = DisplayUtils.dip2px(getContext(), 5);

        /**Sam设置一页多少个*/
        int width;
        if (length > 7) {
            width = getContext().getResources().getDisplayMetrics().widthPixels / 7;
        } else {
            width = getContext().getResources().getDisplayMetrics().widthPixels
                    / length;

        }
        for (int i = 0; i < length; i++) {
//            LayoutParams params = new LayoutParams(width,
//                  LayoutParams.MATCH_PARENT);

                        LayoutParams params = new LayoutParams(width,
                    LayoutParams.MATCH_PARENT);
            // params.bottomMargin = mTabViewSpace;
            // params.leftMargin = params.rightMargin = mVerticalSpace;
            View v = mTabInterface.getTabItem(i);
            v.setClickable(true);
            v.setFocusable(true);
            addView(v, params);

            v.setOnClickListener(this);
            v.setId(i);
        }



        /**
         * 和Tab一样宽
         */
        addTabView(width);
        changScrollState();
        //
        if (length > 0) {
            onClick(getChildAt(0));
        }
    }



    // DataSetObservable
    private void reWrapContentLayoutViews() {
        removeAllViews();
        scrollTo(0, 0);

        if (mTabInterface == null) {
            return;
        }

        final int length = mTabInterface.getTabCount();
        // final int padding = DisplayUtils.dip2px(getContext(), 5);

        /**Sam设置一页多少个*/
        int width;
        if (length > 7) {
            width = getContext().getResources().getDisplayMetrics().widthPixels / 7;
        } else {
            width = getContext().getResources().getDisplayMetrics().widthPixels
                    / length;

        }
        for (int i = 0; i < length; i++) {
//            LayoutParams params = new LayoutParams(width,
//                  LayoutParams.MATCH_PARENT);

            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT);
            // params.bottomMargin = mTabViewSpace;
            // params.leftMargin = params.rightMargin = mVerticalSpace;
            View v = mTabInterface.getTabItem(i);
            v.setClickable(true);
            v.setFocusable(true);
            addView(v, params);

            v.setOnClickListener(this);
            v.setId(i);
        }
        /**
         * 和Tab一样宽
         */
        addTabView(width);
        changScrollState();
        //
        if (length > 0) {
            onClick(getChildAt(0));
        }
    }
    // 广告中,tab不能点击
    private void reWrapContentLayoutViews(boolean enable) {
        removeAllViews();
        scrollTo(0, 0);

        if (mTabInterface == null) {
            return;
        }

        final int length = mTabInterface.getTabCount();
        // final int padding = DisplayUtils.dip2px(getContext(), 5);

        /**Sam设置一页多少个*/
        int width;
        if (length >7) {
            width = getContext().getResources().getDisplayMetrics().widthPixels / 7;
        } else {
            width = getContext().getResources().getDisplayMetrics().widthPixels
                    / length;

        }
        for (int i = 0; i < length; i++) {
//            LayoutParams params = new LayoutParams(width,
//                  LayoutParams.MATCH_PARENT);

            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT);
            // params.bottomMargin = mTabViewSpace;
            // params.leftMargin = params.rightMargin = mVerticalSpace;
            View v = mTabInterface.getTabItem(i);
            v.setClickable(enable);
            v.setFocusable(enable);
            addView(v, params);

//            v.setOnClickListener(this);
            v.setId(i);
        }
        /**
         * 和Tab一样宽
         */
        addTabView(width/2);
        changScrollState();
        //
        if (length > 0) {
            onClick(getChildAt(0));
        }
    }
    public int dp2px(int dp) {
        return (int) (getResources().getDisplayMetrics().density * dp);
    }

    private void addTabView(int width) {
        mTabLayout = new TabLayout(getContext());
        mTabLayout.setTabView(mTabInterface.getTabView());
        mTabLayout.setLayoutParams(new LayoutParams(width, LayoutParams.WRAP_CONTENT));
        mTabLayout.setId(ID_TAB_LAYOUT);
        addView(mTabLayout);
    }


    private void measureTab() {
        if (mTabLayout != null) {
            final int widthSpac = MeasureSpec.makeMeasureSpec(mWidth,
                    MeasureSpec.EXACTLY);
            final int heightSpac = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
            mTabLayout.measure(widthSpac, heightSpac);
        }
    }

    // private View getItem(Object obj, int id, int padding) {
    // Button btn = new Button(getContext());
    // btn.setText(obj.toString());
    // // btn.setBackgroundResource(R.drawable.news_tab_item_selector);
    // btn.setBackgroundResource(0);
    // btn.setGravity(Gravity.CENTER);
    // btn.setPadding(padding, padding, padding, padding);
    // btn.setOnClickListener(this);
    // btn.setSingleLine();
    // btn.setId(id);
    // btn.setTextColor(getResources().getColorStateList(R.color.news_tab_text_selector));
    // return btn;
    // }

    @Override
    public void onClick(View v) {
//        ZLog.i(TAG, "onClick" + v.getId());


        mCurrentPosition = v.getId();
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);

            if (child.getId() == ID_TAB_LAYOUT) {
                continue;
            }

            if (child != v) {
                child.setEnabled(true);
            } else {
                child.setEnabled(false);
                mHandler.sendEmptyMessageDelayed(child.getId(), 50);
                if (mSelectListener != null) {
                    mSelectListener.onItemSelect(i, v);
                }

                if (mViewPager != null) {
                    mViewPager.setCurrentItem(i);
                }
            }
        }
    }

    /**
     * 设置ViewPager的CurrentIndex 手动调用(模拟点击Tabbutton)
     *
     * @param toPosition
     */
    public void outerClickForMove(int toPosition) {
        mCurrentPosition = getChildAt(toPosition).getId();

        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);

            if (child.getId() == ID_TAB_LAYOUT) {
                continue;
            }

            if (child != getChildAt(toPosition)) {
                child.setEnabled(true);
            } else {
                child.setEnabled(false);

                mHandler.sendEmptyMessageDelayed(child.getId(), 50);

                if (mSelectListener != null) {
                    mSelectListener.onItemSelect(i, getChildAt(toPosition));
                }

                if (mViewPager != null) {
                    mViewPager.setCurrentItem(i);
                }
            }
        }
    }

    private boolean isViewVisitableFullInScreen(int viewId) {
        return isViewVisitableFullInScreen(findViewById(viewId));
    }

    private boolean isViewVisitableFullInScreen(View view) {
        return view.getLeft() >= getScrollX()
                && view.getRight() <= getScrollX() + mLayoutWidth;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        } else {
            changScrollState();
        }
    }

    private boolean needScroll() {
        return mWidth > mLayoutWidth;
    }

    private void changScrollState() {
        if (mScrollListener == null) {
            return;
        }

        if (!needScroll()) {
            mScrollListener.onScroll(false, false, false, true);
        } else {
            final float scrollX = getScrollX();

            if (scrollX < 3) {
                mScrollListener.onScroll(true, false, false, false);
            } else if (scrollX + mLayoutWidth >= mWidth - 3) {
                mScrollListener.onScroll(false, false, true, false);
            } else {
                mScrollListener.onScroll(false, true, false, false);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (mTabInterface != null && mTabInterface.getTabCount() > 0
                && mWidth < mLayoutWidth) {
            final int childCount = mTabInterface.getTabCount();

            int margin = (mLayoutWidth - mWidth) / childCount / 2;

            for (int i = 0; i < childCount; i++) {
                final View child = getChildAt(i);
                MarginLayoutParams params = (MarginLayoutParams) child
                        .getLayoutParams();
                params.leftMargin += margin;
                params.rightMargin += margin;
                child.setLayoutParams(params);
                mWidth += margin * 2;
            }
        }

        super.onLayout(changed, l, t, r, b);

        if (mTabLayout != null) {
            mTabLayout.layout(0, getHeight() - mTabLayout.getMeasuredHeight(),
                    mWidth, getHeight());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 0;
        int height = 0;
        final int parenWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int count = getChildCount();

        final int nWidthMeasureSpec = MeasureSpec.makeMeasureSpec(parenWidth,
                MeasureSpec.UNSPECIFIED);

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);

            if (child.getId() != ID_TAB_LAYOUT) {
                measureChild(child, nWidthMeasureSpec, heightMeasureSpec);
                final LayoutParams params = (LayoutParams) child
                        .getLayoutParams();
                width += child.getMeasuredWidth() + params.leftMargin
                        + params.rightMargin;
            }

            height = Math.max(child.getMeasuredHeight(), height);
        }

        mWidth = width;

        mLayoutWidth = parenWidth;

        measureTab();

        if (mLayoutWidth < 300) {
            mLitterMove = mLayoutWidth / 5;
        } else if (mLayoutWidth < 600) {
            mLitterMove = (int) (mLayoutWidth / 4.5f);
        } else {
            mLitterMove = (int) (mLayoutWidth / 3.5);
        }

        setMeasuredDimension(getDefaultSize(width, widthMeasureSpec),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }

    /**
     * 有阻力的ScrollBy();
     */
    private void resistanceScrollBy(float overScroll, float scrollby) {
        scrollBy((int) (scrollby * (mLayoutWidth - Math.abs(overScroll))
                / mLayoutWidth * 0.5), 0);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mDetector.onTouchEvent(ev);

        if (ev.getAction() == MotionEvent.ACTION_UP) {
            onUp();

            MotionEvent e = MotionEvent.obtain(ev);
            e.setAction(MotionEvent.ACTION_CANCEL);
            super.dispatchTouchEvent(e);
            e.recycle();
        }

        return true;
    }

    public void onUp() {

        final int scrollX = getScrollX();

        if (!needScroll()) {
            mScroller.startScroll(scrollX, 0, -scrollX, 0);
            postInvalidate();
            return;
        }

        if (scrollX < 0) {
            mScroller.startScroll(scrollX, 0, -scrollX, 0);
            postInvalidate();
        } else {
            final int s = scrollX + mLayoutWidth - mWidth;

            if (s > 0) {
                mScroller.startScroll(scrollX, 0, -s, 0);
                postInvalidate();
            }
        }

        changScrollState();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        mScroller.abortAnimation();
        super.dispatchTouchEvent(e);
        isEventCancel = false;
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        MotionEvent up = MotionEvent.obtain(e);
        up.setAction(MotionEvent.ACTION_UP);
        super.dispatchTouchEvent(up);
        up.recycle();
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {

        if (!isEventCancel) {
            MotionEvent cancel = MotionEvent.obtain(e1);
            cancel.setAction(MotionEvent.ACTION_CANCEL);
            super.dispatchTouchEvent(cancel);
            cancel.recycle();
            isEventCancel = true;
        }

        final int scrollX = getScrollX();
        if (scrollX <= 0 && distanceX <= 0) {
            resistanceScrollBy(scrollX, distanceX);

            changScrollState();
        } else if (scrollX + mLayoutWidth > mWidth && distanceX >= 0) {
            resistanceScrollBy(scrollX + mLayoutWidth - mWidth, distanceX);

            changScrollState();
        } else {
            scrollBy((int) distanceX, 0);

            changScrollState();
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        final float deltaX = e1.getX() - e2.getX();
        final float deltaY = e1.getY() - e2.getY();

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            int scrolledX = getScrollX();
            float scroll = -velocityX * 0.07f;

            if (scrolledX + scroll < 0) {
                scroll = -scrolledX;
            } else if (scrolledX + mLayoutWidth + scroll > mWidth) {
                scroll = mWidth - scrolledX - mLayoutWidth;
            }

            mScroller.fling(getScrollX(), getScrollY(), (int) -velocityX, 0, 0,
                    mWidth - mLayoutWidth, 0, 0, mLayoutWidth / 3, 0);
            postInvalidate();
        }
        return false;
    }

    public final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            View clickView = findViewById(msg.what);
            moveFull(clickView);
            mTabLayout.scrollToView(clickView, getScrollX());
        }

        ;
    };

    /**
     * @Description: 移动布局将上一个或者下一个控件的部分显示出来
     *
     * @date 2014-7-1
     */
    private void moveFull(View view) {
        if (!needScroll()) {
            changScrollState();
            return;
        }

        final int scrollLeft = getScrollX();
        final int left = view.getLeft();

        if (mLitterMove > left - scrollLeft) {
            int deltaX = scrollLeft - left + mLitterMove;

            if (deltaX > scrollLeft) {
                deltaX = scrollLeft;
            }

            mScroller.startScroll(scrollLeft, 0, -deltaX, 0);
            postInvalidate();

            return;
        }

        final int scrollRight = scrollLeft + mLayoutWidth;
        final int right = view.getRight();
        final int hide = right - scrollRight;

        if (hide > 0 || Math.abs(hide) < mLitterMove) {
            int deltaX = 0;

            if (hide > 0) {
                deltaX = mLitterMove + hide;
            } else {
                deltaX = hide + mLitterMove;
            }

            if (scrollRight + deltaX > mWidth) {
                deltaX = mWidth - scrollRight;
            }

            mScroller.startScroll(scrollLeft, 0, deltaX, 0);
            postInvalidate();
        }
    }

    public int getCurrentItem() {
        if (mTabInterface == null || mTabInterface.getTabCount() == 0) {
            return -1;
        }
        return mCurrentPosition;
    }

    /**
     * 设置每个Item之间的距离
     *
     * @param space
     */
    public void setVerticalSpacing(int space) {
        mVerticalSpace = space;
    }

    /**
     * 设置TabView和Item之间的距离
     *
     * @param space
     */
    public void setTabSpacing(int space) {
        mTabViewSpace = space;
    }

    public OnPageChangeListener getOnPageChangeListener() {
        if (mTabInterface == null) {
            return null;
        }
        return new ViewPagerOnPositionChangeListener();
    }

    /**
     * @Description: 设置滚动监听器
     *
     * @date 2014-6-20
     */
    public void setOnScrollListener(OnScrollListener l) {
        mScrollListener = l;

        changScrollState();
    }

    /**
     * @Description: 设置item选中改变的监听器
     *
     * @date 2014-6-20
     */
    public void setOnItemSelectListener(OnItemSelectListener l) {
        mSelectListener = l;
    }

    public void smoothScrollToPosition(int position) {
        if (mTabInterface != null && position >= 0
                && position < mTabInterface.getTabCount()) {
            if (isAttachedToWindow) {
                onClick(getChildAt(position));
            } else {
                mCurrentPosition = position;
            }
        }
    }

    /**
     * @Description: 向左移动半个屏幕
     *
     *
     * @date 2014-6-4
     * @return void
     */
    public void moveLeft() {
        final int scrollRight = getScrollX() + mLayoutWidth;

        int deltaX = mLayoutWidth / 2;

        if (scrollRight + deltaX > mWidth) {
            deltaX = mWidth - scrollRight;
        }

        mScroller.startScroll(getScrollX(), 0, deltaX, 0);
        postInvalidate();
    }

    /**
     * @Description: 向右移动半个屏幕
     *
     *
     * @date 2014-6-4
     * @return void
     */
    public void moveRight() {
        final int scrollLeft = getScrollX();

        int deltaX = mLayoutWidth / 2;

        if (scrollLeft - deltaX < 0) {
            deltaX = scrollLeft;
        }

        mScroller.startScroll(scrollLeft, 0, -deltaX, 0);
        postInvalidate();
    }

    public void notifyDataSetChanged() {
        reLayoutViews();
    }

    public void setOnPageChangeListener(OnPageChangeListener l) {
        mPageChangeListener = l;
    }

    /**
     * @Description: 设置每个选项的数据，数据可以是任意类型，控件会调用Object的toString()方法设置成每个选项的文字
     *
     * @date 2014-6-20
     */
    public void setAdapter(TabInterface adapter) {
        mTabInterface = adapter;
        reLayoutViews();
    }

    /**
     * @Description: 设置每个选项的数据，数据可以是任意类型，控件会调用Object的toString()方法设置成每个选项的文字
     *
     * @date 特殊的  学习鼓励用的
     */
    public void setStudyManageAdapter(TabInterface adapter) {
        mTabInterface = adapter;
        reWrapContentLayoutViews();
    }

    public void setViewPager(ViewPager pager) {
        mViewPager = pager;
        PagerAdapter adapter = pager.getAdapter();

        if (adapter != null && adapter instanceof TabInterface) {
            mTabInterface = (TabInterface) adapter;
            pager.setOnPageChangeListener(getOnPageChangeListener());
        }
        reLayoutViews();
    }
    public void setWrapContentViewPager(ViewPager pager) {
        mViewPager = pager;
        PagerAdapter adapter = pager.getAdapter();

        if (adapter != null && adapter instanceof TabInterface) {
            mTabInterface = (TabInterface) adapter;
            pager.setOnPageChangeListener(getOnPageChangeListener());
        }
        reWrapContentLayoutViews();
    }
    //不可点击
    public void setWrapContentViewPagerUnable(ViewPager pager) {
        mViewPager = pager;
        PagerAdapter adapter = pager.getAdapter();

        if (adapter != null && adapter instanceof TabInterface) {
            mTabInterface = (TabInterface) adapter;
            pager.setOnPageChangeListener(getOnPageChangeListener());
        }
        reWrapContentLayoutViews(false);
    }

    /**tab的下划线的高度,fragment嵌套会有获取不到高度的bug,以后优化*/
    private int mTabHeight;

    private class TabLayout extends ViewGroup {
        Scroller mWidthScroller;
        Scroller mPositionScroller;

        View mTabView;

        int mHeight = 0;

        public TabLayout(Context context) {
            super(context);
            mWidthScroller = new Scroller(context, new LinearInterpolator());
            mPositionScroller = new Scroller(context, new LinearInterpolator());
        }

        @Override
        public void computeScroll() {
            if (mTabView == null) {
                return;
            }

            boolean hasChange = false;

            if (mWidthScroller.computeScrollOffset()) {
                mTabView.layout(0, 0, mWidthScroller.getCurrX(), mHeight);
                hasChange = true;
            }

            if (mPositionScroller.computeScrollOffset()) {
                scrollTo(mPositionScroller.getCurrX(),
                        mPositionScroller.getCurrY());
                hasChange = true;
            }

            if (hasChange) {
                postInvalidate();
            }
        }

        public void setTabView(View view) {
            removeAllViews();

            mTabView = view;

            if (view != null) {
                addView(view);
            }
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            if (mTabView != null) {
                mTabView.measure(widthMeasureSpec, heightMeasureSpec);
                mHeight = mTabView.getMeasuredHeight();

                if (mHeight <= 0) {
                    LayoutParams params = mTabView.getLayoutParams();
                    mHeight = params != null ? params.height > 0 ? params.height
                            : getDefaultTabHeight()
                            : getDefaultTabHeight();
                    mTabView.measure(widthMeasureSpec, MeasureSpec
                            .makeMeasureSpec(mHeight, MeasureSpec.EXACTLY));
                }
                mTabHeight = mTabView.getMeasuredHeight();
            }
            setMeasuredDimension(widthMeasureSpec, mHeight);
        }

        public int getDefaultTabHeight() {
            return dp2px(2);
        }

        public void scrollToView(View view, int parentScrollX) {
            if (mTabView == null) {
                return;
            }

            if (view.getWidth() != mTabView.getWidth()) {
                mWidthScroller.startScroll(mTabView.getWidth(), 0,
                        view.getWidth() - mTabView.getWidth(), 0, 200);
            }

            mPositionScroller.startScroll(getScrollX(), 0,
                    -(getScrollX() + view.getLeft()), 0, 200);

            postInvalidate();
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {

        }
    }

    /**
     * ViewPager控制Tab滚动的OnPageChangeListener
     *
     * @date 2014-7-2
     */
    private class ViewPagerOnPositionChangeListener implements
            OnPageChangeListener {

        boolean inTouch = false;

        @Override
        public void onPageScrollStateChanged(int state) {
            if (mPageChangeListener != null) {
                mPageChangeListener.onPageScrollStateChanged(state);
            }

            inTouch = state == 1 ? true : inTouch;

            if (state == 1 && !isViewVisitableFullInScreen(mCurrentPosition)) {
                mHandler.sendEmptyMessage(mCurrentPosition);
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                int positionOffsetPixels) {
            if (mPageChangeListener != null) {
                mPageChangeListener.onPageScrolled(position, positionOffset,
                        positionOffsetPixels);
            }

//            ZLog.i(TAG, "mTabLayout.mTabView:" + mTabLayout.mTabView);
            if (!inTouch || mTabLayout.mTabView == null) {
                return;
            }

            // 向右滑动
            if (position == mCurrentPosition) {
                View current = getChildAt(position);
                View target = getChildAt(position + 1);
                System.out.println(target + "ta" + current + "cur");
                int deltaX = (int) ((target.getLeft() - current.getLeft()) * positionOffset);
                int deltaWidth = (int) ((target.getWidth() - current.getWidth()) * positionOffset);

                mTabLayout.scrollTo(-(current.getLeft() + deltaX), 0);

                final View v = mTabLayout.mTabView;
//                ZLog.i(TAG, "v:" + v.getVisibility() + "v:" + v.getHeight() + "v:" + v.getMeasuredHeight() + "v.getBottom()" + v.getBottom());
                v.layout(v.getLeft(), v.getTop(),
                        v.getLeft() + current.getWidth() + deltaWidth,
                        v.getBottom());
//                v.layout(v.getLeft(), v.getTop(),
//                        v.getLeft() + current.getWidth() + deltaWidth,
//                        mTabHeight);
            }
            // 向左滑动
            else if (position < mCurrentPosition) {
                View current = getChildAt(position + 1);
                View target = getChildAt(position);

                float change = 1 - positionOffset;
                System.out.println(target + "ta" + current + "cur");
                int deltaX = (int) ((current.getLeft() - target.getLeft()) * change);
                int deltaWidth = (int) ((target.getWidth() - current.getWidth()) * change);

                mTabLayout.scrollTo(-current.getLeft() + deltaX, 0);

                final View v = mTabLayout.mTabView;
                v.layout(v.getLeft(), v.getTop(),
                        v.getLeft() + current.getWidth() + deltaWidth,
                        v.getBottom());
//                v.layout(v.getLeft(), v.getTop(),
//                        v.getLeft() + current.getWidth() + deltaWidth,
//                        mTabHeight);
//                ZLog.i(TAG, "v:" + v.getVisibility() + "v:" + v.getHeight());
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (mPageChangeListener != null) {
                mPageChangeListener.onPageSelected(position);
            }

            if (inTouch) {
                onClick(getChildAt(position));
                inTouch = false;
            }
        }
    }
}
