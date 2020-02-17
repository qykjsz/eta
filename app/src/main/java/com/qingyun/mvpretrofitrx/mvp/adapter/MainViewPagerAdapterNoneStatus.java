package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class MainViewPagerAdapterNoneStatus extends FragmentStatePagerAdapter {
    private  FragmentManager fm;
    private  List<String> titles;
    private  List<BaseFragment> fragments;
    private  Context context;
    private List<String> tags;//标示fragment的tag
    private List<TextView> tvStatus = new ArrayList<>();
    private List<TextView> tvCount = new ArrayList<>();

    public MainViewPagerAdapterNoneStatus(Context context, List<BaseFragment> fragments, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        this.fm =fm;
    }

    public MainViewPagerAdapterNoneStatus(Context context, List<BaseFragment> fragments, List<String> titles, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        this.titles = titles;
        this.fm =fm;

    }




    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (titles!=null) return titles.get(position);
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    public void notifyDataSetChanged(List<BaseFragment>  fragments) {
        this.fragments = fragments;
        super.notifyDataSetChanged();
    }


    public void  replaceFragment(int index,BaseFragment baseFragment){
//            fragments.add(index,baseFragment);
//            fragments.remove(index+1);
////            getSupportFragmentManager().beginTransaction().replace(0,new AssetFragment()).commit();
//            fm.beginTransaction().replace(0,baseFragment).commit();
//        notifyDataSetChanged();

    }

    @Override
    public int getItemPosition(@NonNull Object object) {
//        if (object instanceof USHistortFragment) {
//            ((USHistortFragment) object).upDate(normalData);
//        }
        return super.getItemPosition(object);
    }
}
