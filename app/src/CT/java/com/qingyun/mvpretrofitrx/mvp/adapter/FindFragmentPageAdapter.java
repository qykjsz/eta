package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qingyun.mvpretrofitrx.mvp.fragment.FindViewPageFragment;
import com.qingyun.mvpretrofitrx.mvp.view.FragmentPageAdapter;

import java.util.List;

/**
 * Date 2019/7/1.
 * Created by Sam
 * ClassExplain：
 */
public class FindFragmentPageAdapter extends FragmentPageAdapter {

    public FindFragmentPageAdapter(Context mContext, View[] mTabs, List<Fragment> fragments, FragmentManager fragmentManager, ViewPager viewPager, int currentPageIndex) {
        super(mContext, mTabs, fragments, fragmentManager, viewPager, currentPageIndex);
    }
    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);

        Fragment fragment = mFragments.get(position);
        if(fragment.isAdded()){//设置被关联
            ((FindViewPageFragment)fragment).doRefresh();
        }
        //设置多个fragment
//        if(position == 0){
//            if(fragment.isAdded()){//设置被关联
//                ((CommunityDataFragment) fragment).doRefresh();
//            }
//        }else{
//            if(fragment.isAdded()){//设置被关联
//                ((第二个Fragment) fragment).doRefresh();
//            }
//        }

    }
}
