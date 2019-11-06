package com.lzj.gallery.library.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class AssetsViewPagerAdapter extends FragmentPagerAdapter {
    private  List<Fragment> fragments;
    private  Context context;

    public AssetsViewPagerAdapter(Context context, List<Fragment> fragments, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
