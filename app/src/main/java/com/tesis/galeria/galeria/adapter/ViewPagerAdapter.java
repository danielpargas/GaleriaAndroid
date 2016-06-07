package com.tesis.galeria.galeria.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by danie on 23/5/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter{

    public ArrayList<Fragment> mFragments = new ArrayList<>();
    public ArrayList<String> mTitulos = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    public void addFragment(Fragment fragment, String titulo){
        mFragments.add(fragment);
        mTitulos.add(titulo);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitulos.get(position);
    }
}
