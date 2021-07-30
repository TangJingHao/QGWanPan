package com.example.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/30/9:09
 * @Description:
 */
public class InitPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments=new ArrayList<>();

    public InitPageAdapter(@NonNull @NotNull FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mFragments=fragments;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

}
