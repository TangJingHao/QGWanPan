package com.example.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BottomPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;

    public BottomPagerAdapter(@NonNull @NotNull FragmentManager fm, List<Fragment> mList) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);//懒加载节约资源
        this.mList = mList;
    }
    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
