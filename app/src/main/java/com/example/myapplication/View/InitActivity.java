package com.example.myapplication.View;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Adapter.InitPageAdapter;
import com.example.myapplication.R;
import com.example.myapplication.basic.SuperBaseActivity;
import com.example.myapplication.bean.CircleImageView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/30/9:16
 * @Description:
 */
public class InitActivity extends SuperBaseActivity {
    private CircleImageView mCircleImageView;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        mCircleImageView = this.findViewById(R.id.init_my_icon_cv);
        mTabLayout = this.findViewById(R.id.init_tab_layout);
        mViewPager = this.findViewById(R.id.init_view_pager);
        mTabLayout.addTab(mTabLayout.newTab().setText("登录"));
        mTabLayout.addTab(mTabLayout.newTab().setText("注册"));
        mFragments.add(new LoginFragment());
        mFragments.add(new RegisterFragment());
        InitPageAdapter initPageAdapter = new InitPageAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(initPageAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
